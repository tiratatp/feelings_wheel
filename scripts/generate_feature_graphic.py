#!/usr/bin/env python3
"""Generate 1024x500 Play Store feature graphic for the Feelings Wheel app.

Layout: left side has app icon + title + feature bullets,
right side has a portrait screenshot in a device mockup frame.

Usage:
    python scripts/generate_feature_graphic.py
"""

import os
import sys
from pathlib import Path

from PIL import Image, ImageDraw, ImageFont

from face_icon import SKY_BLUE, draw_face_icon, crop_safe_zone, apply_circle_mask

# Resolve paths relative to this script's directory
SCRIPT_DIR = Path(os.path.dirname(os.path.abspath(__file__)))
PROJECT_DIR = SCRIPT_DIR.parent

WIDTH, HEIGHT = 1024, 500
WHITE = (255, 255, 255)
NAVY_DARK = (8, 32, 56)
NAVY_LIGHT = (18, 58, 90)
BEZEL_COLOR = (40, 40, 40)
BEZEL_INNER = (20, 20, 20)


def lerp_color(c1, c2, t):
    """Linearly interpolate between two RGB colors."""
    return tuple(int(a + (b - a) * t) for a, b in zip(c1, c2))


def draw_gradient(img, c1, c2):
    """Draw a left-to-right horizontal gradient."""
    draw = ImageDraw.Draw(img)
    for x in range(img.width):
        t = x / (img.width - 1)
        color = lerp_color(c1, c2, t)
        draw.line([(x, 0), (x, img.height)], fill=color)


def draw_rounded_rect(draw, bbox, radius, fill=None, outline=None, width=1):
    """Draw a rounded rectangle (compatible with older Pillow versions)."""
    x0, y0, x1, y1 = bbox
    if fill:
        draw.rectangle([x0 + radius, y0, x1 - radius, y1], fill=fill)
        draw.rectangle([x0, y0 + radius, x1, y1 - radius], fill=fill)
        draw.pieslice([x0, y0, x0 + 2 * radius, y0 + 2 * radius], 180, 270, fill=fill)
        draw.pieslice(
            [x1 - 2 * radius, y0, x1, y0 + 2 * radius], 270, 360, fill=fill
        )
        draw.pieslice(
            [x0, y1 - 2 * radius, x0 + 2 * radius, y1], 90, 180, fill=fill
        )
        draw.pieslice(
            [x1 - 2 * radius, y1 - 2 * radius, x1, y1], 0, 90, fill=fill
        )
    if outline:
        draw.arc(
            [x0, y0, x0 + 2 * radius, y0 + 2 * radius],
            180, 270, fill=outline, width=width,
        )
        draw.arc(
            [x1 - 2 * radius, y0, x1, y0 + 2 * radius],
            270, 360, fill=outline, width=width,
        )
        draw.arc(
            [x0, y1 - 2 * radius, x0 + 2 * radius, y1],
            90, 180, fill=outline, width=width,
        )
        draw.arc(
            [x1 - 2 * radius, y1 - 2 * radius, x1, y1],
            0, 90, fill=outline, width=width,
        )
        draw.line(
            [(x0 + radius, y0), (x1 - radius, y0)], fill=outline, width=width
        )
        draw.line(
            [(x0 + radius, y1), (x1 - radius, y1)], fill=outline, width=width
        )
        draw.line(
            [(x0, y0 + radius), (x0, y1 - radius)], fill=outline, width=width
        )
        draw.line(
            [(x1, y0 + radius), (x1, y1 - radius)], fill=outline, width=width
        )


def load_font(path, size):
    """Try to load a font, falling back gracefully."""
    try:
        return ImageFont.truetype(path, size)
    except OSError:
        return None


def get_fonts():
    """Load Roboto fonts with fallbacks."""
    roboto_bold_paths = [
        "/Library/Fonts/Roboto-Bold.ttf",
        "/usr/share/fonts/truetype/roboto/Roboto-Bold.ttf",
        "/usr/share/fonts/truetype/roboto/hinted/Roboto-Bold.ttf",
    ]
    roboto_regular_paths = [
        "/Library/Fonts/Roboto-Regular.ttf",
        "/usr/share/fonts/truetype/roboto/Roboto-Regular.ttf",
        "/usr/share/fonts/truetype/roboto/hinted/Roboto-Regular.ttf",
    ]
    helvetica = "/System/Library/Fonts/Helvetica.ttc"

    title_font = None
    feature_font = None

    for path in roboto_bold_paths:
        title_font = load_font(path, 58)
        if title_font:
            break

    for path in roboto_regular_paths:
        feature_font = load_font(path, 24)
        if feature_font:
            break

    if not title_font:
        title_font = load_font(helvetica, 58) or ImageFont.load_default()
    if not feature_font:
        feature_font = load_font(helvetica, 24) or ImageFont.load_default()

    return title_font, feature_font


def get_app_icon(size):
    """Render the face icon with sky blue circle background for feature graphic use."""
    full_icon = draw_face_icon(bg_color=SKY_BLUE)
    safe_icon = crop_safe_zone(full_icon)
    safe_icon = apply_circle_mask(safe_icon)
    safe_icon = safe_icon.resize((size, size), Image.LANCZOS)
    return safe_icon


def generate_feature_graphic():
    """Generate the feature graphic."""
    # --- Background gradient ---
    img = Image.new("RGB", (WIDTH, HEIGHT))
    draw_gradient(img, NAVY_DARK, NAVY_LIGHT)

    draw = ImageDraw.Draw(img, "RGBA")
    title_font, feature_font = get_fonts()

    # --- Layout constants ---
    left_margin = 60
    right_section_x = 460

    # --- Measure title to size icon accordingly ---
    title_text = "Feelings Wheel"
    title_bbox = draw.textbbox((0, 0), title_text, font=title_font)
    title_h = title_bbox[3] - title_bbox[1]
    title_top_offset = title_bbox[1]

    icon_size = int(title_h * 1.125)
    icon_gap = 20
    row_y = 120

    # --- App icon ---
    row_mid = row_y + icon_size // 2
    icon = get_app_icon(icon_size)
    img.paste(icon, (left_margin, row_mid - icon_size // 2), icon)
    draw = ImageDraw.Draw(img, "RGBA")

    # --- App name ---
    title_x = left_margin + icon_size + icon_gap
    title_y = row_mid - title_h // 2 - title_top_offset
    draw.text((title_x, title_y), title_text, fill=WHITE, font=title_font)

    # --- Feature bullet points ---
    features = [
        "Interactive emotion wheel",
        "130+ feelings to explore",
        "Tap to identify emotions",
    ]
    bullet_y = row_y + icon_size + 40
    bullet_spacing = 42
    dot_radius = 5

    for i, feature in enumerate(features):
        y = bullet_y + i * bullet_spacing
        dot_cx = left_margin + dot_radius
        dot_cy = y + 12
        draw.ellipse(
            [dot_cx - dot_radius, dot_cy - dot_radius,
             dot_cx + dot_radius, dot_cy + dot_radius],
            fill=SKY_BLUE,
        )
        draw.text((left_margin + dot_radius * 2 + 10, y), feature, fill=SKY_BLUE, font=feature_font)

    # --- Device mockup with screenshot (right side) ---
    screenshot_path = PROJECT_DIR / "assets" / "screenshot-wheel-default.png"
    if not screenshot_path.exists():
        print(f"  Warning: No screenshot found at {screenshot_path}, skipping device mockup")
    else:
        screenshot = Image.open(screenshot_path).convert("RGB")

        bezel_thickness = 8
        corner_radius = 18
        # Portrait screenshot — fit height to canvas
        screen_h = HEIGHT - 40
        screen_w = int(screen_h * screenshot.width / screenshot.height)
        screenshot = screenshot.resize((screen_w, screen_h), Image.LANCZOS)

        device_w = screen_w + 2 * bezel_thickness
        device_h = screen_h + 2 * bezel_thickness
        device_x = right_section_x + (WIDTH - right_section_x - device_w) // 2
        device_y = (HEIGHT - device_h) // 2

        draw_rounded_rect(
            draw,
            [device_x, device_y, device_x + device_w, device_y + device_h],
            corner_radius, fill=BEZEL_COLOR,
        )
        draw_rounded_rect(
            draw,
            [device_x + 2, device_y + 2, device_x + device_w - 2, device_y + device_h - 2],
            corner_radius - 2, fill=BEZEL_INNER,
        )

        screen_x = device_x + bezel_thickness
        screen_y = device_y + bezel_thickness
        img.paste(screenshot, (screen_x, screen_y))

        draw = ImageDraw.Draw(img, "RGBA")
        draw_rounded_rect(
            draw,
            [device_x, device_y, device_x + device_w, device_y + device_h],
            corner_radius, outline=(255, 255, 255, 40), width=1,
        )

    # --- Save ---
    output_path = PROJECT_DIR / "app" / "src" / "main" / "play" / "listings" / "en-US" / "graphics" / "feature-graphic" / "feature_graphic.png"
    output_path.parent.mkdir(parents=True, exist_ok=True)
    img.save(output_path, "PNG")
    print(f"Saved {output_path} ({img.size[0]}x{img.size[1]})")


def main():
    generate_feature_graphic()


if __name__ == "__main__":
    sys.exit(main() or 0)
