package org.example;

public class ScientificCalculator extends Calculator {

    @Override // Переопределение
    public int multiply(int a, int b) {

        System.out.println("Умножаем: " + a + " * " + b);
        if (a == 0 || b == 0) {
            System.out.println("Один из множителей равен 0");
            return 0;
        }
        int result = a * b;
        System.out.println("Результат: " + result);

        return result;
    }
}
