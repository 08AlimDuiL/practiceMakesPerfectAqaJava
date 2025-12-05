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

    //-------------------------------------------------------------------------------------------------------------------
    //217. Contains Duplicate
    public boolean containsDuplicate(int[] nums) {

        Set<Integer> noDoubleNums = new HashSet<>();

        for (int num : nums) {
            noDoubleNums.add(num);
        }

        if (nums.length > noDoubleNums.size()) {
            return true;
        } else {
            return false;
        }
/*
    return Arrays.stream(nums)
        .boxed()
        .collect(Collectors.toSet())
        .size() < nums.length;
 */
    }

    //-------------------------------------------------------------------------------------------------------------------
    //268. Недостающее число

    public static int missingNumber(int[] nums) {

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

    //-------------------------------------------------------------------------------------------------------------------
    // 905. Sort Array By Parity

    public static int[] sortArrayByParity(int[] nums) {

        int[] newSort = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] % 2 == 0) {
                newSort[left] = nums[i];
                left++;
            }

            if (nums[i] % 2 != 0) {
                newSort[right] = nums[i];
                right--;
            }

        }
        return newSort;
    }
    //-------------------------------------------------------------------------------------------------------------------
    // 26. Remove Duplicates from Sorted Array

    public int removeDuplicates(int[] nums) {

        Set<Integer> remove = new LinkedHashSet<>();
        for (int num : nums) {

            remove.add(num);
        }

        int k = remove.size();

        int i = 0;
        for (int num : remove) {
            nums[i] = num;
            i++;
        }

        return k;
    }


    public int removeDuplicates2(int[] nums) {

        int[] unique = Arrays.stream(nums)
                .distinct()
                .toArray();


        return 0;
    }

    //27. Удаление элемента
    public int removeElement(int[] nums, int val) {
        int[] newNums = Arrays.stream(nums)
                .filter(num -> num != val)
                .toArray();

        for (int i = 0; i < newNums.length; i++) {
            nums[i] = newNums[i];
        }

        return newNums.length;
    }

    public int removeElement2(int[] nums, int val) {

        int k = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i]; // копируем в начало
                k++;
            }
        }

        return k;
    }
    //-------------------------------------------------------------------------------------------------------------------
    // 66. Плюс один

    public int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {

            if (digits[i] < 9) {
                digits[i]++;

                return digits;
            }
            digits[i] = 0;
        }

        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    //-------------------------------------------------------------------------------------------------------------------
    // 136. единственное число
    public int singleNumber(int[] nums) {

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }

        return nums[nums.length - 1];
    }


    //-------------------------------------------------------------------------------------------------------------------


}
