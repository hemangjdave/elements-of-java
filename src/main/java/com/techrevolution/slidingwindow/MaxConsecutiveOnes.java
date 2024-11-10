package com.techrevolution.slidingwindow;

public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        var maxResult = 0;
        var currentCount = 0;
        for (int num : nums) {
            if (num == 1) {
                currentCount++;
            } else {
                maxResult = Math.max(maxResult, currentCount);
                currentCount = 0;
            }
        }
        return Math.max(maxResult, currentCount);
    }
}
