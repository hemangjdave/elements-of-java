package com.techrevolution.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class LastStoneWeight {
    public static void main(String[] args) {
        System.out.println(lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
        System.out.println(lastStoneWeight(new int[]{2, 2}));


        System.out.println("-------recursively-------------");
        System.out.println(lastStoneWeightRecursive(new int[]{2, 7, 4, 1, 8, 1}));
        System.out.println(lastStoneWeightRecursive(new int[]{2, 2}));
    }

    public static int lastStoneWeightRecursive(int[] stones) {
        var priorityQueue = new PriorityQueue<Integer>(Comparator.reverseOrder());
        Arrays.stream(stones).boxed().forEach(priorityQueue::offer);
        return dfs(priorityQueue);
    }

    private static int dfs(PriorityQueue<Integer> queue) {
        if (queue.isEmpty()) {
            return 0;
        }
        if (queue.size() == 1) {
            return queue.poll();
        }
        var highest = queue.poll();
        var secondHighest = queue.poll();
        if (!Objects.equals(highest, secondHighest) && highest > secondHighest) {
            queue.add(highest - secondHighest);
        }
        return dfs(queue);
    }


    //[4 , 3]
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> priorityQueue = Arrays.stream(stones).boxed().collect(Collectors.toCollection(() -> new PriorityQueue<>(Comparator.<Integer>reverseOrder())));

        while (priorityQueue.size() > 1) {
            var highest = priorityQueue.poll();
            var lowest = priorityQueue.poll();
            var smashed = highest - lowest;
            if (smashed == 0 && priorityQueue.isEmpty()) {
                return 0;
            }
            if (smashed > 0) {
                priorityQueue.offer(smashed);
            }

            if (priorityQueue.size() == 1) {
                return priorityQueue.poll();
            }
        }

        return 0;
    }
}
