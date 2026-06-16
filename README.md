# UnlockTechTreeMod — v2.0 (Mindustry v8)

A Mindustry Java mod that instantly unlocks all tech-tree research for **Serpulo** and **Erekir**.

> Port of [raffel080108's Tech Tree Unlocker](https://github.com/raffel080108/UnlockTechTreeMod) updated to target **Mindustry v8 (build 158+)**.

---

## Features

| Feature | Description |
|---|---|
| **Unlock All** | Unlock every research node for both Serpulo and Erekir at once. |
| **Per-planet unlock** | Unlock only Serpulo or only Erekir separately. |
| **Reset / Lock** | Roll back all research (with confirmation dialog). Per-planet resets available too. |
| **Auto-unlock toggle** | Enable in Settings → Unlock Tech Tree. Fires whenever a campaign world loads. |
| **Safe to re-run** | Already-unlocked nodes are skipped — running it twice does nothing. |

---

## Requirements

- Mindustry **build 158** or newer
- Desktop or Android

---

## Installation

### Import from URL (recommended)

In Mindustry: **Mods → Import mod → Import from URL**, paste:

```
https://github.com/ArcticHor1zon/TechTreeUnlockerV8/archive/refs/heads/master.zip
```

### Manual

Download the latest `.jar` from [Releases](https://github.com/ArcticHor1zon/TechTreeUnlockerV8/releases) and place it in your Mindustry `mods/` folder.

---

## Usage

**Main menu → Settings → scroll to "Unlock Tech Tree".**

- Checkbox to **auto-unlock on every campaign load**.
- Buttons to unlock or reset research per planet or all at once.

---

## Building from Source

Requires **JDK 17** and a working internet connection (dependencies are pulled from GitHub releases).

```bash
# Desktop JAR
./gradlew jar

# Android JAR (requires ANDROID_HOME set)
./gradlew jarAndroid

# Combined Desktop + Android release JAR
./gradlew deploy
```

Output is placed in `build/libs/`.

---

## Changelog

### v2.0
- Updated `minGameVersion` to **158** (Mindustry v8).
- Switched to **JDK 17** / Java 17 target.
- Added **per-planet unlock**: Serpulo-only or Erekir-only.
- Added **lock / reset** with per-planet support and confirmation dialog.
- Added **auto-unlock toggle** in the Settings panel.
- Added **in-settings action buttons** (no need to open the console).
- Improved safety: already-unlocked nodes are skipped silently.
- GitHub Actions CI workflow.

### v1.0
- Initial release for Mindustry v7 (build 140), by raffel080108.

---

## Credits

Original mod by [raffel080108](https://github.com/raffel080108). v2.0 port by [ArcticHor1zon](https://github.com/ArcticHor1zon).

## License

MIT — see [LICENSE](LICENSE).
