package com.java.test;

import java.util.Random;

public class Test01 {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int j = new Random().nextInt(1000);
            System.out.println(j);
        }

    }
}
