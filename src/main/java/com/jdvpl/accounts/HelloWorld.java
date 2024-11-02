package com.jdvpl.accounts;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    /**
     * Performs arithmetic operations on two numbers.
     *
     * @param a         the first number
     * @param b         the second number
     * @param operation the operation to perform: "add", "subtract", "multiply", or
     *                  "divide"
     * @return the result of the arithmetic operation
     * @throws IllegalArgumentException if the operation is not supported or if
     *                                  division by zero is attempted
     */
    public static double performOperation(double a, double b, String operation) {
        switch (operation.toLowerCase()) {
            case "add":
                return a + b;
            case "subtract":
                return a - b;
            case "multiply":
                return a * b;
            case "divide":
                if (b == 0) {
                    throw new IllegalArgumentException("Division by zero is not allowed.");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Unsupported operation: " + operation);
        }
    }

}
