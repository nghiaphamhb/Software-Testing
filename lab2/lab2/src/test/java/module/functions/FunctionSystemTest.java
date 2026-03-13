package module.functions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import functions.FunctionSystem;
import functions.MathFunction;
import stubs.TableStub;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionSystemTest {

    private static final double DELTA = 1e-9;

    @Test
    @DisplayName("FunctionSystem should use the trigonometric branch when x is less than zero")
    void shouldUseTrigonometricBranchForNonPositiveX() {
        double x = -1.0;

        MathFunction sinStub = new TableStub(Map.of(x, 1.0));
        MathFunction secStub = new TableStub(Map.of(x, 2.0));
        MathFunction tanStub = new TableStub(Map.of(x, 3.0));

        MathFunction lnStub = new TableStub(Map.of());
        MathFunction log2Stub = new TableStub(Map.of());
        MathFunction log5Stub = new TableStub(Map.of());
        MathFunction log10Stub = new TableStub(Map.of());

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        double expected = ((2.0 * 2.0 + 3.0 * 3.0 + 1.0) * Math.pow(3.0, 3)) / 3.0;

        assertEquals(expected, system.calculate(x), DELTA);
    }

    @Test
    @DisplayName("FunctionSystem should use the logarithmic branch when x is greater than zero")
    void shouldUseLogarithmicBranchForPositiveX() {
        double x = 2.0;

        MathFunction sinStub = new TableStub(Map.of());
        MathFunction secStub = new TableStub(Map.of());
        MathFunction tanStub = new TableStub(Map.of());

        MathFunction lnStub = new TableStub(Map.of(x, 2.0));
        MathFunction log2Stub = new TableStub(Map.of(x, 4.0));
        MathFunction log5Stub = new TableStub(Map.of(x, 5.0));
        MathFunction log10Stub = new TableStub(Map.of(x, 3.0));

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        double left = Math.pow((3.0 + 2.0) / Math.pow(4.0, 3), 3);
        double right = Math.pow(5.0, 3) / 4.0;
        double expected = left * right;

        assertEquals(expected, system.calculate(x), DELTA);
    }

    @Test
    @DisplayName("FunctionSystem should use the trigonometric branch at x equals zero")
    void shouldUseTrigonometricBranchAtZero() {
        double x = 0.0;

        MathFunction sinStub = new TableStub(Map.of(x, 2.0));
        MathFunction secStub = new TableStub(Map.of(x, 3.0));
        MathFunction tanStub = new TableStub(Map.of(x, 4.0));

        MathFunction lnStub = new TableStub(Map.of(x, 100.0));
        MathFunction log2Stub = new TableStub(Map.of(x, 100.0));
        MathFunction log5Stub = new TableStub(Map.of(x, 100.0));
        MathFunction log10Stub = new TableStub(Map.of(x, 100.0));

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        double expected = ((3.0 * 3.0 + 4.0 * 4.0 + 2.0) * Math.pow(4.0, 3)) / 4.0;

        assertEquals(expected, system.calculate(x), DELTA);
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the trigonometric branch when sec(x) is NaN")
    void shouldReturnNaNInTrigBranchWhenSecDependencyIsNaN() {
        double x = -2.0;

        MathFunction sinStub = new TableStub(Map.of(x, 1.0));
        MathFunction secStub = new TableStub(Map.of());
        MathFunction tanStub = new TableStub(Map.of(x, 2.0));

        MathFunction lnStub = new TableStub(Map.of());
        MathFunction log2Stub = new TableStub(Map.of());
        MathFunction log5Stub = new TableStub(Map.of());
        MathFunction log10Stub = new TableStub(Map.of());

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        assertTrue(Double.isNaN(system.calculate(x)));
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the trigonometric branch when tan(x) is NaN")
    void shouldReturnNaNInTrigBranchWhenTanDependencyIsNaN() {
        double x = -2.0;

        MathFunction sinStub = new TableStub(Map.of(x, 1.0));
        MathFunction secStub = new TableStub(Map.of(x, 2.0));
        MathFunction tanStub = new TableStub(Map.of());

        MathFunction lnStub = new TableStub(Map.of());
        MathFunction log2Stub = new TableStub(Map.of());
        MathFunction log5Stub = new TableStub(Map.of());
        MathFunction log10Stub = new TableStub(Map.of());

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        assertTrue(Double.isNaN(system.calculate(x)));
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the trigonometric branch when sin(x) is NaN")
    void shouldReturnNaNInTrigBranchWhenSinDependencyIsNaN() {
        double x = -2.0;

        MathFunction sinStub = new TableStub(Map.of());
        MathFunction secStub = new TableStub(Map.of(x, 1.0));
        MathFunction tanStub = new TableStub(Map.of(x, 2.0));

        MathFunction lnStub = new TableStub(Map.of());
        MathFunction log2Stub = new TableStub(Map.of());
        MathFunction log5Stub = new TableStub(Map.of());
        MathFunction log10Stub = new TableStub(Map.of());

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        assertTrue(Double.isNaN(system.calculate(x)));
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the trigonometric branch when tan(x) is approximately zero")
    void shouldReturnNaNInTrigBranchWhenTanIsAlmostZero() {
        double x = -2.0;

        MathFunction sinStub = new TableStub(Map.of(x, 1.0));
        MathFunction secStub = new TableStub(Map.of(x, 2.0));
        MathFunction tanStub = new TableStub(Map.of(x, 1e-12));

        MathFunction lnStub = new TableStub(Map.of());
        MathFunction log2Stub = new TableStub(Map.of());
        MathFunction log5Stub = new TableStub(Map.of());
        MathFunction log10Stub = new TableStub(Map.of());

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        assertTrue(Double.isNaN(system.calculate(x)));
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the logarithmic branch when log10(x) is NaN")
    void shouldReturnNaNInLogBranchWhenLog10DependencyIsNaN() {
        double x = 3.0;

        MathFunction sinStub = new TableStub(Map.of());
        MathFunction secStub = new TableStub(Map.of());
        MathFunction tanStub = new TableStub(Map.of());

        MathFunction lnStub = new TableStub(Map.of(x, 1.0));
        MathFunction log10Stub = new TableStub(Map.of());
        MathFunction log5Stub = new TableStub(Map.of(x, 2.0));
        MathFunction log2Stub = new TableStub(Map.of(x, 3.0));

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        assertTrue(Double.isNaN(system.calculate(x)));
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the logarithmic branch when ln(x) is NaN")
    void shouldReturnNaNInLogBranchWhenLnDependencyIsNaN() {
        double x = 3.0;

        MathFunction sinStub = new TableStub(Map.of());
        MathFunction secStub = new TableStub(Map.of());
        MathFunction tanStub = new TableStub(Map.of());

        MathFunction lnStub = new TableStub(Map.of());
        MathFunction log10Stub = new TableStub(Map.of(x, 1.0));
        MathFunction log5Stub = new TableStub(Map.of(x, 2.0));
        MathFunction log2Stub = new TableStub(Map.of(x, 3.0));

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        assertTrue(Double.isNaN(system.calculate(x)));
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the logarithmic branch when log2(x) is NaN")
    void shouldReturnNaNInLogBranchWhenLog2DependencyIsNaN() {
        double x = 3.0;

        MathFunction sinStub = new TableStub(Map.of());
        MathFunction secStub = new TableStub(Map.of());
        MathFunction tanStub = new TableStub(Map.of());

        MathFunction lnStub = new TableStub(Map.of(x, 1.0));
        MathFunction log10Stub = new TableStub(Map.of(x, 3.0));
        MathFunction log5Stub = new TableStub(Map.of(x, 2.0));
        MathFunction log2Stub = new TableStub(Map.of());

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        assertTrue(Double.isNaN(system.calculate(x)));
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the logarithmic branch when log5(x) is NaN")
    void shouldReturnNaNInLogBranchWhenLog5DependencyIsNaN() {
        double x = 3.0;

        MathFunction sinStub = new TableStub(Map.of());
        MathFunction secStub = new TableStub(Map.of());
        MathFunction tanStub = new TableStub(Map.of());

        MathFunction lnStub = new TableStub(Map.of(x, 1.0));
        MathFunction log10Stub = new TableStub(Map.of(x, 2.0));
        MathFunction log5Stub = new TableStub(Map.of());
        MathFunction log2Stub = new TableStub(Map.of(x, 3.0));

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        assertTrue(Double.isNaN(system.calculate(x)));
    }

    @Test
    @DisplayName("FunctionSystem should return NaN in the logarithmic branch when log2(x) is approximately zero")
    void shouldReturnNaNInLogBranchWhenLog2IsAlmostZero() {
        double x = 1.0;

        MathFunction sinStub = new TableStub(Map.of());
        MathFunction secStub = new TableStub(Map.of());
        MathFunction tanStub = new TableStub(Map.of());

        MathFunction lnStub = new TableStub(Map.of(x, 0.0));
        MathFunction log2Stub = new TableStub(Map.of(x, 1e-12));
        MathFunction log5Stub = new TableStub(Map.of(x, 0.0));
        MathFunction log10Stub = new TableStub(Map.of(x, 0.0));

        FunctionSystem system = new FunctionSystem(
                sinStub, secStub, tanStub,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        assertTrue(Double.isNaN(system.calculate(x)));
    }
}