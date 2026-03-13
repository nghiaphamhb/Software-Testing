package functions.trig;

import functions.MathFunction;

public class Cos implements MathFunction {
    private final MathFunction sin;

    public Cos(MathFunction sin) {
        this.sin = sin;
    }

    @Override
    public double calculate(double x) {
        // cos(x) = sin(pi/2 -x)
        return sin.calculate(Math.PI/2 - x);
    }
}
