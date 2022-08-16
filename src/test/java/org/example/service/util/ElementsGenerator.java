package org.example.service.util;

import java.util.Random;

public class ElementsGenerator {
    public static int[] generateRandomPositive(int length){
        int[] result = new int[length];
        Random rand = new Random();
        int upperbound = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            int value = rand.nextInt(upperbound);
            result[i] = value;
        }
        return result;
    }
    public static int[] generateSequentialPositive(int length){
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = i;
        }
        return result;
    }

    public static int[] generateSequentialWithDecrementPositive(int length) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            //[100, 91, 83, 76, 70, 65, 61, 58, 56, 55]
            //x     -9  -8  -7  -6  -5  -4  -3  -2  -1
            if (i == 0) {
                result[i] = length * length;
            } else {
                result[i] = result[i - 1] - (length - i);
            }
        }
        return result;
    }
}
