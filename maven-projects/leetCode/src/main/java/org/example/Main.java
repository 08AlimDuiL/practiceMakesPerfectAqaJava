package org.example;

public class Main {
    public static void main(String[] args) {

        String[] words = {"flower", "flow", "flight"};
        System.out.println(Solutions.longestCommonPrefix(words));
        System.out.println(Solutions.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));

//        //----------------------------------------------------------------------------------------------------------------

        int num = Integer.MAX_VALUE;

        System.out.println("Финальный результат: " + Solutions.addDigits(num));

        //----------------------------------------------------------------------------------------------------------------

//        String occurrence = "";
//
//        String haystack = "sadbutsad";
//        String needle = "sad";
//        int firstOccurrence = -1;
//        System.out.println(firstOccurrence);
//
//        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
//
//            for (int j = 0; j < needle.length(); j++) {
//                if (needle.charAt(i) == haystack.charAt(j)) {
//                    occurrence += haystack.charAt(j);
//                    if (occurrence.equals(needle)) {
//                        firstOccurrence = 0;
//                        System.out.println(firstOccurrence);
//                        break;
//                    }
//                }
//            }
//        }

        //----------------------------------------------------------------------------------------------------------------

        System.out.println(Solutions.stringMatching(new String[]{"mass", "as", "hero", "superhero"}));
        System.out.println(Solutions.stringMatching(new String[]{"abc", "abcd", "abcde", "ab"}));

    }


}