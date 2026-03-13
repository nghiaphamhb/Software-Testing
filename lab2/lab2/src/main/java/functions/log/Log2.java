package functions.log;

import functions.MathFunction;

public class Log2 implements MathFunction{
    private final MathFunction ln;
    private static final double LN_2 = Math.log(2.0);

    public Log2(MathFunction ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        // log_2(x) = ln(x)/ln(2)
        double lnValue = ln.calculate(x);
        if (Double.isNaN(lnValue)) {
            return Double.NaN;
        }
        return lnValue / LN_2;
    }
}
