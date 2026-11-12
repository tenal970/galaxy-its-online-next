---
name: media-safety-verify
description: Safety rules for downloaded and converted files and for app privacy. Use when touching downloading, converting, file saving, permissions, networking or dependencies.
---
# Why this app exists
Websites that download videos often bundle malware. This app must never become one.

## File verification (before saving or opening any result)
Check the first bytes, not just the extension.
- MP4/M4A/MOV: bytes 4 to 8 are "ftyp"
- WebM/MKV: 1A 45 DF A3
- MP3: "ID3" or FF FB / FF F3 / FF F2
- PNG: 89 50 4E 47. JPEG: FF D8 FF. PDF: "%PDF"
Reject and delete, and write the reason to the log, if the file is:
- ELF (7F 45 4C 46), DEX ("dex\n"), Windows PE ("MZ"), a script ("#!")
- a ZIP/APK ("PK") when the expected type is audio or video
- a type that does not match what the user asked for

## Privacy and network
- No ads, no analytics, no crash-reporting SDKs, no tracking.
- Network only for downloading media and updating yt-dlp.
- Request the fewest Android permissions possible. Never request SMS, contacts, location or accessibility.
- Never run or install a file the app downloaded.
- Add a dependency only after checking it is popular, maintained and needed; pin exact versions.
- Never log or store the user's links outside the user-visible log, and only if "Save log" is on.
