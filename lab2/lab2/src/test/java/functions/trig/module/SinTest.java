package functions.trig.module;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import functions.trig.Sin;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Module tests for Sin")
public class SinTest {
    private static final double EPS = 1e-8;
    private static final double DELTA = 1e-6;

    private final Sin sin = new Sin(EPS);

    @Test
    @DisplayName("sin(0) should be 0")
    void shouldReturnZeroForZero() {
        assertEquals(0.0, sin.calculate(0.0), DELTA);
    }

    @Test
    @DisplayName("sin(π/2) should be 1")
    void shouldCalculateSinOfPiOverTwo() {
        assertEquals(1.0, sin.calculate(Math.PI / 2), DELTA);
    }

    @Test
    @DisplayName("sin(-π/2) should be -1")
    void shouldCalculateSinOfMinusPiOverTwo() {
        assertEquals(-1.0, sin.calculate(-Math.PI / 2), DELTA);
    }

    @Test
    @DisplayName("sin(π) should be close to 0")
    void shouldCalculateSinOfPi() {
        assertEquals(0.0, sin.calculate(Math.PI), DELTA);
    }

    @Test
    @DisplayName("sin(-π) should be close to 0")
    void shouldCalculateSinOfMinusPi() {
        assertEquals(0.0, sin.calculate(-Math.PI), DELTA);
    }

    @Test
    @DisplayName("sin(π/6) should be 0.5")
    void shouldCalculateSinOfPiOverSix() {
        assertEquals(0.5, sin.calculate(Math.PI / 6), DELTA);
    }

    @Test
    @DisplayName("sin(-π/6) should be -0.5")
    void shouldCalculateSinOfMinusPiOverSix() {
        assertEquals(-0.5, sin.calculate(-Math.PI / 6), DELTA);
    }

    @Test
    @DisplayName("sin(π/4) should be √2/2")
    void shouldCalculateSinOfPiOverFour() {
        assertEquals(Math.sqrt(2) / 2, sin.calculate(Math.PI / 4), DELTA);
    }

    @Test
    @DisplayName("Should match Math.sin for several representative values")
    void shouldCalculateSinOfSeveralValues() {
        double[] values = {
                -2.0 * Math.PI,
                -Math.PI,
                -Math.PI / 2,
                -1.0,
                -0.5,
                0.0,
                0.5,
                1.0,
                Math.PI / 2,
                Math.PI,
                2.0 * Math.PI
        };

        for (double x : values) {
            assertEquals(Math.sin(x), sin.calculate(x), DELTA,
                    "Mismatch for x = " + x);
        }
    }

    @Test
    @DisplayName("Should preserve periodicity with period 2π")
    void shouldBePeriodic() {
        double x = 0.7;
        assertEquals(sin.calculate(x), sin.calculate(x + 2 * Math.PI), DELTA);
        assertEquals(sin.calculate(x), sin.calculate(x - 2 * Math.PI), DELTA);
    }

    @Test
    @DisplayName("Should handle large arguments using normalization")
    void shouldHandleLargeArgumentByNormalization() {
        double x = 100 * Math.PI + Math.PI / 6;
        assertEquals(Math.sin(x), sin.calculate(x), DELTA);
    }

    @Test
    @DisplayName("Constructor should throw for non-positive epsilon")
    void shouldThrowExceptionForNonPositiveEps() {
        assertThrows(IllegalArgumentException.class, () -> new Sin(0.0));
        assertThrows(IllegalArgumentException.class, () -> new Sin(-1e-6));
    }
}