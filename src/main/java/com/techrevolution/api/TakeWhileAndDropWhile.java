package com.techrevolution.api;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TakeWhileAndDropWhile {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (var i = 0; i < numbers.length; i++) {
            if (numbers[i] > 5) {
                System.out.println(numbers[i]);
            }
        }
        System.out.println("With Streams....");
        IntStream.of(numbers)
                .takeWhile(value -> {
                    System.out.println(value);
                    return value <= 5;
                })
                .forEach(System.out::println);

        System.out.println("-----drop while example-----");
        System.out.println(Stream.of("a", "b", "c", "de", "f", "g", "h")
                .dropWhile(s -> {
                    System.out.println("dropWhile: " + s);
                    return s.length() <= 1;
                })
                .peek(s -> System.out.println("collecting " + s))
                .toList());

    }
}
