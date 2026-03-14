

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import functions.MathFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FunctionSystemTest {
    @Mock 
    MathFunction sin;
    @Mock 
    MathFunction sec;
    @Mock 
    MathFunction tan;

    @Mock 
    MathFunction ln;
    @Mock 
    MathFunction log2;
    @Mock 
    MathFunction log5;
    @Mock 
    MathFunction log10;

    private static final double DELTA = 1e-9;
    private FunctionSystem system;

    @BeforeEach
    void setUp() {
        system = new FunctionSystem(sin, sec, tan, ln, log2, log5, log10);
    }

    @Test
    @DisplayName("FunctionSystem should use the trigonometric branch when x is less than zero")
    void shouldUseTrigonometricBranchForNonPositiveX() {
        double x = -1.0;

        when(sin.calculate(x)).thenReturn(1.0);
        when(sec.calculate(x)).thenReturn(2.0);
        when(tan.calculate(x)).thenReturn(3.0);

        double result = system.calculate(x);

        double expected = ((2.0 * 2.0 + 3.0 * 3.0 + 1.0) * Math.pow(3.0, 3)) / 3.0;
        assertEquals(expected, result, DELTA);

        verify(sin).calculate(x);
        verify(sec).calculate(x);
        verify(tan).calculate(x);

        verifyNoInteractions(ln, log2, log5, log10);
    }

    @Test
    @DisplayName("FunctionSystem should use the trigonometric branch at x equals zero")
    void shouldUseTrigonometricBranchAtZero() {
        double x = 0.0;

        when(sin.calculate(x)).thenReturn(2.0);
        when(sec.calculate(x)).thenReturn(3.0);
        when(tan.calculate(x)).thenReturn(4.0);

        double result = system.calculate(x);

        double expected = ((3.0 * 3.0 + 4.0 * 4.0 + 2.0) * Math.pow(4.0, 3)) / 4.0;

        assertEquals(expected, result, DELTA);

        verify(sin).calculate(x);
        verify(sec).calculate(x);
        verify(tan).calculate(x);

        verifyNoInteractions(ln, log2, log5, log10);
    }

    @Test
    @DisplayName("FunctionSystem should use the logarithmic branch when x is greater than zero")
    void shouldUseLogarithmicBranchForPositiveX() {
        double x = 2.0;

        when(ln.calculate(x)).thenReturn(2.0);
        when(log2.calculate(x)).thenReturn(4.0);
        when(log5.calculate(x)).thenReturn(5.0);
        when(log10.calculate(x)).thenReturn(3.0);

        double result = system.calculate(x);

        double left = Math.pow((3.0 + 2.0) / Math.pow(4.0, 3), 3);
        double right = Math.pow(5.0, 3) / 4.0;
        double expected = left * right;

        assertEquals(expected, result, DELTA);

        verify(ln).calculate(x);
        verify(log2).calculate(x);
        verify(log5).calculate(x);
        verify(log10).calculate(x);

        verifyNoInteractions(sin, tan, sec);
    }
    
    @Test
    @DisplayName("FunctionSystem should return NaN in the trigonometric branch when sin(x) is NaN")
    void shouldReturnNaNInTrigBranchWhenSinDependencyIsNaN() {
        double x = -2.0;

        when(sin.calculate(x)).thenReturn(Double.NaN);
        when(tan.calculate(x)).thenReturn(2.0);
        when(sec.calculate(x)).thenReturn(1.0);

        double result = system.calculate(x);

        assertTrue(Double.isNaN(result));
        
        verify(sin).calculate(x);
        verify(sec).calculate(x);
        verify(tan).calculate(x);

        verifyNoInteractions(ln, log2, log5, log10);
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the trigonometric branch when tan(x) is NaN")
    void shouldReturnNaNInTrigBranchWhenTanDependencyIsNaN() {
        double x = -2.0;

        when(sin.calculate(x)).thenReturn(1.0);
        when(tan.calculate(x)).thenReturn(Double.NaN);
        when(sec.calculate(x)).thenReturn(2.0);

        double result = system.calculate(x);

        assertTrue(Double.isNaN(result));
        
        verify(sin).calculate(x);
        verify(sec).calculate(x);
        verify(tan).calculate(x);

        verifyNoInteractions(ln, log2, log5, log10);
    }

    
    @Test
    @DisplayName("FunctionSystem should return NaN in the trigonometric branch when sec(x) is NaN")
    void shouldReturnNaNInTrigBranchWhenSecDependencyIsNaN() {
        double x = -2.0;

        when(sin.calculate(x)).thenReturn(1.0);
        when(tan.calculate(x)).thenReturn(2.0);
        when(sec.calculate(x)).thenReturn(Double.NaN);

        double result = system.calculate(x);

        assertTrue(Double.isNaN(result));
        
        verify(sin).calculate(x);
        verify(sec).calculate(x);
        verify(tan).calculate(x);

        verifyNoInteractions(ln, log2, log5, log10);
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the trigonometric branch when tan(x) is approximately zero")
    void shouldReturnNaNInTrigBranchWhenTanIsAlmostZero() {
        double x = -2.0;

        when(sin.calculate(x)).thenReturn(1.0);
        when(sec.calculate(x)).thenReturn(2.0);
        when(tan.calculate(x)).thenReturn(1e-12);

        double result = system.calculate(x);

        assertTrue(Double.isNaN(result));
        
        verify(sin).calculate(x);
        verify(sec).calculate(x);
        verify(tan).calculate(x);

        verifyNoInteractions(ln, log2, log5, log10);
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the logarithmic branch when log10(x) is NaN")
    void shouldReturnNaNInLogBranchWhenLog10DependencyIsNaN() {
        double x = 3.0;

        when(ln.calculate(x)).thenReturn(1.0);
        when(log2.calculate(x)).thenReturn(2.0);
        when(log5.calculate(x)).thenReturn(3.0);
        when(log10.calculate(x)).thenReturn(Double.NaN);

        double result = system.calculate(x);

        assertTrue(Double.isNaN(result));
        
        verify(ln).calculate(x);
        verify(log2).calculate(x);
        verify(log5).calculate(x);
        verify(log10).calculate(x);

        verifyNoInteractions(sin, tan, sec);
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the logarithmic branch when ln(x) is NaN")
    void shouldReturnNaNInLogBranchWhenLnDependencyIsNaN() {
        double x = 3.0;

        when(log2.calculate(x)).thenReturn(2.0);
        when(log5.calculate(x)).thenReturn(3.0);
        when(log10.calculate(x)).thenReturn(4.0);
        when(ln.calculate(x)).thenReturn(Double.NaN);

        double result = system.calculate(x);    
        assertTrue(Double.isNaN(result));

        verify(ln).calculate(x);
        verify(log2).calculate(x);
        verify(log5).calculate(x);
        verify(log10).calculate(x);

        verifyNoInteractions(sin, tan, sec);
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the logarithmic branch when log2(x) is NaN")
    void shouldReturnNaNInLogBranchWhenLog2DependencyIsNaN() {
        double x = 3.0;

        when(log5.calculate(x)).thenReturn(3.0);
        when(log10.calculate(x)).thenReturn(4.0);
        when(ln.calculate(x)).thenReturn(5.0);
        when(log2.calculate(x)).thenReturn(Double.NaN);

        double result = system.calculate(x);    
        assertTrue(Double.isNaN(result));

        verify(ln).calculate(x);
        verify(log2).calculate(x);
        verify(log5).calculate(x);
        verify(log10).calculate(x);

        verifyNoInteractions(sin, tan, sec);
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the logarithmic branch when log5(x) is NaN")
    void shouldReturnNaNInLogBranchWhenLog5DependencyIsNaN() {
        double x = 3.0;

        when(log10.calculate(x)).thenReturn(4.0);
        when(ln.calculate(x)).thenReturn(5.0);
        when(log2.calculate(x)).thenReturn(6.0);
        when(log5.calculate(x)).thenReturn(Double.NaN);

        double result = system.calculate(x);    
        assertTrue(Double.isNaN(result));

        verify(ln).calculate(x);
        verify(log2).calculate(x);
        verify(log5).calculate(x);
        verify(log10).calculate(x);

        verifyNoInteractions(sin, tan, sec);
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the logarithmic branch when log2(x) is approximately zero")
    void shouldReturnNaNInLogBranchWhenLog2IsAlmostZero() {
        double x = 3.0;

        when(ln.calculate(x)).thenReturn(4.0);
        when(log5.calculate(x)).thenReturn(5.0);
        when(log10.calculate(x)).thenReturn(6.0);
        when(log2.calculate(x)).thenReturn(1e-12);

        double result = system.calculate(x);    
        assertTrue(Double.isNaN(result));

        verify(ln).calculate(x);
        verify(log2).calculate(x);
        verify(log5).calculate(x);
        verify(log10).calculate(x);

        verifyNoInteractions(sin, tan, sec);
    }
}