package integration.functions.log;

import functions.log.Ln;
import functions.log.Log10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/* Module Log10 integrates with module Ln */
class Log10IntegrationTest {
    @Test
    @DisplayName("Log10 with the real ln implementation should produce correct values at basic points")
    void shouldCalculateCorrectValuesUsingRealLn() {
        Ln ln = new Ln(1e-12);
        Log10 log10 = new Log10(ln);

        assertEquals(1.0, log10.calculate(10.0), 1e-6);
        assertEquals(2.0, log10.calculate(100.0), 1e-6);
        assertEquals(-1.0, log10.calculate(0.1), 1e-6);
    }

    @Test
    @DisplayName("Log10 with the real ln implementation should return NaN outside its domain")
    void shouldReturnNaNOutsideDomainUsingRealLn() {
        Ln ln = new Ln(1e-12);
        Log10 log10 = new Log10(ln);

        assertTrue(Double.isNaN(log10.calculate(0.0)));
        assertTrue(Double.isNaN(log10.calculate(-10.0)));
    }
}