package com.techrevolution.tricky;

public class CharacterRepetitionRecursion {
    public static void main(String[] args) {
        var startTime = System.nanoTime();
        System.out.println(repeatCharacter("a1b10"));
        System.out.println(repeatCharacter("b3c6d15"));
        var endTime = System.nanoTime();
        System.out.println(endTime - startTime); // 600416
    }

    public static String repeatCharacter(String input) {
        return repeat(input, 0, 0);
    }

    private static String repeat(String string, int repeat, int stringIndex) {
        if (stringIndex >= string.length()) {
            return "";
        }
        String character = string.substring(stringIndex, stringIndex + 1);
        int index = stringIndex + 1;
        char c = string.charAt(index);
        if (Character.isDigit(c)) {
            ++index;
            if (Character.isDigit(string.charAt(index))) {
                index++;
            }
            String substring = string.substring(stringIndex + 1, index);
            repeat = Integer.parseInt(substring);
        }
        return character.repeat(repeat) + repeat(string, 0, index);
    }


//    public static String repeatCharacter(String input) {
//        var counter = 0;
//        char c = ' ';
//        String finalString = "";
//        while (counter < input.length()) {
//            char charred = input.charAt(counter);
//            if (Character.isLetter(charred)) {
//                c = charred;
//                counter++;
//            }
//            char charAt = input.charAt(counter);
//            if (Character.isDigit(charAt)) {
//                String num = String.valueOf(charAt);
//                counter++;
//                char at = input.charAt(counter);
//                if (Character.isDigit(at)) {
//                    num = num.concat(String.valueOf(at));
//                    counter++;
//                }
//                finalString = finalString.concat(String.valueOf(c).repeat(Integer.parseInt(num)));
//            }
//        }
//        return finalString;
//    }
}
