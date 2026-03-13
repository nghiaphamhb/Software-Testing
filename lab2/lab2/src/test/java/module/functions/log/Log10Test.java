package module.functions.log;

import functions.MathFunction;
import functions.log.Log10;
import stubs.log.LnStub;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Log10Test {

    private static final double DELTA = 1e-9;

    @Test
    @DisplayName("Log10 should be calculated as ln(x) divided by ln(10)")
    void shouldCalculateAsLnDividedByLn10() {
        double x1 = 10.0;
        double x2 = 100.0;
        double x3 = 0.1;

        MathFunction lnStub = new LnStub(Map.of(
                x1, Math.log(10.0),
                x2, Math.log(100.0),
                x3, Math.log(0.1)
        ));

        Log10 log10 = new Log10(lnStub);

        assertEquals(1.0, log10.calculate(x1), DELTA);
        assertEquals(2.0, log10.calculate(x2), DELTA);
        assertEquals(-1.0, log10.calculate(x3), DELTA);
    }

    @Test
    @DisplayName("Log10 should return NaN when ln(x) is NaN")
    void shouldReturnNaNWhenLnIsNaN() {
        MathFunction lnStub = new LnStub(Map.of());
        Log10 log10 = new Log10(lnStub);

        assertTrue(Double.isNaN(log10.calculate(3.0)));
    }

    @Test
    @DisplayName("Log10 should return zero when x equals 1")
    void shouldReturnZeroAtOne() {
        MathFunction lnStub = new LnStub(Map.of(1.0, 0.0));
        Log10 log10 = new Log10(lnStub);

        assertEquals(0.0, log10.calculate(1.0), DELTA);
    }
}