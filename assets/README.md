# Assets

## Icon Generation

The app icon is generated via Python/Pillow scripts in `scripts/`. The canonical face geometry lives in `scripts/face_icon.py` — edit constants there, regenerate the PNG, then sync to the Android vector XML.

### Setup

```bash
python3 -m venv .venv
.venv/bin/pip install Pillow
```

### Generate Play Store Icon (512x512)

```bash
.venv/bin/python scripts/generate_app_icons.py
```

Output: `app/src/main/play/listings/en-US/graphics/icon/icon.png`

### Sync to Android Vector XML

After tuning constants in `scripts/face_icon.py`, update the mouth `pathData` in `app/src/main/res/drawable/ic_launcher_foreground.xml` to match. The `generate_foreground_xml()` function can print the full XML.

### Generate Feature Graphic (1024x500)

```bash
.venv/bin/python scripts/generate_feature_graphic.py
```

Output: `app/src/main/play/listings/en-US/graphics/feature-graphic/feature_graphic.png`

### Screenshots

- `screenshot-wheel-default.png` — default wheel state
- `screenshot-wheel-selected.png` — wheel with emotion selected
