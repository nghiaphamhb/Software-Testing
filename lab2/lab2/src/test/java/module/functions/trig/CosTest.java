package module.functions.trig;

import functions.MathFunction;
import functions.trig.Cos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stubs.trig.SinStub;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CosTest {

    private static final double DELTA = 1e-9;

    @Test
    @DisplayName("Cos should be calculated using the sin(pi/2 - x) identity")
    void shouldCalculateCosViaSinShift() {
        MathFunction sinStub = new SinStub(Map.of(
                Math.PI / 2, 1.0,
                0.0, 0.0,
                Math.PI, 0.0,
                -Math.PI / 2, -1.0
        ));

        Cos cos = new Cos(sinStub);

        assertEquals(1.0, cos.calculate(0.0), DELTA);
        assertEquals(0.0, cos.calculate(Math.PI / 2), DELTA);
        assertEquals(0.0, cos.calculate(-Math.PI / 2), DELTA);
        assertEquals(-1.0, cos.calculate(Math.PI), DELTA);
    }

    @Test
    @DisplayName("Cos should return NaN when the underlying sin function returns NaN")
    void shouldReturnNaNWhenUnderlyingSinReturnsNaN() {
        MathFunction sinStub = new SinStub(Map.of());

        Cos cos = new Cos(sinStub);

        assertTrue(Double.isNaN(cos.calculate(1.23)));
    }
}