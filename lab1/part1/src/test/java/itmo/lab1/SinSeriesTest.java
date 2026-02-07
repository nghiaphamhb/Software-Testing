package itmo.lab1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SinSeriesTest {
    @Test
    @DisplayName("🧪 sin(0) = 0.0 🥼")
    void testZero() {
        double eps = 1e-12;
        double result = SinSeries.calculate(0.0, eps);
        assertEquals(0.0, result, 1e-12);
    }

    @Test
    @DisplayName("🧪 Special angles: pi/2, pi, -pi/2, -pi 🥼")
    void testSpecialAngles() {
        double eps = 1e-12;
        double delta = 1e-10;

        assertEquals(Math.sin(Math.PI / 2), SinSeries.calculate(Math.PI / 2, eps), delta);
        assertEquals(Math.sin(Math.PI),     SinSeries.calculate(Math.PI,     eps), delta);
        assertEquals(Math.sin(-Math.PI / 2),SinSeries.calculate(-Math.PI / 2,eps), delta);
        assertEquals(Math.sin(-Math.PI),    SinSeries.calculate(-Math.PI,    eps), delta);
    }

    @Test
    @DisplayName("🧪 Compare with Math.sin for several points 🥼")
    void testAgainstMathSin(){
        double[] tries = {-3, -1, -0.1, 0.1, 1, 2, 3};
        double eps = 1e-12;
        double delta = 1e-10;

        for(double x : tries){
            assertEquals(Math.sin(x), SinSeries.calculate(x, eps), delta);
        }
    }

    @Test
    @DisplayName("🧪 Odd property: sin(-x) = -sin(x) 🥼")
    void testOddProperty() {
        double eps = 1e-12;
        double delta = 1e-10;
        double x = 1.2345;

        double a = SinSeries.calculate(x, eps);
        double b = SinSeries.calculate(-x, eps);
        assertEquals(-a, b, delta);
    }

    @Test
    @DisplayName("🧪 Periodicity property: sin(x + 2pi) = sin(x) 🥼")
    void testPeriodicity() {
        double eps = 1e-12;
        double delta = 1e-10;
        double x = 0.7;

        double a = SinSeries.calculate(x, eps);
        double b = SinSeries.calculate(x + 2.0 * Math.PI, eps);
        assertEquals(a, b, delta);
    }

    @Test
    @DisplayName("🧪 Invalid eps should throw 🥼")
    void testInvalidEps() {
        assertThrows(IllegalArgumentException.class, () -> SinSeries.calculate(1.0, 0.0));
        assertThrows(IllegalArgumentException.class, () -> SinSeries.calculate(1.0, -1e-3));
    }

    @Test
    @DisplayName("🧪 Bigger eps gives rougher accuracy 🥼")
    void testBiggerEps() {
        double x = 1.0;
        double eps = 1e-3;
        assertEquals(Math.sin(x), SinSeries.calculate(x, eps), 1e-3);
    }
}