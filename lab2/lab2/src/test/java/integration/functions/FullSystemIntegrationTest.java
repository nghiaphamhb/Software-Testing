package integration.functions;

import functions.FunctionSystem;
import functions.MathFunction;
import functions.log.Log10;
import functions.log.Log2;
import functions.log.Log5;
import functions.log.Ln;
import functions.trig.Cos;
import functions.trig.Sec;
import functions.trig.Sin;
import functions.trig.Tan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/* Module FullSystem integrates with all real modules */
class FullSystemIntegrationTest {

    private static final double DELTA = 1e-6;

    @Test
    @DisplayName("FunctionSystem should calculate the trigonometric branch correctly with all real modules")
    void shouldCalculateTrigBranchWithAllRealModules() {
        MathFunction sin = new Sin(1e-12);
        MathFunction cos = new Cos(sin);
        MathFunction tan = new Tan(sin, cos);
        MathFunction sec = new Sec(cos);

        MathFunction ln = new Ln(1e-12);
        MathFunction log2 = new Log2(ln);
        MathFunction log5 = new Log5(ln);
        MathFunction log10 = new Log10(ln);

        FunctionSystem system = new FunctionSystem(
                sin, sec, tan,
                ln, log2, log5, log10
        );

        double x = -Math.PI / 4;

        double sinValue = Math.sin(x);
        double cosValue = Math.cos(x);
        double tanValue = Math.tan(x);
        double secValue = 1.0 / cosValue;

        double expected = ((secValue * secValue + tanValue * tanValue + sinValue) * Math.pow(tanValue, 3)) / tanValue;

        assertEquals(expected, system.calculate(x), DELTA);
    }

    @Test
    @DisplayName("FunctionSystem should calculate the logarithmic branch correctly with all real modules")
    void shouldCalculateLogBranchWithAllRealModules() {
        MathFunction sin = new Sin(1e-12);
        MathFunction cos = new Cos(sin);
        MathFunction tan = new Tan(sin, cos);
        MathFunction sec = new Sec(cos);

        MathFunction ln = new Ln(1e-12);
        MathFunction log2 = new Log2(ln);
        MathFunction log5 = new Log5(ln);
        MathFunction log10 = new Log10(ln);

        FunctionSystem system = new FunctionSystem(
                sin, sec, tan,
                ln, log2, log5, log10
        );

        double x = 2.0;

        double lnValue = Math.log(x);
        double log2Value = Math.log(x) / Math.log(2.0);
        double log5Value = Math.log(x) / Math.log(5.0);
        double log10Value = Math.log10(x);

        double expected = (Math.pow((log10Value + lnValue) / Math.pow(log2Value, 3), 3) / log2Value)
                * Math.pow(log5Value, 3);

        assertEquals(expected, system.calculate(x), DELTA);
    }

    @Test
    @DisplayName("FunctionSystem should return NaN at undefined trigonometric points with all real modules")
    void shouldReturnNaNAtUndefinedTrigPoints() {
        MathFunction sin = new Sin(1e-12);
        MathFunction cos = new Cos(sin);
        MathFunction tan = new Tan(sin, cos);
        MathFunction sec = new Sec(cos);

        MathFunction ln = new Ln(1e-12);
        MathFunction log2 = new Log2(ln);
        MathFunction log5 = new Log5(ln);
        MathFunction log10 = new Log10(ln);

        FunctionSystem system = new FunctionSystem(
                sin, sec, tan,
                ln, log2, log5, log10
        );

        assertTrue(Double.isNaN(system.calculate(-Math.PI))); // tan(x) = 0
        assertTrue(Double.isNaN(system.calculate(-Math.PI / 2))); // tan(x) undefined
    }

    @Test
    @DisplayName("FunctionSystem should return NaN at undefined logarithmic points with all real modules")
    void shouldReturnNaNAtUndefinedLogPoints() {
        MathFunction sin = new Sin(1e-12);
        MathFunction cos = new Cos(sin);
        MathFunction tan = new Tan(sin, cos);
        MathFunction sec = new Sec(cos);

        MathFunction ln = new Ln(1e-12);
        MathFunction log2 = new Log2(ln);
        MathFunction log5 = new Log5(ln);
        MathFunction log10 = new Log10(ln);

        FunctionSystem system = new FunctionSystem(
                sin, sec, tan,
                ln, log2, log5, log10
        );

        assertTrue(Double.isNaN(system.calculate(1.0))); // log2(x) = 0
    }
}