package com.example;

public class AdvancedCalculator {

    private final MathLogger logger;
    private final SecurityValidator validator;

    public AdvancedCalculator(MathLogger logger, SecurityValidator validator) {
        this.logger = logger;
        this.validator = validator;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (!validator.isOperationAllowed("divide", a, b)) {
            throw new ArithmeticException("Division operations are not allowed under these conditions");
        }
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        double result = a / b;
        logger.log("divide", result);
        return result;
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public boolean isEven(int n) {
        return n % 2 == 0;
    }

    public int absoluteValue(int n) {
        return Math.abs(n);
    }

    public int modulo(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a % b;
    }
}
