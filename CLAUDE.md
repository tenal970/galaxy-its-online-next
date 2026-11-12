# Galaxy it’s online next

Android app (first), desktop later. Downloads video/audio from YouTube,
TikTok, Facebook, Instagram, X and more, and converts files.
Reason it exists: web downloaders ship malware. This app must stay safe.
This is a fresh project built from scratch.

## Stack
- Kotlin + Jetpack Compose (UI and app logic)
- yt-dlp + ffmpeg via the youtubedl-android library
- Rust engine later (UniFFI), only for file verification and download manager

## Rules
- No ads, no trackers, no analytics. Network only for downloading.
- Verify downloaded file type (magic bytes). Reject executables and scripts.
- UI: glass style, text only (no emoji), user-changeable font, wallpaper, accent, dark/light.
- Thai and English UI strings live in resources, never hard-coded.
- Keep code simple and split into modules so others can extend it.

## Commands
- Build: ./gradlew assembleDebug
- Install: adb install -r app/build/outputs/apk/debug/app-debug.apk
- Logs: adb logcat --pid=$(adb shell pidof -s <applicationId>)
- Low RAM machine (3.7 GB): keep Gradle JVM small in gradle.properties.

## Release
- v1 is download only. File converter comes in a later update.
- Distribute through GitHub Releases only.
