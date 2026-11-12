---
name: rust-bridge
description: Add and maintain the Rust engine (file verification first, download manager later) and connect it to Kotlin. Use when creating or changing Rust code, UniFFI bindings or the native build.
---
# Rust engine

Order of work: (1) file verification, (2) download manager only if really needed. yt-dlp and ffmpeg stay as they are.

## Setup
rustup target add aarch64-linux-android
cargo install cargo-ndk
The Android NDK is required: install it with sdkmanager (newest stable "ndk;..." from sdkmanager --list) and set ANDROID_NDK_HOME.

## Layout
engine/ is a Rust library crate (crate-type cdylib and lib). Keep it small, with few dependencies.

## Bridge
Use UniFFI to generate Kotlin bindings. Do not write JNI by hand.
Build for the phone: cargo ndk -t arm64-v8a -o ../app/src/main/jniLibs build --release

## Rules
- Define the Kotlin-facing interface first (for example verifyFile(path, expectedType) returns a result).
- Keep a pure Kotlin fallback behind the same interface until Rust is proven.
- Write cargo tests with sample byte headers for every file type in media-safety-verify.
- Run cargo clippy and cargo test before every commit.
