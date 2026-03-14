package functions.log;

import functions.MathFunction;

public class Log10 implements MathFunction{
    private final MathFunction ln;

    public Log10(MathFunction ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        // log_10(x) = ln(x)/ln(10)
        double lnValue = ln.calculate(x);
        double ln10Value = ln.calculate(10.0);
        if (Double.isNaN(lnValue)) {
            return Double.NaN;
        }
        return lnValue / ln10Value;
    }
}
