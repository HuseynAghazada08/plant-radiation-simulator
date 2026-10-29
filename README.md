# Plant Radiation Simulator

A Java console simulation of plant survival under changing radiation conditions. Three fictional species respond differently to alpha radiation, delta radiation, and days without radiation. The surviving plants collectively determine the next day's conditions.

Originally developed as a programming techniques coursework project, this repository presents the simulation as a standalone Java application.

## Features

- Three plant species modeled with inheritance and polymorphism.
- Daily nutrient updates, survival tracking, and adaptive radiation selection.
- Validated file input with descriptive messages for malformed data.
- Daily status output and a final list of surviving plants.
- Dependency-free behavior checks and GitHub Actions verification.

## Quick start

Requires a **JDK 17 or later**. No external dependencies or IDE are required.
Run the following commands from the repository root:

```sh
javac -d out src/plantsim/*.java
java -cp out plantsim.PlantSimulation examples/input.txt
```

To enter the input path interactively:

```sh
java -cp out plantsim.PlantSimulation
```

## Input format

The first line contains the number of plants. Each plant occupies one line with a whitespace-free name, a species code, and its starting nutrient level. The final line contains the simulation length in days.

```text
4
Piggy p 7
Slender d 5
Dumpy b 4
Willowy d 3
10
```

| Code | Species |
| --- | --- |
| `p` | Puffs |
| `d` | Deltatree |
| `b` | Parabush |

Plant count, starting nutrients, and day count must be positive integers. Plant entries must contain exactly three fields. Nonblank content after the day count is rejected.

## Simulation rules

The simulation begins with no radiation. Each day applies the current radiation to living plants and then sums the radiation needs of that day's survivors.

| Species | Alpha nutrient change | Delta nutrient change | No radiation |
| --- | --- | --- | --- |
| Puffs | +2 | −2 | −1 |
| Deltatree | −3 | +4 | −1 |
| Parabush | +1 | +1 | −1 |

All species die when nutrients reach zero or below. Puffs also die above 10 nutrients, including at initialization. Dead plants remain in the daily output but receive no further updates and do not influence radiation.

Living Puffs request `10 − nutrients` units of alpha radiation. Living Deltatrees request 4 units of delta radiation below 5 nutrients, 1 unit from 5 through 10, and 0 above 10. Parabushes request neither type.

- Alpha radiation is selected for the next day when total alpha need exceeds total delta need by at least 3.
- Delta radiation is selected when total delta need exceeds total alpha need by at least 3.
- Otherwise, the next day has no radiation.

## Checks

```sh
javac -Xlint:all -d out src/plantsim/*.java test/plantsim/*.java
java -cp out plantsim.TestPlants
```

The check runner covers radiation effects, demand thresholds, and survival boundaries for all three species. A failed check throws an assertion error and returns a failing process status. GitHub Actions runs these checks and a sample simulation on pushes and pull requests.

The `examples/` directory also contains scenarios for alpha, delta, and no radiation; dead plants; no survivors; and invalid input. For example:

```sh
java -cp out plantsim.PlantSimulation examples/invalid-type.txt
```

## Project structure

| Path | Purpose |
| --- | --- |
| `src/plantsim/` | Simulation entry point and domain classes |
| `test/plantsim/TestPlants.java` | Plant behavior checks |
| `examples/` | Sample inputs and validation scenarios |
| `.github/workflows/ci.yml` | Automated compilation and checks |

`PlantSimulation` reads input, `Planet` coordinates daily updates, and the abstract `Plant` class defines the shared interface implemented by each species. `Radiation` enumerates the available conditions.

## Scope

This is a deterministic educational model using fictional species and simplified rules. It is not a biological or radiation forecasting tool. The console application reports input errors as messages; these errors do not currently set a failing process exit code.
