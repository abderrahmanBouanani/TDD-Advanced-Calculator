package com.example;

public class SecurityValidator {
    public boolean isOperationAllowed(String operation, double a, double b) {
        if ("divide".equals(operation) && b == 0)
            return false;
        if ("power".equals(operation) && b < 0 && Math.abs(b) > 100)
            return false;
        return true;
    }
}
