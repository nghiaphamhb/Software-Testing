package functions.trig.module;

import functions.MathFunction;
import functions.trig.Sec;

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
class SecTest {
    @Mock
    MathFunction cos;

    @Test
    @DisplayName("Sec should be calculated as 1 divided by cos(x)")
    void shouldCalculateAsOneDividedByCos() {
        double x1 = 0.0;
        double x2 = Math.PI / 3;
        double x3 = Math.PI;

        when(cos.calculate(x1)).thenReturn(1.0);
        when(cos.calculate(x2)).thenReturn(0.5);
        when(cos.calculate(x3)).thenReturn(-1.0);

        Sec sec = new Sec(cos);

        assertEquals(1.0, sec.calculate(x1), 1e-9);
        assertEquals(2.0, sec.calculate(x2), 1e-9);
        assertEquals(-1.0, sec.calculate(x3), 1e-9);

        verify(cos).calculate(x1);
        verify(cos).calculate(x2);
        verify(cos).calculate(x3);
    }

    @Test
    @DisplayName("Sec should return NaN when cos(x) is approximately zero")
    void shouldReturnNaNWhenCosIsAlmostZero() {
        double x = Math.PI/2;
        when(cos.calculate(x)).thenReturn(1e-12);

        Sec sec = new Sec(cos);

        assertTrue(Double.isNaN(sec.calculate(x)));

        verify(cos).calculate(x);
    }
}