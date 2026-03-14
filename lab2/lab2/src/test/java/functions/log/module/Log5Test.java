package functions.log.module;

import functions.MathFunction;
import functions.log.Log5;

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
class Log5Test {
    @Mock
    MathFunction ln;

    @Test
    @DisplayName("Log5 should be calculated as ln(x) divided by ln(5)")
    void shouldCalculateAsLnDividedByLn5() {
        when(ln.calculate(5.0)).thenReturn(5.0); // ln(5)
        when(ln.calculate(25.0)).thenReturn(25.0);
        when(ln.calculate(50.0)).thenReturn(50.0); 

        Log5 log5 = new Log5(ln);

        assertEquals(5.0, log5.calculate(25.0), 1e-9);
        assertEquals(10.0, log5.calculate(50.0), 1e-9);

        verify(ln).calculate(25.0);
        verify(ln).calculate(50.0);
    }

    @Test
    @DisplayName("Log5 should return NaN when ln(x) is NaN")
    void shouldReturnNaNWhenLnIsNaN() {
        when(ln.calculate(5.0)).thenReturn(5.0); // ln(5)
        when(ln.calculate(0.0)).thenReturn(Double.NaN);

        Log5 log5 = new Log5(ln);

        assertTrue(Double.isNaN(log5.calculate(0.0)));

        verify(ln).calculate(5.0);
        verify(ln).calculate(0.0);
    }

}