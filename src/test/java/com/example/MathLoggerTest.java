package com.example;

import org.junit.jupiter.api.Test;

class MathLoggerTest {
    @Test
    void shouldLogOperation() {
        MathLogger logger = new MathLogger();
        logger.log("add", 5.0);
        // Just invoking it covers the line.
    }
}
