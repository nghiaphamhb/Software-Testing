package functions.trig.module;

import functions.MathFunction;
import functions.trig.Tan;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TanTest {
    @Mock
    MathFunction sin;
    @Mock
    MathFunction cos;

    @Test
    @DisplayName("Tan should be calculated as sin(x) divided by cos(x)")
    void shouldCalculateAsSinDividedByCos() {
        double x1 = -1.0;
        double x2 = -0.5;
        double x3 = 0.5;

        when(sin.calculate(x1)).thenReturn(-0.84);
        when(sin.calculate(x2)).thenReturn(-0.48);
        when(sin.calculate(x3)).thenReturn(0.48);

        when(cos.calculate(x1)).thenReturn(0.54);
        when(cos.calculate(x2)).thenReturn(0.88);
        when(cos.calculate(x3)).thenReturn(0.88);

        Tan tan = new Tan(sin, cos);

        assertEquals(-0.84 / 0.54, tan.calculate(x1), 1e-9);
        assertEquals(-0.48 / 0.88, tan.calculate(x2), 1e-9);
        assertEquals(0.48 / 0.88, tan.calculate(x3), 1e-9);

        verify(sin).calculate(x1);
        verify(sin).calculate(x2);
        verify(sin).calculate(x3);

        verify(cos).calculate(x1);
        verify(cos).calculate(x2);
        verify(cos).calculate(x3);
    }

    @Test
    @DisplayName("Tan should return NaN when cos(x) is approximately zero")
    void shouldReturnNaNWhenCosIsAlmostZero() {
        double x = 1.0;

        when(sin.calculate(x)).thenReturn(-0.84);
        when(cos.calculate(x)).thenReturn(1e-12);

        Tan tan = new Tan(sin, cos);

        assertTrue(Double.isNaN(tan.calculate(x)));

        verify(sin).calculate(x);
        verify(cos).calculate(x);
    }
}