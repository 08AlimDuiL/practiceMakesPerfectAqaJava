// src/test/java/ru/stqa/pft/soap/CalculatorTests.java
package ru.stqa.pft.soap;

import org.testng.annotations.Test;

public class CalculatorTests {

    @Test
    public void testCalculator() {
        try {
            Calculator calculatorService = new Calculator();
            CalculatorSoap calculator = calculatorService.getCalculatorSoap12();
            int result = calculator.add(1, 2);
            System.out.println("Результат сложения: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}