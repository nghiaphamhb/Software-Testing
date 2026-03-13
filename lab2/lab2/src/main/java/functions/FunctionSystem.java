package functions;

public class FunctionSystem implements MathFunction{
    private final MathFunction sin;
    private final MathFunction sec;
    private final MathFunction tan;

    private final MathFunction ln;
    private final MathFunction log2;
    private final MathFunction log5;
    private final MathFunction log10;

    public FunctionSystem(MathFunction sin, MathFunction sec, MathFunction tan, MathFunction ln, MathFunction log2,
            MathFunction log5, MathFunction log10) {
        this.sin = sin;
        this.sec = sec;
        this.tan = tan;
        this.ln = ln;
        this.log2 = log2;
        this.log5 = log5;
        this.log10 = log10;
    }

    @Override
    public double calculate(double x) {
        if (x <= 0) {
            return calculateTrigBranch(x);
        } else {
            return calculateLogBranch(x);
        }
    }

    private double calculateTrigBranch(double x){
        double secValue = sec.calculate(x);
        double tanValue = tan.calculate(x);
        double sinValue = sin.calculate(x);

        if (Double.isNaN(secValue) || Double.isNaN(tanValue) || Double.isNaN(sinValue)) {
            return Double.NaN;
        }

        // the expression in the sample should be different 0
        if (Math.abs(tanValue) < 1e-10) {
            return Double.NaN;
        }

        // x <= 0 : (((((sec(x) * sec(x)) + (tan(x) ^ 2)) + sin(x)) * (tan(x) ^ 3)) / tan(x))
        return ((secValue * secValue + tanValue * tanValue + sinValue)
                * Math.pow(tanValue, 3)) / tanValue;
    }

    private double calculateLogBranch(double x) {
        double log10Value = log10.calculate(x);
        double lnValue = ln.calculate(x);
        double log2Value = log2.calculate(x);
        double log5Value = log5.calculate(x);

        if (Double.isNaN(log10Value) || Double.isNaN(lnValue)
                || Double.isNaN(log2Value) || Double.isNaN(log5Value)) {
            return Double.NaN;
        }

        // the expression in the sample should be different 0
        if (Math.abs(log2Value) < 1e-10) {
            return Double.NaN;
        }

        double left = Math.pow((log10Value + lnValue) / Math.pow(log2Value, 3), 3);
        double right = Math.pow(log5Value, 3) / log2Value;

        // x > 0 : (((((log_10(x) + ln(x)) / (log_2(x) ^ 3)) ^ 3) / log_2(x)) * (log_5(x) ^ 3))
        return left * right;
    }
}
