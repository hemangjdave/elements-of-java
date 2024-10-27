package com.techrevolution.dynamic;

import java.util.HashSet;
import java.util.LinkedList;

public class CoinChange {
    public static void main(String[] args) {
        System.out.println(new CoinChange().coinChange(new int[]{3, 4, 5}, 31));//7
        System.out.println(new CoinChange().coinChange(new int[]{1, 2, 5}, 11));//3
        System.out.println(new CoinChange().coinChange(new int[]{1, 2, 5}, 6)); //2
        System.out.println(new CoinChange().coinChange(new int[]{1, 3, 5}, 8)); //2
        System.out.println(new CoinChange().coinChange(new int[]{2}, 3));//-1
        System.out.println(new CoinChange().coinChange(new int[]{1}, 0));//0
        System.out.println(new CoinChange().coinChange(new int[]{2}, 4));//2
    }

    // [1,2,5] , 11 --> 3(ans) --> 5+5+1
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return amount;
        if (coins.length == 0) return -1;
        var deque = new LinkedList<Integer>();
        var visitedCoin = new HashSet<Integer>();
        deque.add(amount);
        visitedCoin.add(amount);
        var lastAdded = deque.peekLast();
        var minCoin = 1;
        while (!deque.isEmpty()) {
            var current = deque.pollFirst();
            for (int coin : coins) {
                var remaining = current - coin;
                if (remaining == 0) {
                    return minCoin;
                }
                if (!visitedCoin.contains(remaining) && remaining > 0) {
                    deque.add(remaining);
                    visitedCoin.add(remaining);
                }
            }
            if (current.equals(lastAdded)) {
                minCoin++;
                lastAdded = deque.peekLast();
            }
        }
        return -1;
    }
}
