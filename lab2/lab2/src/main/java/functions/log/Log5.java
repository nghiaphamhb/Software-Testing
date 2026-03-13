package functions.log;

import functions.MathFunction;

public class Log5 implements MathFunction{
    private final MathFunction ln;
    private static final double LN_5 = Math.log(5.0);

    public Log5(MathFunction ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        // log_5(x) = ln(x)/ln(5)
        double lnValue = ln.calculate(x);
        if (Double.isNaN(lnValue)) {
            return Double.NaN;
        }
        return lnValue / LN_5;
    }
}
