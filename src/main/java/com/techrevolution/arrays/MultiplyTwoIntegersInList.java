package com.techrevolution.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiplyTwoIntegersInList {
    public static void main(String[] args) {
        System.out.println(multiPly(List.of(2, 5), List.of(2, 6)));
    }

    public static List<Integer> multiPly(List<Integer> number1, List<Integer> number2) {
        List<Integer> result = new ArrayList<>(Collections.nCopies(number1.size() * number2.size(), 0));
        var finalResultIndex = number1.size() * number2.size() - 1;
        var carry = 0;
        for (var i = number2.size() - 1; i > -1; i--) {
            var digit = number2.get(i);
            for (var j = number1.size() - 1; j > -1; j--) {
                var secondDigit = number1.get(j);
                var multiply = digit * secondDigit;
                if (multiply < 10) {
                    result.set(finalResultIndex, multiply);
                } else {
                    var dividend = multiply / 10;
                    var reminder = multiply % 10;
                    if (carry == 0) {
                        result.set(finalResultIndex, reminder);
                        carry = dividend;
                    } else {
                        result.set(finalResultIndex, reminder + carry);
                    }
                }
                finalResultIndex--;
            }
        }
        return result;
    }

}
