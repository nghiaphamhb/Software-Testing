package integration.functions.log;

import functions.log.Ln;
import functions.log.Log5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/* Module Log5 integrates with module Ln */
class Log5IntegrationTest {
    @Test
    @DisplayName("Log5 with the real ln implementation should produce correct values at basic points")
    void shouldCalculateCorrectValuesUsingRealLn() {
        Ln ln = new Ln(1e-12);
        Log5 log5 = new Log5(ln);

        assertEquals(1.0, log5.calculate(5.0), 1e-6);
        assertEquals(2.0, log5.calculate(25.0), 1e-6);
        assertEquals(-1.0, log5.calculate(0.2), 1e-6);
    }

    @Test
    @DisplayName("Log5 with the real ln implementation should return NaN outside its domain")
    void shouldReturnNaNOutsideDomainUsingRealLn() {
        Ln ln = new Ln(1e-12);
        Log5 log5 = new Log5(ln);

        assertTrue(Double.isNaN(log5.calculate(0.0)));
        assertTrue(Double.isNaN(log5.calculate(-5.0)));
    }
}