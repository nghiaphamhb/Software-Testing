package stubs;

import functions.MathFunction;

import java.util.Map;

/* This tabular maps point to his function's value */
public class TableStub implements MathFunction {
    private final Map<Double, Double> table;

    public TableStub(Map<Double, Double> table) {
        this.table = table;
    }

    @Override
    public double calculate(double x) {
        return table.getOrDefault(x, Double.NaN);
    }
}