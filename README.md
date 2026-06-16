# UnlockTechTreeMod — v2.0 (Mindustry v8)

A Mindustry Java mod that instantly unlocks all tech-tree research for **Serpulo** and **Erekir**.

> Updated from raffel080108's original v7 release to target **Mindustry v8 (build 147+)**.

---

## Features

| Feature | Description |
|---|---|
| **Unlock All** | Unlock every research node for both Serpulo and Erekir at once. |
| **Per-planet unlock** | Unlock only Serpulo or only Erekir separately. |
| **Reset / Lock** | Roll back all research (with confirmation dialog). Per-planet resets available too. |
| **Auto-unlock toggle** | Enable in Settings → Unlock Tech Tree. Fires whenever a campaign world loads. |
| **Client commands** | `/unlockall [planet]` and `/lockall [planet]` usable from the in-game console. |
| **Safe to re-run** | Already-unlocked nodes are skipped — running it twice does nothing. |

---

## Installation

### Option A — Import from URL (recommended)
In Mindustry: **Mods → Import mod → Import from URL**, paste:
```
https://github.com/YOUR_FORK/UnlockTechTreeMod/archive/refs/heads/main.zip
```

### Option B — Manual
1. Download `UnlockTechTreeMod.jar` from [Releases](../../releases).
2. Move it to your Mindustry mods folder:
   - **Windows**: `%AppData%/Mindustry/mods/`
   - **Linux**: `~/.local/share/Mindustry/mods/`
   - **Android**: `Android/data/io.anuke.mindustry/files/mods/`
3. Restart Mindustry.

---

## Usage

### Settings panel
**Main menu → Settings → scroll to "Unlock Tech Tree".**

You'll find:
- A checkbox to **auto-unlock on every campaign load**.
- Buttons to unlock or reset research per planet.

### Console commands
Open the console with `/` (PC) or the console button (Android):

```
/unlockall             — unlock everything (Serpulo + Erekir)
/unlockall serpulo     — unlock Serpulo only
/unlockall erekir      — unlock Erekir only

/lockall               — reset everything
/lockall serpulo       — reset Serpulo only
/lockall erekir        — reset Erekir only
```

---

## Building from Source

**Requirements**: JDK 17, internet access (to download Mindustry v147 via JitPack).

```bash
# Clone
git clone https://github.com/YOUR_FORK/UnlockTechTreeMod.git
cd UnlockTechTreeMod

# Desktop-only JAR (for testing)
./gradlew jar
# Output: build/libs/UnlockTechTreeMod.jar
```

For an **Android-compatible** JAR, set up the Android SDK and run:
```bash
./gradlew deploy
```

Or push to GitHub and let the Actions workflow build it for you.

---

## Targeting a Newer Build

Edit `build.gradle`:
```groovy
ext {
    mindustryVersion = 'v150'   // or 'v157', etc.
}
```
And `mod.hjson`:
```hjson
minGameVersion: 150
```

---

## Changelog

### v2.0
- Updated `minGameVersion` to **147** (Mindustry v8).
- Switched to **JDK 17** / Java 17 target.
- Added **per-planet unlock**: Serpulo-only or Erekir-only.
- Added **lock / reset** with per-planet support and confirmation dialog.
- Added **auto-unlock toggle** in the Settings panel.
- Added **in-settings action buttons** (no need to open the console).
- Added `/unlockall` and `/lockall` client commands with optional planet argument.
- Improved safety: already-unlocked nodes are skipped silently.
- GitHub Actions CI workflow.

### v1.0
- Initial release for Mindustry v7 (build 140).

---

## License

MIT — see [LICENSE.md](LICENSE.md).
