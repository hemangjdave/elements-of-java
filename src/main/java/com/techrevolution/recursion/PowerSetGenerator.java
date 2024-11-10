package com.techrevolution.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PowerSetGenerator {
    public static void main(String[] args) {
        System.out.println(generatePowerSet(List.of(1, 2, 3, 4)));
    }

    public static List<List<Integer>> generatePowerSet(List<Integer> list) {
        var result = new ArrayList<List<Integer>>();
        result.add(Collections.emptyList());
        var deque = new LinkedList<List<Integer>>();
        for (var i = 0; i < list.size(); i++) {
            deque.add(new ArrayList<>(Collections.singletonList(list.get(i))));
            bfs(result, deque, list, i);
        }
        return result;
    }

    private static void bfs(List<List<Integer>> result, Deque<List<Integer>> deque, List<Integer> input, int index) {
        while (!deque.isEmpty()) {
            var polled = deque.poll();
            for (var i = index + 1; i < input.size(); i++) {
                var currentList = new ArrayList<>(polled);
                currentList.add(input.get(i));
                result.add(currentList);
                deque.add(currentList);
            }
            index++;
        }
    }
}
