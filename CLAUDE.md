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

## Dependencies and license
- Never invent library names or versions. Take coordinates from the library's official README or Maven Central and show the source.
- Download library: io.github.junkfood02.youtubedl-android (modules library and ffmpeg) from Maven Central. It is GPL-3.0, so this app is GPL-3.0.
- No Android Studio on this machine. Command-line Gradle only. Targets: minSdk 24, targetSdk = newest installed platform.
- Start with modules app, downloader and verify only. No Room, no Hilt in v1.

## Verified build versions (Nov 2026, from the official AGP 9.4.0 release notes)
- Android Gradle Plugin 9.4.0 needs Gradle 9.6.0, JDK 17+, SDK Build Tools 36.0.0, supports up to API 37.
- Make the wrapper: /home/eclipse/tools/gradle-8.10/bin/gradle wrapper --gradle-version 9.6.0
- Before writing build files, read https://developer.android.com/build/releases/agp-9-4-0-release-notes and the AGP 9 migration notes (Kotlin plugin setup changed). Do not guess.
- Use Kotlin DSL (.kts), plugins blocks and a version catalog (gradle/libs.versions.toml).
- gradle.properties: org.gradle.jvmargs=-Xmx1024m -Dfile.encoding=UTF-8, org.gradle.workers.max=2, kotlin.daemon.jvmargs=-Xmx768m, org.gradle.caching=true. Never 2048m (machine has 3.7 GB RAM).
- Download library: io.github.junkfood02.youtubedl-android:library and :ffmpeg, version 0.18.1, Maven Central.
- After writing any version number or dependency, print the file with cat so the numbers can be checked.

## Build baseline (working)
- Project was created with `android create` (official template), then upgraded to AGP 9.4.0 and Gradle 9.6.0 (wrapper checksum pinned and checked against services.gradle.org). compileSdk 37, minSdk 24.
- Do not change plugin, Gradle, Kotlin or SDK versions unless a build error requires it. Say why and show the file after changing.
- Work in small steps. After each step run ./gradlew assembleDebug, install with adb, and stop for me to check on the phone.
- Do not rewrite build files wholesale. Edit only the lines needed.
