---
name: release-github
description: Prepare and publish a new app version on GitHub Releases. Use when bumping the version, writing a changelog, signing an APK or publishing.
---
# Release checklist

1. Make sure main is clean and tests pass: ./gradlew test
2. Bump versionName and versionCode in app/build.gradle(.kts).
3. Update CHANGELOG.md (what is new, what is fixed, in plain words).
4. Run /code-review, then the media-safety-verify checks.
5. Build a signed release APK: ./gradlew assembleRelease
   The keystore and its passwords stay OUT of git (*.jks and keystore.properties are in .gitignore).
6. Install the release APK on the phone and test: paste a link, download MP3 and MP4, change font and wallpaper, open Settings.
7. Tag: git tag vX.Y.Z and push the tag.
8. Create the GitHub Release, attach the APK, and add its SHA-256 (sha256sum app-release.apk).

## README must say
- No ads, no tracking, no data collected.
- Download the APK from the GitHub Releases page only. Copies from other sites may contain malware.
