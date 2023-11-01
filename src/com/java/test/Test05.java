package com.java.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Test05 {

    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            System.out.println(r.nextInt(3)+1);
        }
    }
}
