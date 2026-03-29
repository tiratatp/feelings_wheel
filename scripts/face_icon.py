"""Face icon drawing code and vector XML generation.

Single source of truth for icon geometry. Provides:
- Pillow drawing functions for raster PNG rendering
- Vector XML generation for Android adaptive icon foreground
- Shared constants, bezier utilities, and masking functions
"""

from PIL import Image, ImageDraw

# --- Colors ---
SKY_BLUE = (56, 189, 248)  # #38BDF8
FACE_COLOR = (51, 51, 51)  # #333333

# --- Viewport ---
VIEWPORT = 108
SAFE_ZONE = 72
SAFE_OFFSET = (VIEWPORT - SAFE_ZONE) // 2
RENDER_SCALE = 4

# --- Face geometry (viewport coordinates) ---
# Eyes: (cx, cy, radius)
LEFT_EYE = (42, 46.5, 2.5)
RIGHT_EYE = (64, 50.5, 2.5)

# Mouth: quadratic bezier (start, control, end)
MOUTH_START = (30, 62)
MOUTH_CONTROL = (52, 66)
MOUTH_END = (78, 58)
MOUTH_STROKE = 2.5


# --- Bezier utilities ---

def quadratic_bezier(p0, p1, p2, steps=64):
    """Evaluate a quadratic bezier curve, returning a list of (x, y) points."""
    points = []
    for i in range(steps + 1):
        t = i / steps
        u = 1 - t
        x = u**2 * p0[0] + 2 * u * t * p1[0] + t**2 * p2[0]
        y = u**2 * p0[1] + 2 * u * t * p1[1] + t**2 * p2[1]
        points.append((x, y))
    return points


# --- Pillow drawing functions ---

def draw_face_icon(bg_color=SKY_BLUE):
    """Render the face adaptive icon at high res.

    Args:
        bg_color: Background color tuple (R, G, B), or None for transparent.
    """
    size = VIEWPORT * RENDER_SCALE
    bg = bg_color + (255,) if bg_color else (0, 0, 0, 0)
    img = Image.new("RGBA", (size, size), bg)
    draw = ImageDraw.Draw(img, "RGBA")

    def s(val):
        return val * RENDER_SCALE

    fill = FACE_COLOR + (255,)

    # Left eye
    cx, cy, r = s(LEFT_EYE[0]), s(LEFT_EYE[1]), s(LEFT_EYE[2])
    draw.ellipse([cx - r, cy - r, cx + r, cy + r], fill=fill)

    # Right eye
    cx, cy, r = s(RIGHT_EYE[0]), s(RIGHT_EYE[1]), s(RIGHT_EYE[2])
    draw.ellipse([cx - r, cy - r, cx + r, cy + r], fill=fill)

    # Mouth (quadratic bezier)
    mouth_pts = quadratic_bezier(
        (s(MOUTH_START[0]), s(MOUTH_START[1])),
        (s(MOUTH_CONTROL[0]), s(MOUTH_CONTROL[1])),
        (s(MOUTH_END[0]), s(MOUTH_END[1])),
    )
    stroke_w = max(1, int(MOUTH_STROKE * RENDER_SCALE))
    draw.line(mouth_pts, fill=fill, width=stroke_w, joint="curve")

    return img


# --- Utility functions ---

def crop_safe_zone(img):
    """Crop the center 72/108 safe zone from the rendered adaptive icon."""
    size = img.width
    offset = int(SAFE_OFFSET / VIEWPORT * size)
    safe_size = int(SAFE_ZONE / VIEWPORT * size)
    return img.crop((offset, offset, offset + safe_size, offset + safe_size))


def apply_rounded_rect_mask(img, radius_fraction=0.18):
    """Apply a rounded rectangle mask for Play Store icon."""
    size = img.width
    radius = int(size * radius_fraction)
    mask = Image.new("L", (size, size), 0)
    draw = ImageDraw.Draw(mask)
    draw.rounded_rectangle([0, 0, size - 1, size - 1], radius=radius, fill=255)
    result = Image.new("RGBA", (size, size), (0, 0, 0, 0))
    result.paste(img, mask=mask)
    return result


def apply_circle_mask(img):
    """Apply a circular mask."""
    size = img.width
    mask = Image.new("L", (size, size), 0)
    draw = ImageDraw.Draw(mask)
    draw.ellipse([0, 0, size - 1, size - 1], fill=255)
    result = Image.new("RGBA", (size, size), (0, 0, 0, 0))
    result.paste(img, mask=mask)
    return result


# --- Vector XML generation ---

def _eye_path(cx, cy, r):
    """Generate SVG arc path data for a circle (two semicircular arcs)."""
    top_y = cy - r
    return f"M{cx},{top_y} A{r},{r} 0 1,1 {cx},{top_y + 2 * r} A{r},{r} 0 1,1 {cx},{top_y}z"


def generate_foreground_xml():
    """Generate Android vector drawable XML from the canonical constants."""
    left_cx, left_cy, left_r = LEFT_EYE
    right_cx, right_cy, right_r = RIGHT_EYE
    left_eye_path = _eye_path(left_cx, left_cy, left_r)
    right_eye_path = _eye_path(right_cx, right_cy, right_r)
    mx, my = MOUTH_START
    qx, qy = MOUTH_CONTROL
    ex, ey = MOUTH_END
    mouth_path = f"M{mx},{my} Q{qx},{qy} {ex},{ey}"

    return f"""<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
    <!-- Left eye -->
    <path
        android:fillColor="#333333"
        android:pathData="{left_eye_path}" />
    <!-- Right eye -->
    <path
        android:fillColor="#333333"
        android:pathData="{right_eye_path}" />
    <!-- Mouth -->
    <path
        android:strokeColor="#333333"
        android:strokeWidth="{MOUTH_STROKE}"
        android:strokeLineCap="round"
        android:fillColor="#00000000"
        android:pathData="{mouth_path}" />
</vector>
"""
