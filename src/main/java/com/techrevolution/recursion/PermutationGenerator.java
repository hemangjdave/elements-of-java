package com.techrevolution.recursion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class PermutationGenerator {
    public static void main(String[] args) {
        System.out.println(generatePermutations("abcd"));
        System.out.println(generatePermutations("abc"));
    }

    public static List<String> generatePermutations(String input) {
        if (input == null || input.isEmpty() || input.length() == 1)
            return Collections.singletonList(input);

        var visitedString = new HashSet<String>();
        visitedString.add(input);
        var queue = new ArrayDeque<String>();
        queue.add(input);
        var result = new ArrayList<String>();
        result.add(input);
        while (!queue.isEmpty()) {
            var currentString = queue.poll();
            for (var i = 0; i < currentString.length() - 1; i++) {
                var newString = swapChar(currentString, i, i + 1);
                if (!visitedString.contains(newString)) {
                    queue.add(newString);
                    result.add(newString);
                    visitedString.add(newString);
                }
            }
        }
        return result;
    }

    private static String swapChar(String input, int i, int j) {
        var chars = input.toCharArray();
        var temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
