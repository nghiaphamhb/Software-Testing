package functions.log;

import functions.MathFunction;

public class Log2 implements MathFunction{
    private final MathFunction ln;

    public Log2(MathFunction ln) {
        this.ln = ln;
    }

    @Override
    public double calculate(double x) {
        // log_2(x) = ln(x)/ln(2)
        double lnValue = ln.calculate(x);
        double ln2Value = ln.calculate(2.0);
        if (Double.isNaN(lnValue)) {
            return Double.NaN;
        }
        return lnValue / ln2Value;
    }
}
