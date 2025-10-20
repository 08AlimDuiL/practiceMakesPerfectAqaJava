package ru.stqa.ptf.sandbox;

public class Repeat {
    public static void main(String[] args) {
        try {
            String longestWord = findLongest(new String[0]);
            System.out.println("longest word: " + longestWord);
        } catch (Exception e) {
            System.out.println("Couldn't find the longest word.");
        }
        System.out.println("Continue the execution");
    }

    public static String findLongest(String[] words) {
        assertNotEmpty(words);
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    private static void assertNotEmpty(String[] words) {
        if (words == null || words.length == 0) {
            throw new RuntimeException("Array cannot be null or empty");
        }
    }
}
/*
public class Grade {
    private final int grade;  // Была опечатка: gpade -> grade

    public Grade(int grade) {
        if (grade < 1 || grade > 5) {
            throw new IllegalArgumentException("Grade must be between 1 and 5");
        }
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public static void main(String[] args) {
        try {
            Grade three = new Grade(3);
            System.out.println("Created grade: " + three.getGrade());

            // Эта строка выбросит исключение
            Grade seven = new Grade(7);
            System.out.println("Created grade: " + seven.getGrade());

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
 */