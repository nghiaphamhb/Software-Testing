package functions.log.module;

import functions.MathFunction;
import functions.log.Log10;

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
class Log10Test {
    @Mock
    MathFunction ln;

    @Test
    @DisplayName("Log10 should be calculated as ln(x) divided by ln(10)")
    void shouldCalculateAsLnDividedByLn10() {
        when(ln.calculate(10.0)).thenReturn(10.0); // ln(10)
        when(ln.calculate(20.0)).thenReturn(20.0);
        when(ln.calculate(30.0)).thenReturn(30.0);

        Log10 log10 = new Log10(ln);

        assertEquals(2.0, log10.calculate(20.0), 1e-9);
        assertEquals(3.0, log10.calculate(30.0), 1e-9);

        verify(ln).calculate(20.0);
        verify(ln).calculate(30.0);
    }

    @Test
    @DisplayName("Log10 should return NaN when ln(x) is NaN")
    void shouldReturnNaNWhenLnIsNaN() {
        when(ln.calculate(10.0)).thenReturn(10.0); // ln(10)
        when(ln.calculate(0.0)).thenReturn(Double.NaN);

        Log10 log10 = new Log10(ln);

        assertTrue(Double.isNaN(log10.calculate(0.0)));

        verify(ln).calculate(0.0);
    }


}