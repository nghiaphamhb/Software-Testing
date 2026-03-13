package integration.functions.trig;

import functions.MathFunction;
import functions.trig.Cos;
import functions.trig.Sec;
import functions.trig.Sin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/* Module Sec integrates with module Cos (Sin) */
class SecIntegrationTest {
    @Test
    @DisplayName("Sec with the real cos implementation should produce correct values at defined points")
    void shouldCalculateCorrectValuesUsingRealCos() {
        MathFunction sin = new Sin(1e-12);
        MathFunction cos = new Cos(sin);
        Sec sec = new Sec(cos);

        assertEquals(1.0, sec.calculate(0.0), 1e-7);
        assertEquals(2.0, sec.calculate(Math.PI / 3), 1e-6);
        assertEquals(-1.0, sec.calculate(Math.PI), 1e-7);
    }

    @Test
    @DisplayName("Sec with the real cos implementation should return NaN at pi/2")
    void shouldReturnNaNAtPiOverTwo() {
        MathFunction sin = new Sin(1e-12);
        MathFunction cos = new Cos(sin);
        Sec sec = new Sec(cos);

        assertTrue(Double.isNaN(sec.calculate(Math.PI / 2)));
    }
}