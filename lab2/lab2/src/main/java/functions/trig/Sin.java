package functions.trig;

import functions.MathFunction;

/* Basic trigonometric module */
public class Sin implements MathFunction{
    private final double eps;

    public Sin(double eps){
        if (eps <= 0) {
            throw new IllegalArgumentException("eps must be > 0");
        }
        this.eps = eps;
    }
    
    @Override
    public double calculate(double x) {
        double normalizedX = normalize(x);

        double term = normalizedX;
        double sum = normalizedX;
        int k = 0;

        while (Math.abs(term) >= eps) {
            double denom = (2.0 * k + 2.0) * (2.0 * k + 3.0);
            term = -term * normalizedX * normalizedX / denom;
            sum += term;
            k++;
        }

        return sum;
    }

    // normalize to range [−π,π]
    private double normalize(double x) {
        double twoPi = 2.0 * Math.PI;
        x = x % twoPi;

        if (x > Math.PI) {
            x -= twoPi;
        } else if (x < -Math.PI) {
            x += twoPi;
        }

        return x;
    }
}
