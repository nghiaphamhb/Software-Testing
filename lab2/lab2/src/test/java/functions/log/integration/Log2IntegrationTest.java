package functions.log.integration;

import functions.MathFunction;
import functions.log.Ln;
import functions.log.Log2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/* Module Log2 integrates with module Ln */
class Log2IntegrationTest {
    private MathFunction ln;
    private MathFunction log2;

    @BeforeEach
    void setUp(){
        this.ln = new Ln(1e-12);
        this.log2 = new Log2(ln);
    }

    @Test
    @DisplayName("Log2 with the real ln implementation should produce correct values at basic points")
    void shouldCalculateCorrectValuesUsingRealLn() {
        assertEquals(0.0, log2.calculate(1.0), 1e-6);
        assertEquals(1.0, log2.calculate(2.0), 1e-6);
        assertEquals(2.0, log2.calculate(4.0), 1e-6);
        assertEquals(3.0, log2.calculate(8.0), 1e-6);
        assertEquals(-1.0, log2.calculate(0.5), 1e-6);
    }

    @Test
    @DisplayName("Log2 with the real ln implementation should return NaN outside its domain")
    void shouldReturnNaNOutsideDomainUsingRealLn() {
        assertTrue(Double.isNaN(log2.calculate(0.0)));
        assertTrue(Double.isNaN(log2.calculate(-2.0)));
    }

}