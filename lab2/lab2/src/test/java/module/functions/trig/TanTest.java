package module.functions.trig;

import functions.MathFunction;
import functions.trig.Tan;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stubs.trig.CosStub;
import stubs.trig.SinStub;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TanTest {

    private static final double DELTA = 1e-9;

    @Test
    @DisplayName("Tan should be calculated as sin(x) divided by cos(x)")
    void shouldCalculateAsSinDividedByCos() {
        double x1 = -1.0;
        double x2 = -0.5;
        double x3 = 0.5;

        MathFunction sinStub = new SinStub(Map.of(
                x1, -0.84,
                x2, -0.48,
                x3, 0.48
        ));

        MathFunction cosStub = new CosStub(Map.of(
                x1, 0.54,
                x2, 0.88,
                x3, 0.88
        ));

        Tan tan = new Tan(sinStub, cosStub);

        assertEquals(-0.84 / 0.54, tan.calculate(x1), DELTA);
        assertEquals(-0.48 / 0.88, tan.calculate(x2), DELTA);
        assertEquals(0.48 / 0.88, tan.calculate(x3), DELTA);
    }

    @Test
    @DisplayName("Tan should return NaN when cos(x) is NaN")
    void shouldReturnNaNWhenCosIsNaN() {
        double x = 1.0;

        MathFunction sinStub = new SinStub(Map.of(x, 0.84));
        MathFunction cosStub = new CosStub(Map.of());

        Tan tan = new Tan(sinStub, cosStub);

        assertTrue(Double.isNaN(tan.calculate(x)));
    }

    @Test
    @DisplayName("Tan should return NaN when cos(x) is approximately zero")
    void shouldReturnNaNWhenCosIsAlmostZero() {
        double x = 1.0;

        MathFunction sinStub = new SinStub(Map.of(x, 0.84));
        MathFunction cosStub = new CosStub(Map.of(x, 1e-12));

        Tan tan = new Tan(sinStub, cosStub);

        assertTrue(Double.isNaN(tan.calculate(x)));
    }
}