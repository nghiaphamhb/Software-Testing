import functions.MathFunction;
import functions.log.Ln;
import functions.log.Log2;
import functions.log.Log5;
import functions.log.Log10;
import functions.trig.Cos;
import functions.trig.Sec;
import functions.trig.Sin;
import functions.trig.Tan;
import utils.CsvExporter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class Main {

    private static final double EPS = 1e-12;
    private static final double BEGIN = -8.0;
    private static final double END = 8.0;
    private static final double STEP = 0.25;
    private static final String CSV_OUTPUT_DIR = "lab2/output/csv/";
    private static final String GRAPHIC_OUTPUT_DIR = "lab2/output/graph/";
    private static final CsvExporter exporter = new CsvExporter();

    public static void main(String[] args) throws Exception {
        MathFunction sin = new Sin(EPS);
        MathFunction cos = new Cos(sin);
        MathFunction tan = new Tan(sin, cos);
        MathFunction sec = new Sec(sin);

        MathFunction ln = new Ln(EPS);
        MathFunction log2 = new Log2(ln);
        MathFunction log5 = new Log5(ln);
        MathFunction log10 = new Log10(ln);

        MathFunction functionSystem = new FunctionSystem(EPS);

        exportCSVFile(sin, "Sin");
        exportCSVFile(cos, "Cos"); 
        exportCSVFile(tan, "Tan");
        exportCSVFile(sec, "Sec");

        exportCSVFile(ln, "Ln");
        exportCSVFile(log2, "Log2");
        exportCSVFile(log5, "Log5");
        exportCSVFile(log10, "Log10");

        exportCSVFile(functionSystem, "FunctionSystem");

        plotCsv(
                CSV_OUTPUT_DIR + "Sin.csv",
                "Sin(x)",
                "Sin",
                -4.0, 4.0
        );
        plotCsv(
                CSV_OUTPUT_DIR + "Cos.csv",
                "Cos(x)",
                "Cos",
                -4.0, 4.0
        );
        plotCsv(
                CSV_OUTPUT_DIR + "Tan.csv",
                "Tan(x)",
                "Tan",
                -4.0, 4.0
        );
        plotCsv(
                CSV_OUTPUT_DIR + "Sec.csv",
                "Sec(x)",
                "Sec",
                -4.0, 4.0
        );

        plotCsv(
                CSV_OUTPUT_DIR + "Ln.csv",
                "Ln(x)",
                "Ln",
                -4.0, 4.0
        );
        plotCsv(
                CSV_OUTPUT_DIR + "Log2.csv",
                "Log2(x)",
                "Log2",
                -4.0, 4.0
        );
        plotCsv(
                CSV_OUTPUT_DIR + "Log5.csv",
                "Log5(x)",
                "Log5",
                -4.0, 4.0
        );
        plotCsv(
                CSV_OUTPUT_DIR + "Log10.csv",
                "Log10",
                "Log10",
                -4.0, 4.0
        );

        plotCsv(
                CSV_OUTPUT_DIR + "FunctionSystem.csv",
                "Function System(x)",
                "FunctionSystem",
                0.0, 8.0
        );

        System.out.println("Integration CSV export and plotting completed.");
    }


    /* Export CSV file */
    private static void exportCSVFile(MathFunction function, String filename) throws Exception {
        exporter.export(function, BEGIN, END, STEP, CSV_OUTPUT_DIR + filename + ".csv");
    }

    /* Draw graphic */
    private static void plotCsv(String csvPath, String chartTitle, String outputName, double minY, double maxY) throws Exception {
        List<Double> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvPath))) {
            String line = reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                double x = Double.parseDouble(parts[0].trim());
                double y = Double.parseDouble(parts[1].trim());

                xData.add(x);
                yData.add(y);
            }
        }

        XYChart chart = new XYChartBuilder()
                .width(1000)
                .height(600)
                .title(chartTitle)
                .xAxisTitle("x")
                .yAxisTitle("f(x)")
                .build();

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setYAxisMin(minY);
        chart.getStyler().setYAxisMax(maxY);

        chart.addSeries("f(x)", xData, yData);

        BitmapEncoder.saveBitmap(chart, GRAPHIC_OUTPUT_DIR + outputName, BitmapEncoder.BitmapFormat.PNG);
    }

}