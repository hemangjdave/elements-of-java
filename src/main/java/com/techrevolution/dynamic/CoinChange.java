package com.techrevolution.dynamic;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CoinChange {
    public static void main(String[] args) {
        System.out.println(new CoinChange().coinChange(new int[]{1, 2, 5}, 11));//3
        System.out.println(new CoinChange().coinChange(new int[]{1, 2, 5}, 6)); //2
        System.out.println(new CoinChange().coinChange(new int[]{1, 3, 5}, 8)); //2
        System.out.println(new CoinChange().coinChange(new int[]{2}, 3));//-1
        System.out.println(new CoinChange().coinChange(new int[]{1}, 0));//0
        System.out.println(new CoinChange().coinChange(new int[]{2}, 4));//2
    }

    // [1,2,5] , 11 --> 3(ans) --> 5+5+1
    public int coinChange(int[] coins, int amount) {
        Map<Integer, Integer> collect = Arrays.stream(coins)
                .boxed()
                .collect(
                        Collectors.toMap(Function.identity(), integer -> 1)
                );
        List<Integer> possibilities = new ArrayList<>();
        for (int key : collect.keySet()) {
            var quotient = amount / key;
            var reminder = amount % key;
            if (reminder == 0) {
                possibilities.add(quotient);
            } else {
                if (collect.get(reminder) != null) {
                    possibilities.add(quotient + reminder);
                }
            }
        }
        return possibilities.isEmpty() ? -1 : possibilities.stream().min(Comparator.naturalOrder()).orElse(-1);

    }
}
