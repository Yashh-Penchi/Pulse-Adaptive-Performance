# Pulse Adaptive Performance

Client-side adaptive performance mod for Minecraft Fabric.

**Currently supporting:** 1.21.11

This is still early in development. Right now it only has the basic foundation - config screen, keybinds, and logging. The actual adaptive performance system is being built next.

---

### What it will do

Pulse watches your FPS in real-time and automatically adjusts settings like render distance, entity distance, and particles to keep the game running smoothly. When performance recovers, it puts your original settings back.

---

### Requirements

- Minecraft 1.21.11
- Fabric Loader
- Fabric API
- Cloth Config
- Mod Menu (optional, for the config button)

### Project Structure

net.yashhlabs.pulse
├── PulseClient.java
├── config/
├── adaptive/      ← coming soon
├── hud/           ← coming soon
├── keybind/
└── util/

---

### Status

 Project setup
 Config system
 Mod Menu support
 Adaptive system
 HUD
 Settings application

 ---

Building
```bash
./gradlew build
```

To run the client:
```bash
./gradlew runClient
```
 </br>

#### Made by Yashh-Penchi...

Email: Hello@yashh.app | [Discord Primary](https://discord.com/users/1487701049639960637) | [Discord Secondary](https://discord.com/users/785000257984659476) | 
[Instagram](https://www.instagram.com/yashh.penchii)