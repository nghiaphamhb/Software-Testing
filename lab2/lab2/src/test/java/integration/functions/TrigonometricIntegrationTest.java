package integration.functions;

import functions.FunctionSystem;
import functions.MathFunction;
import functions.log.Log10;
import functions.log.Log2;
import functions.log.Log5;
import functions.trig.Cos;
import functions.trig.Sec;
import functions.trig.Sin;
import functions.trig.Tan;
import stubs.log.LnStub;
import stubs.log.Log10Stub;
import stubs.log.Log2Stub;
import stubs.log.Log5Stub;
import stubs.trig.SecStub;
import stubs.trig.SinStub;
import stubs.trig.TanStub;
import functions.log.Ln;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/* Trigonometric branch of Full System IntegrationTest */
class TrigonometricIntegrationTest {

    private static final double DELTA = 1e-6;

    @Test
    @DisplayName("FunctionSystem should work with the fully integrated trigonometric branch and stubbed logarithmic branch")
    void shouldWorkWithFullyIntegratedTrigBranch() {
        MathFunction sin = new Sin(1e-12);
        MathFunction cos = new Cos(sin);
        MathFunction tan = new Tan(sin, cos);
        MathFunction sec = new Sec(cos);

        MathFunction lnStub = new LnStub(Map.of());
        MathFunction log2Stub = new Log2Stub(Map.of());
        MathFunction log5Stub = new Log5Stub(Map.of());
        MathFunction log10Stub = new Log10Stub(Map.of());

        FunctionSystem system = new FunctionSystem(
                sin, sec, tan,
                lnStub, log2Stub, log5Stub, log10Stub
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
    @DisplayName("FunctionSystem should return NaN when the integrated trigonometric branch reaches tan(x) equal to zero")
    void shouldReturnNaNWhenIntegratedTrigBranchHasZeroTan() {
        MathFunction sin = new Sin(1e-12);
        MathFunction cos = new Cos(sin);
        MathFunction tan = new Tan(sin, cos);
        MathFunction sec = new Sec(cos);

        MathFunction lnStub = new LnStub(Map.of());
        MathFunction log2Stub = new Log2Stub(Map.of());
        MathFunction log5Stub = new Log5Stub(Map.of());
        MathFunction log10Stub = new Log10Stub(Map.of());

        FunctionSystem system = new FunctionSystem(
                sin, sec, tan,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        assertTrue(Double.isNaN(system.calculate(-Math.PI)));
    }

    @Test
    @DisplayName("FunctionSystem should return NaN when the integrated trigonometric branch reaches an undefined point")
    void shouldReturnNaNWhenIntegratedTrigBranchIsUndefined() {
        MathFunction sin = new Sin(1e-12);
        MathFunction cos = new Cos(sin);
        MathFunction tan = new Tan(sin, cos);
        MathFunction sec = new Sec(cos);

        MathFunction lnStub = new LnStub(Map.of());
        MathFunction log2Stub = new Log2Stub(Map.of());
        MathFunction log5Stub = new Log5Stub(Map.of());
        MathFunction log10Stub = new Log10Stub(Map.of());

        FunctionSystem system = new FunctionSystem(
                sin, sec, tan,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        assertTrue(Double.isNaN(system.calculate(-Math.PI / 2)));
    }

    @Test
    @DisplayName("FunctionSystem should ignore the logarithmic branch for negative arguments")
    void shouldIgnoreLogBranchForNegativeArguments() {
        double x = -1.0;

        MathFunction sinStub = new SinStub(Map.of(x, 1.0));
        MathFunction secStub = new SecStub(Map.of(x, 2.0));
        MathFunction tanStub = new TanStub(Map.of(x, 3.0));

        MathFunction ln = new Ln(1e-12);
        MathFunction log2 = new Log2(ln);
        MathFunction log5 = new Log5(ln);
        MathFunction log10 = new Log10(ln);

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                ln, log2, log5, log10
        );

        double expected = ((2.0 * 2.0 + 3.0 * 3.0 + 1.0) * Math.pow(3.0, 3)) / 3.0;

        assertEquals(expected, system.calculate(x), DELTA);
    }
}
