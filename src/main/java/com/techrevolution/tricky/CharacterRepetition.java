package com.techrevolution.tricky;

public class CharacterRepetition {
    public static void main(String[] args) {
        var startTime = System.nanoTime();
        System.out.println(repeatCharacter("a1b10"));
        System.out.println(repeatCharacter("b3c6d15"));
        var endTime = System.nanoTime();
        System.out.println(endTime - startTime); // 251417
    }

    public static String repeatCharacter(String input) {
        var counter = 0;
        char c = ' ';
        String finalString = "";
        while (counter < input.length()) {
            char charred = input.charAt(counter);
            if (Character.isLetter(charred)) {
                c = charred;
                counter++;
            }
            char charAt = input.charAt(counter);
            if (Character.isDigit(charAt)) {
                String num = String.valueOf(charAt);
                counter++;
                char at = input.charAt(counter);
                if (Character.isDigit(at)) {
                    num = num.concat(String.valueOf(at));
                    counter++;
                }
                finalString = finalString.concat(String.valueOf(c).repeat(Integer.parseInt(num)));
            }
        }
        return finalString;
    }

}
