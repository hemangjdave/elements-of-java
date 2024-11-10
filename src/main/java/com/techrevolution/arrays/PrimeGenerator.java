package com.techrevolution.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class PrimeGenerator {
    public static void main(String[] args) {
        System.out.println(getPrimes(18));
    }

    public static List<Integer> getPrimes(int n) {
        List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n, true));
        isPrime.set(0, false);
        isPrime.set(1, false);
        for (int i = 2; i * i < n; i++) {
            if (Boolean.TRUE.equals(isPrime.get(i))) {
                for (int j = i * i; j < n; j += i) {
                    isPrime.set(j, false);
                }
            }
        }
        return Stream
                .iterate(2, integer -> integer < n, integer -> integer + 1)
                .filter(integer -> Boolean.TRUE.equals(isPrime.get(integer)))
                .toList();
    }
}
