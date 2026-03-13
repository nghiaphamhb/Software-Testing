package utils;

import functions.MathFunction;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvExporter {
    public void export(MathFunction function,
                double start,
                double end,
                double step,
                String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("x,result");

            for (double x = start; x <= end; x += step) {
                writer.println(x + "," + function.calculate(x));
            }
        }
    }
}
