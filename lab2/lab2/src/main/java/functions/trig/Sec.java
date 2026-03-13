package functions.trig;

import functions.MathFunction;

public class Sec implements MathFunction{
    private final MathFunction cos;

    public Sec(MathFunction cos){
        this.cos = cos;
    }

    @Override
    public double calculate(double x) {
        // sec(x) = 1/cos(x)
        double cosValue = cos.calculate(x);
        if (Double.isNaN(cosValue) || Math.abs(cosValue) < 1e-10) {
            return Double.NaN;
        }
        return 1.0 / cosValue;
    }
    
}
