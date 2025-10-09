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

        List<String> languages = new ArrayList<>();
        languages.add("Java");
        languages.add("C#");
        languages.add("Python");

        List<String> languages1 = Arrays.asList("Java", "C#", "Python", "PHP");

        for (int i = 0; i < languages1.size(); i++) {
            System.out.println("Я мечтаю выучить " + languages1.get(i));
        }

        for (String l : languages1) {
            System.out.println("Я очень мечтаю выучить " + l);
        }

        List languages2 = Arrays.asList("Java", "C#", "Python", "PHP");

        for (Object l : languages2) {
            System.out.println("Я очень желаю выучить " + l);
        }

    }
}
