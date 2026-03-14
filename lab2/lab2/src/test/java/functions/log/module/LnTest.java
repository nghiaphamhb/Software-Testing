package functions.log.module;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import functions.log.Ln;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Module tests for Ln")
public class LnTest {
    private static final double EPS = 1e-8;
    private static final double DELTA = 1e-6;

    private final Ln ln = new Ln(EPS);

    @Test
    @DisplayName("Should return NaN when x = 0")
    void shouldReturnNaNForZero() {
        assertTrue(Double.isNaN(ln.calculate(0.0)));
    }

    @Test
    @DisplayName("Should return NaN for negative input values or Nan value")
    void shouldReturnNaNForNegativeValue() {
        assertTrue(Double.isNaN(ln.calculate(-1.0)));
        assertTrue(Double.isNaN(ln.calculate(-10.0)));
        assertTrue(Double.isNaN(ln.calculate(Double.NaN)));
    }

    @Test
    @DisplayName("Should return 0 when x = 1")
    void shouldReturnZeroForOne() {
        assertEquals(0.0, ln.calculate(1.0), DELTA);
    }

    @Test
    @DisplayName("Should correctly calculate ln(e) = 1")
    void shouldCalculateLnOfE() {
        assertEquals(1.0, ln.calculate(Math.E), DELTA);
    }

    @Test
    @DisplayName("Should correctly calculate ln(2)")
    void shouldCalculateLnOfTwo() {
        assertEquals(Math.log(2.0), ln.calculate(2.0), DELTA);
    }

    @Test
    @DisplayName("Should correctly calculate ln(0.5)")
    void shouldCalculateLnOfHalf() {
        assertEquals(Math.log(0.5), ln.calculate(0.5), DELTA);
    }

    @Test
    @DisplayName("Should correctly calculate ln(10)")
    void shouldCalculateLnOfTen() {
        assertEquals(Math.log(10.0), ln.calculate(10.0), DELTA);
    }

    @Test
    @DisplayName("Should correctly calculate ln for a small positive value")
    void shouldCalculateLnForSmallPositiveValue() {
        double x = 0.1;
        assertEquals(Math.log(x), ln.calculate(x), DELTA);
    }

    @Test
    @DisplayName("Should correctly calculate ln for a value greater than 1")
    void shouldCalculateLnForValueGreaterThanOne() {
        double x = 5.0;
        assertEquals(Math.log(x), ln.calculate(x), DELTA);
    }

    @Test
    @DisplayName("Should be accurate for several representative input values")
    void shouldBeAccurateForSeveralValues() {
        double[] values = {0.2, 0.5, 1.5, 2.0, 3.0, 7.0, 15.0};

        for (double x : values) {
            assertEquals(Math.log(x), ln.calculate(x), DELTA,
                    "Mismatch for x = " + x);
        }
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when eps is non-positive")
    void shouldThrowExceptionForNonPositiveEps() {
        assertThrows(IllegalArgumentException.class, () -> new Ln(0.0));
        assertThrows(IllegalArgumentException.class, () -> new Ln(-1e-6));
    }
}