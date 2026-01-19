package com.example;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SecurityValidatorTest {

    private final SecurityValidator validator = new SecurityValidator();

    @Test
    void shouldAllowNormalDivision() {
        assertThat(validator.isOperationAllowed("divide", 10, 2)).isTrue();
    }

    @Test
    void shouldBlockDivisionByZero() {
        assertThat(validator.isOperationAllowed("divide", 10, 0)).isFalse();
    }

    @Test
    void shouldAllowNormalPower() {
        assertThat(validator.isOperationAllowed("power", 2, 3)).isTrue();
    }

    @Test
    void shouldBlockMassiveNegativeExponent() {
        // b < 0 && Math.abs(b) > 100
        assertThat(validator.isOperationAllowed("power", 2, -101)).isFalse();
    }

    @Test
    void shouldAllowSmallNegativeExponent() {
        assertThat(validator.isOperationAllowed("power", 2, -99)).isTrue();
    }

    @Test
    void shouldAllowOtherOperations() {
        assertThat(validator.isOperationAllowed("add", 1, 1)).isTrue();
    }
}
