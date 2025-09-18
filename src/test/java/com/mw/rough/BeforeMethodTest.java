package com.mw.rough;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BeforeMethodTest {
	
    NewTest calculator;

    @BeforeMethod
    public void setUp() {
        calculator = new NewTest();
        System.out.println("Calculator setup complete.");
    }

    @Test
    public void testAddition() {
        System.out.println("Addition Test:");
        int result = calculator.add(5, 3);
        System.out.println("Result: " + result);
    }

    @Test
    public void testSubtraction() {
        System.out.println("Subtraction Test:");
        int result = calculator.subtract(10, 4);
        System.out.println("Result: " + result);
    }

    @Test
    public void testMultiplication() {
        System.out.println("Multiplication Test:");
        int result = calculator.multiply(6, 7);
        System.out.println("Result: " + result);
    }

    @Test
    public void testDivision() {
        System.out.println("Division Test:");
        double result = calculator.divide(20, 4);
        System.out.println("Result: " + result);
    }
    
    public class NewTest {

        public int add(int a, int b) {
            return a + b;
        }

        public int subtract(int a, int b) {
            return a - b;
        }

        public int multiply(int a, int b) {
            return a * b;
        }

        public double divide(int dividend, int divisor) {
            if (divisor == 0) {
                throw new IllegalArgumentException("Cannot divide by zero");
            }
            return (double) dividend / divisor;
        }
    }

}
