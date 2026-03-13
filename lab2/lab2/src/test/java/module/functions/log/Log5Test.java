package module.functions.log;

import functions.MathFunction;
import functions.log.Log5;
import stubs.log.LnStub;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Log5Test {

    private static final double DELTA = 1e-9;

    @Test
    @DisplayName("Log5 should be calculated as ln(x) divided by ln(5)")
    void shouldCalculateAsLnDividedByLn5() {
        double x1 = 5.0;
        double x2 = 25.0;
        double x3 = 0.2;

        MathFunction lnStub = new LnStub(Map.of(
                x1, Math.log(5.0),
                x2, Math.log(25.0),
                x3, Math.log(0.2)
        ));

        Log5 log5 = new Log5(lnStub);

        assertEquals(1.0, log5.calculate(x1), DELTA);
        assertEquals(2.0, log5.calculate(x2), DELTA);
        assertEquals(-1.0, log5.calculate(x3), DELTA);
    }

    @Test
    @DisplayName("Log5 should return NaN when ln(x) is NaN")
    void shouldReturnNaNWhenLnIsNaN() {
        MathFunction lnStub = new LnStub(Map.of());
        Log5 log5 = new Log5(lnStub);

        assertTrue(Double.isNaN(log5.calculate(3.0)));
    }

    @Test
    @DisplayName("Log5 should return zero when x equals 1")
    void shouldReturnZeroAtOne() {
        MathFunction lnStub = new LnStub(Map.of(1.0, 0.0));
        Log5 log5 = new Log5(lnStub);

        assertEquals(0.0, log5.calculate(1.0), DELTA);
    }
}