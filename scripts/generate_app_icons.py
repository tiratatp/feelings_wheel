#!/usr/bin/env python3
"""Generate Play Store listing icon (512x512) for the Feelings Wheel app.

Uses face_icon.py as the single source of truth for face geometry.

Usage:
    python scripts/generate_app_icons.py
"""

import os
import sys

from PIL import Image

from face_icon import draw_face_icon, crop_safe_zone, apply_rounded_rect_mask

PLAY_STORE_SIZE = 512
OUTPUT_DIR = os.path.join(
    os.path.dirname(__file__), "..",
    "app", "src", "main", "play", "listings", "en-US", "graphics", "icon",
)


def main():
    # Render the full adaptive icon, crop safe zone, resize, mask
    full_icon = draw_face_icon()
    safe_icon = crop_safe_zone(full_icon)
    resized = safe_icon.resize((PLAY_STORE_SIZE, PLAY_STORE_SIZE), Image.LANCZOS)
    play_icon = apply_rounded_rect_mask(resized)

    # Save
    os.makedirs(OUTPUT_DIR, exist_ok=True)
    out_path = os.path.join(OUTPUT_DIR, "icon.png")
    play_icon.save(out_path, "PNG")
    print(f"Saved Play Store icon: {out_path}")


if __name__ == "__main__":
    sys.exit(main() or 0)
