package com.java.test;

import java.util.Arrays;

public class test03 {

    public static void func1(int[] array) {
        array = new int[]{21, 11, 14, 15, 60, 89};
    }

    public static void func2(int[] array) {
        array[0] = 1000;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(array));
        func1(array);
        System.out.println(Arrays.toString(array));
        func2(array);
        System.out.println(Arrays.toString(array));


    }
}
