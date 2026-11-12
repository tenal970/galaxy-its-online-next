---
name: glass-ui-design
description: Visual rules for the Galaxy it’s online next UI (glass style, text only, user-chosen font, wallpaper and accent). Use whenever creating or changing any screen, theme or component.
---
# Glass UI rules

## Look
- Text only. No emoji, no icon packs. Buttons and links are words.
- Dark gradient wallpaper by default, frosted translucent cards on top.
- Card: white fill at 5 to 35 percent alpha (more alpha in light mode), 1px border of white at 20 percent, corner radius 26 (cards), 16 (inputs), pill (buttons and chips).
- One accent color only (default #8b7cff) for the main button, progress bar and selected chips. Everything else is white or grey.
- One font family at a time, light to medium weights, generous spacing.
- Screens: Home (one link box), options sheet (MP3/MP4 plus quality in one step), progress (big percentage, expandable log), Settings.

## User customization (must work without code changes)
- Font: user imports a .ttf/.otf/.woff2 file from the device; copy it into app-private storage; load it as the app font; fall back to a Thai-capable font for missing glyphs. Offer a Remove button.
- Wallpaper: a visible Wallpaper button on Home and in Settings opens the system picker; copy the image into app-private storage; offer Remove.
- Accent color and Dark/Light tone, plus a glass strength slider.
- Persist every choice.

## Compose notes
- Modifier.blur blurs the composable itself, not what is behind it. Blur the wallpaper layer and draw translucent cards over it. Do not blur text.
- Blur needs Android 12 (API 31) or newer. Below that, use plain translucent cards.
