---
name: i18n-th-en
description: Thai and English text rules for the app. Use when adding or changing any user-visible text, the language setting, or fonts that must show Thai.
---
# Thai and English

- Never hard-code user-visible text. Put it in res/values/strings.xml (English) and res/values-th/strings.xml (Thai).
- Every new string must be added in both files in the same change.
- A language setting in Settings lets the user pick Thai or English, independent of the phone language. Default: follow the phone.
- Keep sentences short and plain. Thai text should sound natural, not machine-translated.
- Do not build sentences by joining fragments; use format strings with placeholders (for example %1$s).
- Fonts: the app font and any user-imported font must still show Thai. If a glyph is missing, fall back to a Thai-capable font.
- Check that Thai text is not clipped: line height must be generous.
