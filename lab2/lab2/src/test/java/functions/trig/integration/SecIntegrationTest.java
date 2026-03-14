package functions.trig.integration;

import functions.MathFunction;
import functions.trig.Cos;
import functions.trig.Sec;
import functions.trig.Sin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

/* Module Sec integrates with module Cos (Sin) */
class SecIntegrationTest {
    private MathFunction sec;

    @BeforeEach
    void setUp(){
        MathFunction cos = new Cos(new Sin(1e-12));
        sec = new Sec(cos);
    }

    @Test
    @DisplayName("Sec with the real cos implementation should produce correct values at defined points")
    void shouldCalculateCorrectValuesUsingRealCos() {
        assertEquals(1.0, sec.calculate(0.0), 1e-7);
        assertEquals(2.0, sec.calculate(Math.PI / 3), 1e-6);
        assertEquals(-1.0, sec.calculate(Math.PI), 1e-7);
    }

    @Test
    @DisplayName("Sec with the real cos implementation should return NaN at pi/2 and -pi/2")
    void shouldReturnNaNAtPiOverTwo() {
        assertTrue(Double.isNaN(sec.calculate(Math.PI / 2)));
        assertTrue(Double.isNaN(sec.calculate(-Math.PI / 2)));
    }
}