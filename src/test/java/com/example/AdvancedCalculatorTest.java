package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdvancedCalculatorTest {

    @Mock
    private MathLogger mathLogger;

    @Mock
    private SecurityValidator securityValidator;

    @InjectMocks
    private AdvancedCalculator calculator;

    @Test
    void shouldAddTwoNumbers() {
        // Given
        int a = 2;
        int b = 3;

        // When
        int result = calculator.add(a, b);

        // Then
        assertThat(result).isEqualTo(5);
    }

    @Test
    void shouldSubtractTwoNumbers() {
        // Given
        int a = 5;
        int b = 2;

        // When
        int result = calculator.subtract(a, b);

        // Then
        assertThat(result).isEqualTo(3);
    }

    @Test
    void shouldMultiplyTwoNumbers() {
        // Given
        int a = 3;
        int b = 4;

        // When
        int result = calculator.multiply(a, b);

        // Then
        assertThat(result).isEqualTo(12);
    }

    @Test
    void shouldDivideTwoNumbers() {
        // Given
        double a = 10;
        double b = 2;
        when(securityValidator.isOperationAllowed(anyString(), anyDouble(), anyDouble())).thenReturn(true);

        // When
        double result = calculator.divide(a, b);

        // Then
        assertThat(result).isEqualTo(5.0);
        verify(securityValidator).isOperationAllowed("divide", a, b);

        // Argument Capture
        ArgumentCaptor<String> operationCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> resultCaptor = ArgumentCaptor.forClass(Double.class);
        verify(mathLogger).log(operationCaptor.capture(), resultCaptor.capture());

        assertThat(operationCaptor.getValue()).isEqualTo("divide");
        assertThat(resultCaptor.getValue()).isEqualTo(5.0);
    }

    @Test
    void shouldThrowExceptionWhenDividingByZero() {
        // Given
        double a = 10;
        double b = 0;
        lenient().when(securityValidator.isOperationAllowed(eq("divide"), anyDouble(), eq(0.0))).thenReturn(true);

        // When/Then
        assertThatExceptionOfType(ArithmeticException.class)
                .isThrownBy(() -> calculator.divide(a, b))
                .withMessage("Cannot divide by zero");
    }

    @Test
    void shouldThrowExceptionWhenOperationNotAllowed() {
        // Given
        double a = 10;
        double b = 2;
        when(securityValidator.isOperationAllowed(anyString(), anyDouble(), anyDouble())).thenReturn(false);

        // When/Then
        assertThatExceptionOfType(ArithmeticException.class)
                .isThrownBy(() -> calculator.divide(a, b))
                .withMessage("Division operations are not allowed under these conditions");

        verify(mathLogger, never()).log(anyString(), anyDouble());
    }

    @Test
    void shouldCalculatePower() {
        // Given
        double base = 2;
        double exponent = 3;

        // When
        double result = calculator.power(base, exponent);

        // Then
        assertThat(result).isEqualTo(8);
    }

    @Test
    void shouldReturnTrueIfNumberIsEven() {
        // Given
        int n = 2;

        // When
        boolean result = calculator.isEven(n);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseIfNumberIsOdd() {
        // Given
        int n = 3;

        // When
        boolean result = calculator.isEven(n);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    void shouldReturnAbsoluteValue() {
        // Given
        int n = -5;

        // When
        int result = calculator.absoluteValue(n);

        // Then
        assertThat(result).isEqualTo(5);
    }

    @Test
    void shouldCalculateModulo() {
        // Given
        int a = 10;
        int b = 3;

        // When
        int result = calculator.modulo(a, b);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void shouldThrowExceptionWhenModuloByZero() {
        // Given
        int a = 10;
        int b = 0;

        // When/Then
        assertThatExceptionOfType(ArithmeticException.class)
                .isThrownBy(() -> calculator.modulo(a, b));
    }
}
