package functions.log;

import functions.MathFunction;

public class Log10 implements MathFunction{
    private final MathFunction ln;
    private static final double LN_10 = Math.log(10.0);

    public Log10(MathFunction ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        // log_10(x) = ln(x)/ln(10)
        double lnValue = ln.calculate(x);
        if (Double.isNaN(lnValue)) {
            return Double.NaN;
        }
        return lnValue / LN_10;
    }
}
