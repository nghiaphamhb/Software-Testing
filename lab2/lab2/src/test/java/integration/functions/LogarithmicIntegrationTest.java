package integration.functions;

import functions.FunctionSystem;
import functions.MathFunction;
import functions.log.Log10;
import functions.log.Log2;
import functions.log.Log5;
import functions.log.Ln;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stubs.log.LnStub;
import stubs.log.Log10Stub;
import stubs.log.Log2Stub;
import stubs.log.Log5Stub;
import stubs.trig.SecStub;
import stubs.trig.SinStub;
import stubs.trig.TanStub;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/* Logarithmic branch of Full System IntegrationTest */
class LogarithmicIntegrationTest {

    private static final double DELTA = 1e-6;

    @Test
    @DisplayName("FunctionSystem should work with the fully integrated logarithmic branch and stubbed trigonometric branch")
    void shouldWorkWithFullyIntegratedLogBranch() {
        MathFunction ln = new Ln(1e-12);
        MathFunction log2 = new Log2(ln);
        MathFunction log5 = new Log5(ln);
        MathFunction log10 = new Log10(ln);

        double x = 2.0;

        MathFunction sinStub = new SinStub(Map.of());
        MathFunction secStub = new SecStub(Map.of());
        MathFunction tanStub = new TanStub(Map.of());

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                ln, log2, log5, log10
        );

        double lnValue = Math.log(x);
        double log2Value = Math.log(x) / Math.log(2.0);
        double log5Value = Math.log(x) / Math.log(5.0);
        double log10Value = Math.log10(x);

        double expected = (Math.pow((log10Value + lnValue) / Math.pow(log2Value, 3), 3) / log2Value)
                * Math.pow(log5Value, 3);

        assertEquals(expected, system.calculate(x), DELTA);
    }

    @Test
    @DisplayName("FunctionSystem should return NaN when the integrated logarithmic branch reaches log2(x) equal to zero")
    void shouldReturnNaNWhenIntegratedLogBranchHasZeroLog2() {
        MathFunction ln = new Ln(1e-12);
        MathFunction log2 = new Log2(ln);
        MathFunction log5 = new Log5(ln);
        MathFunction log10 = new Log10(ln);

        MathFunction sinStub = new SinStub(Map.of());
        MathFunction secStub = new SecStub(Map.of());
        MathFunction tanStub = new TanStub(Map.of());

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                ln, log2, log5, log10
        );

        assertTrue(Double.isNaN(system.calculate(1.0)));
    }

    @Test
    @DisplayName("FunctionSystem should ignore the trigonometric branch for positive arguments")
    void shouldIgnoreTrigBranchForPositiveArguments() {
        double x = 5.0;

        MathFunction sin = new SinStub(Map.of());
        MathFunction sec = new SecStub(Map.of());
        MathFunction tan = new TanStub(Map.of());

        MathFunction lnStub = new LnStub(Map.of(x, 2.0));
        MathFunction log2Stub = new Log2Stub(Map.of(x, 4.0));
        MathFunction log5Stub = new Log5Stub(Map.of(x, 1.0));
        MathFunction log10Stub = new Log10Stub(Map.of(x, 3.0));

        FunctionSystem system = new FunctionSystem(
                sin, sec, tan,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        double expected = (Math.pow((3.0 + 2.0) / Math.pow(4.0, 3), 3) / 4.0) * Math.pow(1.0, 3);

        assertEquals(expected, system.calculate(x), DELTA);
    }
}