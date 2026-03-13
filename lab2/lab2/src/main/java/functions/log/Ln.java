package functions.log;

import functions.MathFunction;

/* Basic logarithmic module */
public class Ln implements MathFunction {
    private final double eps;

    public Ln(double eps) {
        if (eps <= 0) {
            throw new IllegalArgumentException("eps must be > 0");
        }
        this.eps = eps;
    }

    @Override
    public double calculate(double x) {
        if (Double.isNaN(x) || x <= 0.0) {
            return Double.NaN;
        }

        if (x == 1.0) {
            return 0.0;
        }

        return lnSeries(x);
    }

    /*
     * ln(x) = 2 * (z + z^3/3 + z^5/5 + ...),
     * where z = (x - 1) / (x + 1)
     */
    private double lnSeries(double x) {
        double z = (x - 1.0) / (x + 1.0);
        double z2 = z * z;

        double power = z;   // z^(2k+1)
        double sum = 0.0;
        int n = 1;

        while (Math.abs(2.0 * power / n) >= eps) {
            sum += power / n;
            power *= z2;
            n += 2;
        }

        return 2.0 * sum;
    }
}