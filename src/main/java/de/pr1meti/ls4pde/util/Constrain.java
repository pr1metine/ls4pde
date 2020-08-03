package de.pr1meti.ls4pde.util;

/**
 * Constrain
 */
public class Constrain {

    static public final int constrain(int amt, int low, int high) {
        return (amt < low) ? low : ((amt > high) ? high : amt);
    }

    /**
     * ( begin auto-generated from constrain.xml )
     *
     * Constrains a value to not exceed a maximum and minimum value.
     *
     * ( end auto-generated )
     *
     * @webref math:calculation
     * @param amt  the value to constrain
     * @param low  minimum limit
     * @param high maximum limit
     * @see PApplet#max(float, float, float)
     * @see PApplet#min(float, float, float)
     */

    static public final float constrain(float amt, float low, float high) {
        return (amt < low) ? low : ((amt > high) ? high : amt);
    }
}
