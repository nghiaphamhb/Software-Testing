package integration.functions.trig;

import functions.MathFunction;
import functions.trig.Cos;
import functions.trig.Sin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* Module Cos integrates with module Sin */
class CosIntegrationTest {
    @Test
    @DisplayName("Cos with the real sin implementation should produce correct values at basic angles")
    void shouldCalculateCorrectValuesUsingRealSin() {
        MathFunction sin = new Sin(1e-12);
        Cos cos = new Cos(sin);

        assertEquals(1.0, cos.calculate(0.0), 1e-7);
        assertEquals(0.0, cos.calculate(Math.PI / 2), 1e-7);
        assertEquals(-1.0, cos.calculate(Math.PI), 1e-7);
        assertEquals(0.0, cos.calculate(3 * Math.PI / 2), 1e-7);
    }
}