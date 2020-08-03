package de.pr1meti.ls4pde.util;

/**
 * Parse
 */
public class Parse {

    static final public int parseInt(boolean what) {
        return what ? 1 : 0;
    }

    /**
     * Note that parseInt() will un-sign a signed byte value.
     */
    static final public int parseInt(byte what) {
        return what & 0xff;
    }

    /**
     * Note that parseInt('5') is unlike String in the sense that it won't return 5,
     * but the ascii value. This is because ((int) someChar) returns the ascii
     * value, and parseInt() is just longhand for the cast.
     */
    static final public int parseInt(char what) {
        return what;
    }

    /**
     * Same as floor(), or an (int) cast.
     */
    static final public int parseInt(float what) {
        return (int) what;
    }

    /**
     * Parse a String into an int value. Returns 0 if the value is bad.
     */
    static final public int parseInt(String what) {
        return parseInt(what, 0);
    }

    /**
     * Parse a String to an int, and provide an alternate value that should be used
     * when the number is invalid.
     */
    static final public int parseInt(String what, int otherwise) {
        try {
            int offset = what.indexOf('.');
            if (offset == -1) {
                return Integer.parseInt(what);
            } else {
                return Integer.parseInt(what.substring(0, offset));
            }
        } catch (NumberFormatException e) {
        }
        return otherwise;
    }

    // . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

    static final public int[] parseInt(boolean[] what) {
        int[] list = new int[what.length];
        for (int i = 0; i < what.length; i++) {
            list[i] = what[i] ? 1 : 0;
        }
        return list;
    }

    static final public int[] parseInt(byte[] what) { // note this unsigns
        int[] list = new int[what.length];
        for (int i = 0; i < what.length; i++) {
            list[i] = (what[i] & 0xff);
        }
        return list;
    }

    static final public int[] parseInt(char[] what) {
        int[] list = new int[what.length];
        for (int i = 0; i < what.length; i++) {
            list[i] = what[i];
        }
        return list;
    }

    static public int[] parseInt(float[] what) {
        int[] inties = new int[what.length];
        for (int i = 0; i < what.length; i++) {
            inties[i] = (int) what[i];
        }
        return inties;
    }

    /**
     * Make an array of int elements from an array of String objects. If the String
     * can't be parsed as a number, it will be set to zero.
     *
     * String s[] = { "1", "300", "44" }; int numbers[] = parseInt(s);
     *
     * numbers will contain { 1, 300, 44 }
     */
    static public int[] parseInt(String[] what) {
        return parseInt(what, 0);
    }

    /**
     * Make an array of int elements from an array of String objects. If the String
     * can't be parsed as a number, its entry in the array will be set to the value
     * of the "missing" parameter.
     *
     * String s[] = { "1", "300", "apple", "44" }; int numbers[] = parseInt(s,
     * 9999);
     *
     * numbers will contain { 1, 300, 9999, 44 }
     */
    static public int[] parseInt(String[] what, int missing) {
        int[] output = new int[what.length];
        for (int i = 0; i < what.length; i++) {
            try {
                output[i] = Integer.parseInt(what[i]);
            } catch (NumberFormatException e) {
                output[i] = missing;
            }
        }
        return output;
    }
}
