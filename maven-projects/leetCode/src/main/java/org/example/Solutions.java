package org.example;

import java.util.*;

public class Solutions {

    //14. Longest Common Prefix
    public static String longestCommonPrefix(String[] strs) {  //   14. Longest Common Prefix

        if (strs == null || strs.length == 0) {

            return "Для входного массива строк общего префикса нет.";
        }

        String prefix = "";
        int minCharacters = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minCharacters) {
                minCharacters = strs[i].length();
                prefix = strs[i];
            }
        }

        for (int i = 0; i < prefix.length(); i++) {
            char currentChar = prefix.charAt(i);

            for (int j = 0; j < strs.length; j++) {

                if (strs[j].charAt(i) != currentChar) {
                    if (i == 0) {

                        return "Для входного массива строк общего префикса нет.";
                    }

                    return prefix.substring(0, i);
                }
            }
        }

        return prefix;
    }

    //258 Add Digits
    public static int addDigits(int num) {

        String b = String.valueOf(num);
        int sum = num;

        while (b.length() > 1) {
            int[] c = new int[b.length()];
            sum = 0;

            for (int i = 0; i < b.length(); i++) {
                c[i] = Integer.parseInt(String.valueOf(b.charAt(i)));
                sum += c[i];
            }
            b = String.valueOf(sum);
        }

        return sum;

        /*
            if (num == 0) return 0;
            return 1 + (num - 1) % 9; ответ всегда равен остатку от деления на 9, учитываем что 18%9 = 0, но цифровой корень 1+8 = 9
         */
    }
    //-------------------------------------------------------------------------------------------------------------------

    //28. Find the Index of the First Occurrence in a String
    //Даны две строки needle и haystack, верните индекс первого вхождения needle в haystack. Если needle не входит в haystack, верните -1.
    public int strStr(String haystack, String needle) {

        int firstOccurrence = haystack.indexOf(needle);

        return firstOccurrence;
    }

    //-------------------------------------------------------------------------------------------------------------------


    //1408. String Matching in an Array
    public static List<String> stringMatching(String[] words) {

        // List<String> substring = new ArrayList<String>();

        Set<String> substring = new HashSet<>();
        for (int i = 0; i < words.length; i++) {

            for (int j = 0; j < words.length; j++) {

                if (i != j && words[j].contains(words[i])) {

                    substring.add(words[i]);
                    //break;
                }
            }
        }

        return new ArrayList<>(substring);
    }
}
