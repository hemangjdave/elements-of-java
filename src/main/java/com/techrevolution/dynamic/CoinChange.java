package com.techrevolution.dynamic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class CoinChange {
    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange(new int[]{3, 4, 5}, 31));//7
        System.out.println(coinChange.coinChange(new int[]{1, 2, 5}, 11));//3
        System.out.println(coinChange.coinChange(new int[]{1, 2, 5}, 6)); //2
        System.out.println(coinChange.coinChange(new int[]{1, 3, 5}, 8)); //2
        System.out.println(coinChange.coinChange(new int[]{2}, 3));//-1
        System.out.println(coinChange.coinChange(new int[]{1}, 0));//0
        System.out.println(coinChange.coinChange(new int[]{2}, 4));//2
        System.out.println("------------recursive-------------------");
//        System.out.println(coinChange.coinChangeRecursive(new int[]{3, 4, 5}, 31));//7
//        System.out.println(coinChange.coinChangeRecursive(new int[]{1, 2, 5}, 11));//3
//        System.out.println(coinChange.coinChangeRecursive(new int[]{1, 2, 5}, 6)); //2
        System.out.println(coinChange.coinChangeRecursive(new int[]{1, 3, 5}, 8)); //2
//        System.out.println(coinChange.coinChangeRecursive(new int[]{2}, 3));//-1
//        System.out.println(coinChange.coinChangeRecursive(new int[]{1}, 0));//0
//        System.out.println(coinChange.coinChangeRecursive(new int[]{2}, 4));//2
    }

    public int coinChangeRecursive(int[] coins, int amount) {
        return dfs(coins, amount, new HashSet<>(), 0);
    }

    public int dfs(int[] coins , int amount , Set<Integer> visitedCoin , int minCoin){
        if (amount == 0) return minCoin;
        for (var coin : coins) {
            var currentCoin = amount - coin;
            if (currentCoin < 0) return -1;
            if (!visitedCoin.contains(currentCoin)) {
                visitedCoin.add(currentCoin);
                return dfs(coins, currentCoin, visitedCoin, minCoin);
            }
        }
        return -1;
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
