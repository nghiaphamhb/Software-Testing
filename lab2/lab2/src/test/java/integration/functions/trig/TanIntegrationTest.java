package integration.functions.trig;

import functions.MathFunction;
import functions.trig.Cos;
import functions.trig.Sin;
import functions.trig.Tan;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/* Module Tan integrates with module Cos and Sin */
class TanIntegrationTest {
    @Test
    @DisplayName("Tan with real sin and cos implementations should produce correct values at regular points")
    void shouldCalculateCorrectValuesUsingRealDependencies() {
        MathFunction sin = new Sin(1e-12);
        MathFunction cos = new Cos(sin);
        Tan tan = new Tan(sin, cos);

        assertEquals(0.0, tan.calculate(0.0), 1e-7);
        assertEquals(1.0, tan.calculate(Math.PI / 4), 1e-6);
        assertEquals(-1.0, tan.calculate(-Math.PI / 4), 1e-6);
    }

    @Test
    @DisplayName("Tan with real dependencies should return NaN at pi/2")
    void shouldReturnNaNAtPiOverTwo() {
        MathFunction sin = new Sin(1e-12);
        MathFunction cos = new Cos(sin);
        Tan tan = new Tan(sin, cos);

        assertTrue(Double.isNaN(tan.calculate(Math.PI / 2)));
    }
}