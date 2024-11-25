package com.techrevolution.slidingwindow;

import java.util.LinkedList;

public class MaxConsecutiveOnesWithKFlipBits {
    public static void main(String[] args) {

        System.out.println(findMaxConsecutiveOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));//6
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0}, 2));//?
        System.out.println(findMaxConsecutiveOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));//10
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 3));//11
        System.out.println(findMaxConsecutiveOnes(new int[]{0, 0, 1, 1, 1, 0, 0}, 0));//3
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 0));//3

        System.out.println("-----with dp------");
        System.out.println(findMaxConsecutiveOnesWithDP(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));//6
        System.out.println(findMaxConsecutiveOnesWithDP(new int[]{1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0}, 2));//?
        System.out.println(findMaxConsecutiveOnesWithDP(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));//10
        System.out.println(findMaxConsecutiveOnesWithDP(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 3));//11
        System.out.println(findMaxConsecutiveOnesWithDP(new int[]{0, 0, 1, 1, 1, 0, 0}, 0));//3
        System.out.println(findMaxConsecutiveOnesWithDP(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 0));//3

    }

    public static int findMaxConsecutiveOnesWithDP(int[] nums, int k) {
        var currentOnes = 0;
        var maxOnes = 0;
        var queue = new LinkedList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                currentOnes++;
            } else {
                if (k > 0) {
                    currentOnes++;
                    k--;
                    queue.add(i);
                } else if (!queue.isEmpty()) {
                    currentOnes = i - queue.pollFirst();
                    queue.add(i);
                } else {
                    currentOnes = 0;
                }
            }
            maxOnes = Math.max(maxOnes, currentOnes);
        }
        return Math.max(maxOnes, currentOnes);
    }


    public static int findMaxConsecutiveOnes(int[] nums, int k) {

        var currentOnes = 0;
        var maxOnes = 0;
        var swapLength = k;
        var zeroIndexQueue = new LinkedList<Integer>();
        for (var i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                currentOnes++;
            } else {
                if (swapLength > 0) {
                    zeroIndexQueue.add(i);
                    nums[i] += 1;
                    currentOnes++;
                    swapLength--;
                } else {
                    if (!zeroIndexQueue.isEmpty()) {
                        var index = zeroIndexQueue.pollFirst();
                        nums[index] -= 1;
                        nums[i] += 1;
                        zeroIndexQueue.add(i);
                    }
                    currentOnes = 0;
                    for (var idx = i; idx > -1; idx--) {
                        if (nums[idx] == 1) {
                            currentOnes++;
                        } else {
                            break;
                        }
                    }
                }

            }
            maxOnes = Math.max(maxOnes, currentOnes);
        }
        return Math.max(maxOnes, currentOnes);
    }
}
