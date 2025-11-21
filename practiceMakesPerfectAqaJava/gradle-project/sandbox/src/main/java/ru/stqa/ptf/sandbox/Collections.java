package ru.stqa.ptf.sandbox;

import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

        Iterator<String> iter = strings.iterator(); // контракт для итерации
        // Set<Integer> intSet = Set.of(1, 2, 3, 4, 5);
        Set<Integer> intSet = new HashSet<>();

        Map<String, Integer> map = new HashMap<>();

       /* List<Integer> ints = new ArrayList<Integer>(List.of(1, 4, -2, 5, 0, 6));
        ints.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(ints);
*/

    }
}
