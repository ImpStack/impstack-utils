package org.impstack.jme.math;

import com.jme3.math.ColorRGBA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColorHelper {

    private static final Logger LOG = LoggerFactory.getLogger(ColorHelper.class);

    public static ColorRGBA fromHex(String hex) {
        return fromHex(hex, false);
    }

    /**
     * Returns the hexadecimal representation of the supplied color object
     * @param color
     * @return hexadecimal string representation of the color
     */
    public static String toHex(ColorRGBA color) {
        return "#" + leftPad(Integer.toHexString((int) (color.r * 255))).toUpperCase() + leftPad(Integer.toHexString((int) (color.g * 255))).toUpperCase() + leftPad(Integer.toHexString((int) (color.b * 255))).toUpperCase() + leftPad(Integer.toHexString((int) (color.a * 255))).toUpperCase();
    }

    /**
     * Returns a ColorRGBA object from the passed hex string. When the color is not in linear space, the values will
     * be gamma corrected to be stored in linear space. GAMMA value is 2.2
     * When the color is picked from a color picker or GUI, the linearSpace boolean should probably be set to false.
     *
     * @param hex: the hex string
     * @param linearSpace: if the color is in linear space
     * @return
     */
    public static ColorRGBA fromHex(String hex, boolean linearSpace) {
        String cleaned = clean(hex);
        if (cleaned.length() == 6) cleaned += "FF"; // add alpha
        if (cleaned.length() != 8) {
            LOG.error("Invalid hex code: '{}' specified!", hex);
            return null;
        }
        try {
            return linearSpace ? new ColorRGBA(Integer.valueOf(cleaned.substring(0, 2), 16) / 255.0f, Integer.valueOf(cleaned.substring(2, 4), 16) / 255.0f, Integer.valueOf(cleaned.substring(4, 6), 16) / 255.0f, Integer.valueOf(cleaned.substring(6), 16) / 255.0f) :
                    new ColorRGBA().setAsSrgb(Integer.valueOf(cleaned.substring(0, 2), 16) / 255.0f, Integer.valueOf(cleaned.substring(2, 4), 16) / 255.0f, Integer.valueOf(cleaned.substring(4, 6), 16) / 255.0f, Integer.valueOf(cleaned.substring(6), 16) / 255.0f);
        } catch (Exception e) {
            LOG.error("Invalid hex code: '{}' specified!", hex);
            return null;
        }
    }

    public static ColorRGBA fromRGBA(int red, int green, int blue, int alpa) {
        return fromRGBA(red, green, blue, alpa, false);
    }

    /**
     * Returns a ColorRGBA object from the passed red, green, blue, alpha values. When the color is not in linear space,
     * the values will be gamma corrected to be stored in linear space. GAMMA value is 2.2
     * When the color is picked from a color picker or GUI, the linearSpace boolean should probably be set to false.
     *
     * @param red
     * @param green
     * @param blue
     * @param alpha
     * @param linearSpace
     * @return
     */
    public static ColorRGBA fromRGBA(int red, int green, int blue, int alpha, boolean linearSpace) {
        if (red < 0 || red > 255) {
            LOG.error("Invalid red value: '{}' specified!", red);
            return null;
        }
        if (green < 0 || green > 255) {
            LOG.error("Invalid green value: '{}' specified!", green);
            return null;
        }
        if (blue < 0 || blue > 255) {
            LOG.error("Invalid blue value: '{}' specified!", blue);
            return null;
        }
        if (alpha < 0 || alpha > 255) {
            LOG.error("Invalid alpha value: '{}' specified!", alpha);
            return null;
        }
        try {
            return linearSpace ? new ColorRGBA(red / 255f, green / 255f, blue / 255f, alpha / 255f) :
                    new ColorRGBA().setAsSrgb(red / 255f, green / 255f, blue / 255f, alpha / 255f);
        } catch (Exception e) {
            LOG.error("Invalide RGBA value: '{}, {}, {}, {}' specified!", red, green, blue, alpha);
            return null;
        }
    }

    private static String clean(String s) {
        String string = s.trim();
        string = string.startsWith("#") ? string.substring(1) : string;
        return string;
    }

    private static String leftPad(String s) {
        return s.length() == 1 ? "0" + s : s;
    }
}
