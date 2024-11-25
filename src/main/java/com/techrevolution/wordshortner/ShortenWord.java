package com.techrevolution.wordshortner;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.Integer.valueOf;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

public class ShortenWord {
    public static void main(String[] args) {
        var string = "AAAABBB1111CCCDD";
        System.out.println(shortenWord(string));
    }

    private static String shortenWord(String string) {
        Map<Character, Integer> collect = string.chars()
                .mapToObj(value -> (char) value)
                .collect(
                        groupingBy(identity(), LinkedHashMap::new, collectingAndThen(
                                counting(), aLong -> valueOf(String.valueOf(aLong))
                        ))
                );
        System.out.println("Collection map of frequency is");
        System.out.println(collect);
        return collect.keySet()
                .stream()
                .flatMap(
                        character -> of(collect.get(character))
                                .map(freq -> String.valueOf(freq).concat(String.valueOf(character)))
                ).collect(joining());
    }
}
