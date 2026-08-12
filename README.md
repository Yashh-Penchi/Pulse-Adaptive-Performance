# Pulse Adaptive Performance

Client-side foundation mod for Minecraft 1.21.11 (Fabric). Mod ID: `pulse`.

This is a clean starting point only — **no adaptive performance logic is
implemented yet**. It exists to compile, launch, and give you a working
config screen, keybind, and logger to build the real system on top of.

## Toolchain (verified Aug 2026)

| Component     | Version              |
|----------------|----------------------|
| Minecraft      | 1.21.11               |
| Mappings       | Yarn `1.21.11+build.4` |
| Fabric Loader  | `0.18.4`              |
| Fabric Loom    | `1.14.10`              |
| Gradle         | `9.2.1` (required by Loom 1.14.10's published plugin metadata) |
| Fabric API     | `0.141.6+1.21.11`     |
| Cloth Config   | `21.11.153+fabric`    |
| Mod Menu       | `17.0.0`              |
| Java           | 21 (compiles fine on a Java 24 JDK running Gradle)      |

Note: Yarn mappings are still supported for 1.21.11 but are being phased
out in favor of Mojang's official mappings starting with Minecraft 26.1.
This project intentionally stays on Yarn per the target version.

## Getting started

1. Make sure you have a JDK 21 installed.
2. Open the project in IntelliJ IDEA (recommended) or run:
   ```
   ./gradlew build
   ```
   The first build will download Minecraft, remap it with Yarn, and set up
   the dev environment — this can take a few minutes.

   **Note:** this project ships without a pre-built `gradle-wrapper.jar`
   (it couldn't be fetched in this environment). Before the first build,
   run `gradle wrapper` once with any local Gradle 9.2+ install, or just
   open the folder in IntelliJ IDEA — it will generate the wrapper for you
   automatically via the Gradle import.
3. Launch the client in-game with:
   ```
   ./gradlew runClient
   ```

## Project layout

```
src/main/java/net/yashhlabs/pulse/
├── PulseClient.java   # client entrypoint — wires up config, keybinds, logging
├── config/            # Cloth Config-based client config (PulseConfig)
├── hud/                # empty — reserved for future HUD rendering
├── adaptive/            # empty — reserved for future adaptive performance logic
├── util/                # PulseLogger
└── keybind/            # PulseKeybinds — registered, does nothing yet
```

## What's included

- `fabric.mod.json` with `"environment": "client"` — this mod will refuse
  to load on a dedicated server.
- A Cloth Config-backed config class (`PulseConfig`) with AutoConfig
  registration, exposed via GSON to `config/pulse.json`.
- Explicit Mod Menu integration (`PulseModMenuIntegration`) that opens the
  Cloth Config screen from Mod Menu's "config" button. This only loads if
  Mod Menu is actually installed — it does nothing on its own.
- One placeholder keybinding (`key.pulse.open_hud`), unbound by default.
- A tagged SLF4J logger wrapper (`PulseLogger`).

## What's deliberately NOT included

No adaptive performance systems, no HUD rendering, no mixins. Those come
next, once this foundation is confirmed working in-game.
