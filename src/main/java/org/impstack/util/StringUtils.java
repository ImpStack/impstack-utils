package org.impstack.util;

import org.apache.commons.text.WordUtils;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * String utility class
 *
 * @author remy
 * @since 26/07/17.
 */
public class StringUtils {

    /**
     * Converts a string to camelcase.
     * toCamelCase(null) : ""
     * toCamelCase("hello") : "hello"
     * toCamelCase("my name is Remy") : "myNameIsRemy"
     * toCamelCase("I am FINE") : "iAmFine"
     * toCamelCase("     "): ""
     * @param string
     * @return camelcase representation of the given string
     */
    public static String toCamelCase(String string) {
        String s = WordUtils.capitalizeFully(string);
        if (s != null) {
            s = s.replaceAll("\\s", "");
            return s.length() > 1 ? s.substring(0, 1).toLowerCase() + s.substring(1) : s.toLowerCase();
        }
        return "";
    }

    /**
     * Returns the given string, with a maximum of {@code max} characters.
     * Characters after the {@code max} will be stripped
     * @param string
     * @param max
     * @return
     */
    public static String max(String string, int max) {
        return string != null && string.length() > max ? string.substring(0, max) : string;
    }

    /**
     * Returns a concatenated string of all the objects in the collection
     * @param collection
     * @return
     */
    public static String toString(Collection collection) {
        return collection != null ? collection.stream().map(Object::toString).collect(Collectors.joining(",")).toString() : null;
    }

}
