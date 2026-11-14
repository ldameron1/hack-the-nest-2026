import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/** Small Java 21 adapter for Exa's search API. Returns the response as JSON. */
public class ExaSearch {
    public static String search(String query, String objective)
            throws IOException, InterruptedException {
        String body = payload(query, objective);
        String key = System.getenv("EXA_API_KEY");
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Set EXA_API_KEY in your environment or local .env file.");
        }

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.exa.ai/search"))
                .timeout(Duration.ofSeconds(60))
                .header("Content-Type", "application/json")
                .header("x-api-key", key)
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();

        try (HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10)).build()) {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                String hint = switch (response.statusCode()) {
                    case 401, 403 -> "Check your Exa key and account permissions.";
                    case 429 -> "Check your Exa rate limit and remaining credits.";
                    default -> "Check Exa's status and request documentation.";
                };
                // Do not log credentials, request headers, or arbitrary error bodies.
                throw new IOException("Exa returned HTTP " + response.statusCode() + ". " + hint);
            }
            return response.body();
        }
    }

    static String payload(String query, String objective) {
        if (query == null || query.isBlank() || objective == null || objective.isBlank()) {
            throw new IllegalArgumentException("Provide a nonempty query and search objective.");
        }
        if (objective.length() > 4096) {
            throw new IllegalArgumentException("The search objective must be at most 4096 characters.");
        }
        return "{\"query\":" + jsonString(query)
                + ",\"objective\":" + jsonString(objective)
                + ",\"contents\":{\"highlights\":true}}";
    }

    // No JSON dependency is needed for this request's two string fields.
    private static String jsonString(String value) {
        StringBuilder result = new StringBuilder("\"");
        for (char c : value.toCharArray()) {
            switch (c) {
                case '"' -> result.append("\\\"");
                case '\\' -> result.append("\\\\");
                default -> {
                    if (c < 0x20 || Character.isSurrogate(c)) {
                        result.append(String.format("\\u%04x", (int) c));
                    } else {
                        result.append(c);
                    }
                }
            }
        }
        return result.append('"').toString();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: bash tools/exa-search.sh \"query\" \"objective\"");
            System.exit(2);
        }
        try {
            System.out.println(search(args[0], args[1]));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Exa search interrupted.");
            System.exit(1);
        } catch (IOException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
