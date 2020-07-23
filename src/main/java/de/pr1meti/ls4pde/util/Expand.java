package de.pr1meti.ls4pde.util;

import java.lang.reflect.Array;

/**
 * Expand
 */
public class Expand {

    /**
     * ( begin auto-generated from expand.xml )
     *
     * Increases the size of an array. By default, this function doubles the size of
     * the array, but the optional <b>newSize</b> parameter provides precise control
     * over the increase in size. <br/>
     * <br/>
     * When using an array of objects, the data returned from the function must be
     * cast to the object array's data type. For example: <em>SomeClass[] items =
     * (SomeClass[]) expand(originalArray)</em>.
     *
     * ( end auto-generated )
     *
     * @webref data:array_functions
     * @param list the array to expand
     * @see PApplet#shorten(boolean[])
     */
    static public boolean[] expand(boolean[] list) {
        return expand(list, list.length > 0 ? list.length << 1 : 1);
    }

    /**
     * @param newSize new size for the array
     */
    static public boolean[] expand(boolean[] list, int newSize) {
        boolean[] temp = new boolean[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(newSize, list.length));
        return temp;
    }

    static public byte[] expand(byte[] list) {
        return expand(list, list.length > 0 ? list.length << 1 : 1);
    }

    static public byte[] expand(byte[] list, int newSize) {
        byte[] temp = new byte[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(newSize, list.length));
        return temp;
    }

    static public char[] expand(char[] list) {
        return expand(list, list.length > 0 ? list.length << 1 : 1);
    }

    static public char[] expand(char[] list, int newSize) {
        char[] temp = new char[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(newSize, list.length));
        return temp;
    }

    static public int[] expand(int[] list) {
        return expand(list, list.length > 0 ? list.length << 1 : 1);
    }

    static public int[] expand(int[] list, int newSize) {
        int[] temp = new int[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(newSize, list.length));
        return temp;
    }

    static public long[] expand(long[] list) {
        return expand(list, list.length > 0 ? list.length << 1 : 1);
    }

    static public long[] expand(long[] list, int newSize) {
        long[] temp = new long[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(newSize, list.length));
        return temp;
    }

    static public float[] expand(float[] list) {
        return expand(list, list.length > 0 ? list.length << 1 : 1);
    }

    static public float[] expand(float[] list, int newSize) {
        float[] temp = new float[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(newSize, list.length));
        return temp;
    }

    static public double[] expand(double[] list) {
        return expand(list, list.length > 0 ? list.length << 1 : 1);
    }

    static public double[] expand(double[] list, int newSize) {
        double[] temp = new double[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(newSize, list.length));
        return temp;
    }

    static public String[] expand(String[] list) {
        return expand(list, list.length > 0 ? list.length << 1 : 1);
    }

    static public String[] expand(String[] list, int newSize) {
        String[] temp = new String[newSize];
        // in case the new size is smaller than list.length
        System.arraycopy(list, 0, temp, 0, Math.min(newSize, list.length));
        return temp;
    }

    /**
     * @nowebref
     */
    static public Object expand(Object array) {
        int len = Array.getLength(array);
        return expand(array, len > 0 ? len << 1 : 1);
    }

    static public Object expand(Object list, int newSize) {
        Class<?> type = list.getClass().getComponentType();
        Object temp = Array.newInstance(type, newSize);
        System.arraycopy(list, 0, temp, 0, Math.min(Array.getLength(list), newSize));
        return temp;
    }

    // contract() has been removed in revision 0124, use subset() instead.
    // (expand() is also functionally equivalent)

    /**
     * ( begin auto-generated from append.xml )
     *
     * Expands an array by one element and adds data to the new position. The
     * datatype of the <b>element</b> parameter must be the same as the datatype of
     * the array. <br/>
     * <br/>
     * When using an array of objects, the data returned from the function must be
     * cast to the object array's data type. For example: <em>SomeClass[] items =
     * (SomeClass[]) append(originalArray, element)</em>.
     *
     * ( end auto-generated )
     *
     * @webref data:array_functions
     * @param array array to append
     * @param value new data for the array
     * @see PApplet#shorten(boolean[])
     * @see PApplet#expand(boolean[])
     */
    static public byte[] append(byte[] array, byte value) {
        array = expand(array, array.length + 1);
        array[array.length - 1] = value;
        return array;
    }

    static public char[] append(char[] array, char value) {
        array = expand(array, array.length + 1);
        array[array.length - 1] = value;
        return array;
    }

    static public int[] append(int[] array, int value) {
        array = expand(array, array.length + 1);
        array[array.length - 1] = value;
        return array;
    }

    static public float[] append(float[] array, float value) {
        array = expand(array, array.length + 1);
        array[array.length - 1] = value;
        return array;
    }

    static public String[] append(String[] array, String value) {
        array = expand(array, array.length + 1);
        array[array.length - 1] = value;
        return array;
    }

    static public Object append(Object array, Object value) {
        int length = Array.getLength(array);
        array = expand(array, length + 1);
        Array.set(array, length, value);
        return array;
    }
}
