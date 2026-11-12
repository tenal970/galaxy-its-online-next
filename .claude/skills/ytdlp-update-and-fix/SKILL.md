---
name: ytdlp-update-and-fix
description: Update yt-dlp and fix downloads that stop working after a site changes. Use when extraction fails, a platform returns errors, or before a release.
---
# Fixing broken downloads

1. Read the exact error. Show a readable message to the user and write it to the log. Never fail silently.
2. Update yt-dlp using the update function of the youtubedl-android library (see its README for the current call). Update at app start when online and in a Settings button.
3. Retest with public links:
   - https://www.youtube.com/watch?v=jNQXAC9IVRw (first YouTube video, short)
   - Add other short public or Creative Commons test links here as they are found, one per supported site.
4. If it still fails, search the yt-dlp GitHub issues for the same error and try the suggested fix.
5. Do not add ways to bypass DRM, paywalls or logins. The app is for content the user is allowed to save.

## Done means
A short public link from each supported site downloads as MP4 and as MP3, passes media-safety-verify, and the log shows no errors.
