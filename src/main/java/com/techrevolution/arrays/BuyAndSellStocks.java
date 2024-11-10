package com.techrevolution.arrays;

import java.util.List;

public class BuyAndSellStocks {
    public static void main(String[] args) {
        System.out.println(maxProfit(List.of(310, 315, 275, 295, 260, 270, 290, 230, 255, 250)));
        System.out.println(maxProfit(List.of(310, 310, 275, 275, 260, 260, 260, 230, 230, 230)));
        System.out.println(maxProfit(List.of(12, 11, 13, 9, 12, 8, 14, 13, 15, 10)));
    }

    public static double maxProfit(List<Integer> prices) {
        double maxProfit = 0;
        var buyStockAt = 0;
        double sellStockAt = 0;
        var buyIndex = 0;
        var sellIndex = 1;
        int listSize = prices.size();
        while (buyIndex < listSize && sellIndex < listSize) {
            buyStockAt = prices.get(buyIndex);
            while (sellIndex < listSize) {
                sellStockAt = prices.get(sellIndex);
                if (sellStockAt > buyStockAt) {
                    maxProfit = Math.max(maxProfit, sellStockAt - buyStockAt);
                } else {
                    buyIndex = sellIndex;
                    sellIndex = sellIndex + 1;
                    break;
                }
                sellIndex++;
            }
        }
        return maxProfit;
    }
}
