package functions.trig;

import functions.MathFunction;

public class Tan implements MathFunction{
    private final MathFunction sin;
    private final MathFunction cos;

    public Tan(MathFunction sin, MathFunction cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public double calculate(double x) {
        // tan(x)=sin(x)/cos(x)
        double cosValue = cos.calculate(x);
        double sinvalue = sin.calculate(x);

        if (Math.abs(cosValue) < 1e-10) {
            return Double.NaN;
        }
        return  sinvalue / cosValue;
    }
}
