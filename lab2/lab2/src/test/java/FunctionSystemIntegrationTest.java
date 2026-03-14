import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class FunctionSystemIntegrationTest {
    private FunctionSystem system;

    @BeforeEach
    void setUp(){
        system = new FunctionSystem(1e-10);
    }

    @Test
    @DisplayName("Should calculate trig branch with real modules")
    void shouldCalculateTrigBranchWithRealModules() {
        double x = -1.0;
        double result = system.calculate(x);

        double sec = 1.0 / Math.cos(x);
        double tan = Math.tan(x);
        double sin = Math.sin(x);
        double expected = ((sec * sec + tan * tan + sin) * Math.pow(tan, 3)) / tan;

        assertEquals(expected, result, 1e-6);
    }

    @Test
    @DisplayName("Should calculate log branch with real modules")
    void shouldCalculateLogBranchWithRealModules() {
        double x = 5.0;
        double result = system.calculate(x);

        double log10 = Math.log10(x);
        double ln = Math.log(x);
        double log2 = Math.log(x) / Math.log(2);
        double log5 = Math.log(x) / Math.log(5);

        double left = Math.pow((log10 + ln) / Math.pow(log2, 3), 3);
        double right = Math.pow(log5, 3) / log2;
        double expected = left * right;

        assertEquals(expected, result, 1e-6);
    }

    @Test
    @DisplayName("Should return NaN at trig singularity")
    void shouldReturnNaNAtTrigSingularity() {
        double x = 0.0; // tan(0)=0
        assertTrue(Double.isNaN(system.calculate(x)));
    }

    @Test
    @DisplayName("Should return NaN at log singularity")
    void shouldReturnNaNAtLogSingularity() {
        double x = 1.0; // log2(1)=0
        assertTrue(Double.isNaN(system.calculate(x)));
    }
}