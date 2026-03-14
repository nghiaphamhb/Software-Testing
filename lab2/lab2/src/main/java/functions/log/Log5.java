package functions.log;

import functions.MathFunction;

public class Log5 implements MathFunction{
    private final MathFunction ln;

    public Log5(MathFunction ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        // log_5(x) = ln(x)/ln(5)
        double lnValue = ln.calculate(x);
        double ln5Value = ln.calculate(5.0);
        if (Double.isNaN(lnValue)) {
            return Double.NaN;
        }
        return lnValue / ln5Value;
    }
}
