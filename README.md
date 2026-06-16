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
| **Safe to re-run** | Already-unlocked nodes are skipped — running it twice does nothing. |

---

## Installation

### Import from Mod Browser (recommended)
In Mindustry: **Mods → Import mod → Import from URL**, paste:
```
https://github.com/YOUR_FORK/UnlockTechTreeMod/archive/refs/heads/main.zip
```
---

## Usage

### Settings panel
**Main menu → Settings → scroll to "Unlock Tech Tree".**

You'll find:
- A checkbox to **auto-unlock on every campaign load**.
- Buttons to unlock or reset research per planet.

---


---

## Changelog

### v2.0
- Updated `minGameVersion` to **147** (Mindustry v8).
- Switched to **JDK 17** / Java 17 target.
- Added **per-planet unlock**: Serpulo-only or Erekir-only.
- Added **lock / reset** with per-planet support and confirmation dialog.
- Added **auto-unlock toggle** in the Settings panel.
- Added **in-settings action buttons** (no need to open the console).
- Improved safety: already-unlocked nodes are skipped silently.
- GitHub Actions CI workflow.

### v1.0
- Initial release for Mindustry v7 (build 140).

---

## License

MIT — see [LICENSE.md](LICENSE.md).
