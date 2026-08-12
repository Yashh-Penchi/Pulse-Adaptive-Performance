# Pulse Adaptive Performance

Client-side adaptive performance mod for Minecraft Fabric.

**Currently supporting:** 1.21.11

This is still early in development. Right now it only has the basic foundation - config screen, keybinds, and logging. The actual adaptive performance system is being built next.

### What it will do

Pulse watches your FPS in real-time and automatically adjusts settings like render distance, entity distance, and particles to keep the game running smoothly. When performance recovers, it puts your original settings back.

The goal is to give you higher average FPS without having to constantly tweak video settings yourself.

### Requirements

- Minecraft 1.21.11
- Fabric Loader
- Fabric API
- Cloth Config
- Mod Menu (optional, for easy config access)

### Building

```bash
./gradlew build
```

To run the development client:

```bash
./gradlew runClient
```

### Project Structure

```
net.yashhlabs.pulse
├── PulseClient.java
├── config/
├── adaptive/      ← coming soon
├── hud/           ← coming soon
├── keybind/
└── util/
```

### Status

- [x] Project setup
- [x] Config system
- [x] Mod Menu support
- [ ] Adaptive system
- [ ] Settings application
- [ ] HUD

---

#### Made by [Yashh-Penchi](https://github.com/Yashh-Penchi)

Email: Hello@yashh.app  
[Discord](https://discord.com/users/1487701049639960637) · [Instagram](https://www.instagram.com/yashh.penchii)
