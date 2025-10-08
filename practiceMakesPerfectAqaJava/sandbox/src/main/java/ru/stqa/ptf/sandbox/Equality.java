package ru.stqa.ptf.sandbox;

public class Equality {

    public static void main(String[] args) {

        String s1 = "firefox";
        String s2 = s1;
        String s3 = new String(s1);

        System.out.println(s1 == s3);
        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));

    }
}
