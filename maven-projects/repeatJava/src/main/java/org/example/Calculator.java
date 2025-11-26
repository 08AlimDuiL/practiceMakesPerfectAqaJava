package org.example;

class Calculator {
    //Overloading!!!!!!!!!!! перегрузка
    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public String add(String a, String b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}


