package com.techrevolution.arrays;

import java.util.Arrays;
import java.util.Random;

//write a program that takes an array of integer.
//Re-order an array so that all even integers come first then odd integers.
public class EvenOddArrays {

    public static void main(String[] args) {
        int[] array = new int[10];
        Random random = new Random();
        for (var i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(array));
        System.out.println("--------after arranging items--------");
        arrangeArray(array);
        System.out.println(Arrays.toString(array));
    }

    //[65, 96, 37, 75, 11, 77, 4, 28, 40, 11]

    public static void arrangeArray(int[] input) {
        var even = 0;
        var odd = input.length - 1;
        while (even < odd) {
            if (input[even] % 2 == 0) {
                even++;
            } else {
                var temp = input[odd];
                input[odd] = input[even];
                input[even] = temp;
                odd--;
            }
        }
    }
}
