package module.functions.trig;

import functions.MathFunction;
import functions.trig.Sec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stubs.trig.CosStub;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecTest {

    private static final double DELTA = 1e-9;

    @Test
    @DisplayName("Sec should be calculated as 1 divided by cos(x)")
    void shouldCalculateAsOneDividedByCos() {
        double x1 = 0.0;
        double x2 = Math.PI / 3;
        double x3 = Math.PI;

        MathFunction cosStub = new CosStub(Map.of(
                x1, 1.0,
                x2, 0.5,
                x3, -1.0
        ));

        Sec sec = new Sec(cosStub);

        assertEquals(1.0, sec.calculate(x1), DELTA);
        assertEquals(2.0, sec.calculate(x2), DELTA);
        assertEquals(-1.0, sec.calculate(x3), DELTA);
    }

    @Test
    @DisplayName("Sec should return NaN when cos(x) is NaN")
    void shouldReturnNaNWhenCosIsNaN() {
        MathFunction cosStub = new CosStub(Map.of());
        Sec sec = new Sec(cosStub);

        assertTrue(Double.isNaN(sec.calculate(0.3)));
    }

    @Test
    @DisplayName("Sec should return NaN when cos(x) is approximately zero")
    void shouldReturnNaNWhenCosIsAlmostZero() {
        double x = 1.0;
        MathFunction cosStub = new CosStub(Map.of(x, 1e-12));

        Sec sec = new Sec(cosStub);

        assertTrue(Double.isNaN(sec.calculate(x)));
    }
}