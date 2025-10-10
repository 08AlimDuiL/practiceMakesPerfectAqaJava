package ru.stqa.ptf.sandbox;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Collections {

    public static void main(String[] args) {

        String[] langs = new String[4];
        langs[0] = "Java";
        langs[1] = "C#";
        langs[2] = "Python";
        langs[3] = "PHP";

        String[] langs1 = {"Java", "C#", "Python", "PHP"};

        for (int i = 0; i < langs.length; i++) {
            System.out.println("Я хочу выучить " + langs[i]);
        }

        for (String l : langs) {
            System.out.println("Я очень хочу выучить " + l);
        }
        List<String> strings = new ArrayList<>(Arrays.asList("aa", "bb"));
      //  List<String> strings = new ArrayList<>(List.of("aa", "bb")); mutable для Java 9++
        //   List<String> strings = List.of("aa", "bb")); immutable



    }
}
