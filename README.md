# Mavenproject2

A simple turn-based combat game that uses the Gemini API for combat decisions and Gson to parse API responses.

- Maven project: `pom.xml`
- Java sources: `src/main/java/com/mycompany/mavenproject2/`
- Main class: `com.mycompany.mavenproject2.Mavenproject2`

The Maven configuration targets Java 23, matching the Windows NetBeans setup.
Set your Gemini API key in the environment before running the game.
Open `pom.xml` in NetBeans, or build with Maven and JDK 23:

```sh
mvn package
```

The Windows NetBeans project and the WSL workspace are separate Git checkouts.
Commit and push from the checkout where you edit, then pull in the other checkout.

Keep the repository private until the owner explicitly requests a visibility change.
