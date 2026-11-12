---
name: android-build-test
description: Build, install and debug the Android app on a low-RAM Debian machine with a USB-connected phone. Use when building, running on the phone, reading crash logs, or when Gradle runs out of memory.
---
# Build and test

1. Find the package name: read applicationId in app/build.gradle(.kts).
2. Build: ./gradlew assembleDebug
3. Check the phone: adb devices (must say "device", not "unauthorized")
4. Install: adb install -r app/build/outputs/apk/debug/app-debug.apk
5. Start: adb shell monkey -p <applicationId> 1
6. Logs: adb logcat --pid=$(adb shell pidof -s <applicationId>)
   Crashes only: adb logcat -b crash

## Low RAM (3.7 GB machine)
Keep gradle.properties small:
org.gradle.jvmargs=-Xmx1024m -Dfile.encoding=UTF-8
org.gradle.workers.max=2
If the build is killed (exit 137) it ran out of RAM: lower -Xmx or add swap.
Do not run Android Studio and Gradle at the same time.

## Rules
- Fix the first error in the build output, not the last.
- After a change compiles, install it and open it on the phone before saying it works.
