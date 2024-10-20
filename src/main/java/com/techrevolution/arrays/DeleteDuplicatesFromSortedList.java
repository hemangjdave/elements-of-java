package com.techrevolution.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteDuplicatesFromSortedList {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new ArrayList<>(Arrays.asList(2, 3, 5, 5, 7, 11, 11, 11, 13))));
    }

    public static List<Integer> removeDuplicates(List<Integer> list) {
        if (list.size() == 1 || list.isEmpty()) {
            return list;
        }
        var vacantIndex = -1;

        return list;
    }
}
