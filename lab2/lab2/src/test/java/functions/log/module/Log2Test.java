package functions.log.module;

import functions.MathFunction;
import functions.log.Log2;

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
class Log2Test {
    @Mock
    MathFunction ln; 

    @Test
    @DisplayName("Log2 should be calculated as ln(x) divided by ln(2)")
    void shouldCalculateAsLnDividedByLn2() {
        double x1 = 1.0;
        double x2 = 3.0;

        when(ln.calculate(2.0)).thenReturn(2.0); // ln(2)
        when(ln.calculate(x1)).thenReturn(4.0);
        when(ln.calculate(x2)).thenReturn(6.0);

        Log2 log2 = new Log2(ln);

        assertEquals(2.0, log2.calculate(x1), 1e-9);
        assertEquals(3.0, log2.calculate(x2), 1e-9);

        verify(ln).calculate(x1);
        verify(ln).calculate(x2);
    }

    @Test
    @DisplayName("Log2 should return NaN when ln(x) is NaN")
    void shouldReturnNaNWhenLnIsNaN() {
        double x = 10.0;

        when(ln.calculate(2.0)).thenReturn(2.0); // ln(2)
        when(ln.calculate(x)).thenReturn(Double.NaN);

        Log2 log2 = new Log2(ln);

        assertTrue(Double.isNaN(log2.calculate(x)));

        verify(ln).calculate(x);
    }
}