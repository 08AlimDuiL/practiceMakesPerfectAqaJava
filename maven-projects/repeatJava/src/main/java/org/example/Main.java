package org.example;

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // Компилятор выбирает подходящий метод по параметрам
        System.out.println(calc.add(5, 10));
        System.out.println("・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・");
        System.out.println(calc.add(5, 10, 15));    // 30
        System.out.println("・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・");
        System.out.println(calc.add(5.5, 2.3));     // 7.8
        System.out.println("・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・");
        System.out.println(calc.add("Hello ", "World")); // "Hello World"
        System.out.println("・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・");


        Calculator basic = new Calculator();
        ScientificCalculator science = new ScientificCalculator();

        System.out.println("Базовый: " + basic.multiply(5, 6));
        System.out.println("・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・・");
        System.out.println("Научный: " + science.multiply(5, 6));
    }

}