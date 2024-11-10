package com.techrevolution.slidingwindow;

import java.util.HashSet;

public class LongestSubStringLength {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        var charSet = new HashSet<Character>();
        var maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            boolean isPresent = charSet.add(s.charAt(i));
            if (!isPresent) {
                int size = charSet.size();
                maxLength = Math.max(maxLength, size);
                charSet.clear();
                charSet.add(s.charAt(i));
            }
        }
        return maxLength;
    }
}
