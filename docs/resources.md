# Available resources

Checked November 14, 2026. Event offers below are from the supplied **Hack_The_Nest_2026_Hacker_Packet_updated.pdf**. They are advertised offers, not confirmation that credits have been claimed or capacity remains. Full track resource guides are separate PDFs referenced by the packet; request them from organizers/Discord. Access codes and keys are intentionally omitted.

## Event credits and compute

| Resource | Advertised offer | How to obtain / limits | Java fit |
| --- | --- | --- | --- |
| [Exa](https://docs.exa.ai/) | $50 search API credits per hacker | Code at kickoff / Discord | HTTP API; search and retrieval, not LLM inference |
| [ElevenLabs](https://elevenlabs.io/docs/overview) | Creator plan for one month | Access key at kickoff / Discord; plan quotas apply | HTTP API for voice/audio |
| Brev GPU credits | About 50 teams, first come first served | Captain posts one line about project in #operation-qa-participants and tags @Matt Cool; code by DM. Credit amount/hardware not specified | Java can call a model service running there; GPU model work may need other tools |
| [Seqera](https://cloud.seqera.io/) | Team workspace, AWS compute, AI Co-Scientist | Sign up; give team emails to Code of Life help desk. Shared credits; bioinformatics track use | Pipelines use Nextflow tooling; Java can consume results |
| [Vultr](https://mlh.link/vultr) | Signup cloud credits | Packet gives no amount, expiry, card requirements or GPU guarantee; check offer first | Can host a Java service |
| [Quantum Rings](https://www.quantumrings.com/) | 200-qubit SDK license for one year | Event promo in packet; SDK uses Python 3.11+. Simulation still constrained by available hardware | Python SDK, not a Java-first route |
| [Base44](https://base44.com/) | Free account + event access code | Code on Discord; packet does not specify credit amount | App builder rather than Java implementation |
| [SSOJet](https://ssojet.com/) | Free plan | Signup; check plan limits | Authentication, not compute/inference |
| [MongoDB Atlas](https://mlh.link/mongodb) | Free forever tier without card, or $50 student credit | Student offer eligibility applies | Java database driver |
| [Tiger Data](https://mlh.link/tigerdata) | Free-tier cloud instances | Verify current offer and limits | PostgreSQL JDBC |

OpenAI sponsors the AI track, but the packet does **not** promise free OpenAI API credits. Ask the help desk if there is an additional event offer. No accounts, paid plans or resources have been provisioned.

## General free inference (separate from event offers)

- [Gemini API pricing](https://ai.google.dev/gemini-api/docs/pricing): free input/output on selected models, with model-specific rate limits. [Billing documentation](https://ai.google.dev/gemini-api/docs/billing). Use the account's actual model availability and limits; not every model is free. REST works from Java. Gemini is also an MLH prize category.
- [Groq free-plan limits](https://console.groq.com/docs/rate-limits): hosted inference with model-specific quotas. Current table includes GPT-OSS 20B/120B at 30 requests/minute, 1,000/day and 8,000 tokens/minute; account limits are authoritative. REST works from Java. Groq is not listed as an event sponsor.

## Data sources

| Subject | Source | Access and scope |
| --- | --- | --- |
| Space / Earth | [NASA APIs](https://api.nasa.gov/), [Earthdata Search](https://search.earthdata.nasa.gov/), [Worldview](https://worldview.earthdata.nasa.gov/) | Packet lists APOD, asteroids, space weather, fires/storms, Landsat and MODIS. NASA API key is free; Earthdata products may require separate free registration. Product formats and access vary. |
| Astronomy | [NASA Exoplanet Archive](https://exoplanetarchive.ipac.caltech.edu/), [MAST](https://mast.stsci.edu/) | Exoplanet and telescope data; packet lists Hubble, JWST and TESS. Prefer a small subset for a demo. |
| Brain signals | [g.tec hackathon datasets](https://github.com/unicorn-bi/Hackathon) | Packet lists seven datasets: P300 Speller, SSVEP, Stroke Rehab, ECoG Hand Pose, ECoG Video, Locked In, and Unresponsive Wakefulness Syndrome. Data Science section; packet restricts use to Hack the Nest projects. Hardware available at booth with waiver. |
| Simulated brain signals | [PiEEG](https://pieeg.com/) | Packet advertises PiEEG Cloud simulated signals and loaner Octopus 16 boards; ask booth for current endpoint/access. |
| Bioinformatics | Code of Life resource guide / Seqera workspace | Packet lists 12 datasets including single-cell RNA, ClinVar, Synthea synthetic patient records, TCGA and DepMap. Ask organizers for exact dataset links; access conditions vary. Research/education only, no real patient data or medical advice. |
| Weather / air / flood | [Open-Meteo](https://open-meteo.com/en/docs), [free-tier terms and pricing](https://open-meteo.com/en/pricing) | General resource, not an event sponsor. No API key for free noncommercial access; 600 calls/minute, 5,000/hour, 10,000/day and 300,000/month. Weather, air quality, flood and other APIs. |
| Earth AI models | [NASA/IBM Prithvi](https://huggingface.co/ibm-nasa-geospatial) | Packet points to open Earth/weather models. Downloadable models are not hosted inference credits; check each model's requirements and license. |

Most web APIs can be called from Java over HTTP. Downloaded scientific datasets may need format-specific libraries or preprocessing. A free dataset does not include free compute automatically.

## Submission details to keep handy

From the supplied packet (schedule changes may appear on Discord): draft due midnight Saturday night; final due noon Sunday, November 15, Eastern. Final submission requires public GitHub repo, video of three minutes or less, track selections, and AI-tool disclosure. Keep this repository private until the owner requests otherwise.
