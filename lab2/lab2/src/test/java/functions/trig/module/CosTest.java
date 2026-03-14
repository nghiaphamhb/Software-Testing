package functions.trig.module;

import functions.MathFunction;
import functions.trig.Cos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CosTest {
    @Mock
    MathFunction sin;

    @Test
    @DisplayName("Cos should be calculated using the sin(pi/2 - x) identity")
    void shouldCalculateCosViaSinShift() {
        when(sin.calculate(0.0)).thenReturn(0.0);
        when(sin.calculate(Math.PI/2)).thenReturn(1.0);
        when(sin.calculate(Math.PI)).thenReturn(0.0);
        when(sin.calculate(-Math.PI / 2)).thenReturn(-1.0);

        Cos cos = new Cos(sin);

        assertEquals(1.0, cos.calculate(0.0), 1e-9);
        assertEquals(0.0, cos.calculate(Math.PI / 2), 1e-9);
        assertEquals(0.0, cos.calculate(-Math.PI / 2), 1e-9);
        assertEquals(-1.0, cos.calculate(Math.PI), 1e-9);


    }
}