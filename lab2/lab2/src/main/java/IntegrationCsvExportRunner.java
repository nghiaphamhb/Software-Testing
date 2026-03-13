import functions.FunctionSystem;
import functions.MathFunction;
import functions.log.Ln;
import functions.log.Log2;
import functions.log.Log5;
import functions.log.Log10;
import functions.trig.Cos;
import functions.trig.Sec;
import functions.trig.Sin;
import functions.trig.Tan;
import stubs.log.LnStub;
import stubs.log.Log10Stub;
import stubs.log.Log2Stub;
import stubs.log.Log5Stub;
import stubs.trig.SecStub;
import stubs.trig.SinStub;
import stubs.trig.TanStub;
import utils.CsvExporter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class IntegrationCsvExportRunner {

    private static final double EPS = 1e-12;
    private static final double STEP = 0.25;
    private static final String OUTPUT_DIR = "src/main/resources/";

    public static void main(String[] args) throws Exception {
        CsvExporter exporter = new CsvExporter();

        exportTrigIntegrationStage(exporter); // range [-8.0, 0.0]
        exportLogIntegrationStage(exporter); // range (0.0, 8.0]
        exportFullIntegrationStage(exporter); // range [-8.0, 8.0]

        plotCsv(
                OUTPUT_DIR + "integration-trig-system.csv",
                "Trigonometric Branch Integration",
                "integration-trig-system"
        );

        plotCsv(
                OUTPUT_DIR + "integration-log-system.csv",
                "Logarithmic Branch Integration",
                "integration-log-system"
        );

        plotCsv(
                OUTPUT_DIR + "integration-full-system.csv",
                "Full System Integration",
                "integration-full-system"
        );

        System.out.println("Integration CSV export and plotting completed.");
    }

    /**
     * Real trigonometric branch + stubbed logarithmic branch
     * Export only the interval where the trigonometric branch is used
     */
    private static void exportTrigIntegrationStage(CsvExporter exporter) throws Exception {
        MathFunction sin = new Sin(EPS);
        MathFunction cos = new Cos(sin);
        MathFunction tan = new Tan(sin, cos);
        MathFunction sec = new Sec(cos);

        MathFunction lnStub = new LnStub(Map.of());
        MathFunction log2Stub = new Log2Stub(Map.of());
        MathFunction log5Stub = new Log5Stub(Map.of());
        MathFunction log10Stub = new Log10Stub(Map.of());

        MathFunction system = new FunctionSystem(
                sin, sec, tan,
                lnStub, log2Stub, log5Stub, log10Stub
        );

        exporter.export(system, -8.0, 0.0, STEP, OUTPUT_DIR + "integration-trig-system.csv");
    }

    /**
     * Stubbed trigonometric branch + real logarithmic branch
     * Export only the interval where the trigonometric branch is used
     */
    private static void exportLogIntegrationStage(CsvExporter exporter) throws Exception {
        MathFunction sinStub = new SinStub(Map.of());
        MathFunction secStub = new SecStub(Map.of());
        MathFunction tanStub = new TanStub(Map.of());

        MathFunction ln = new Ln(EPS);
        MathFunction log2 = new Log2(ln);
        MathFunction log5 = new Log5(ln);
        MathFunction log10 = new Log10(ln);

        MathFunction system = new FunctionSystem(
                sinStub, secStub, tanStub,
                ln, log2, log5, log10
        );

        exporter.export(system, 0.05, 8.0, STEP, OUTPUT_DIR + "integration-log-system.csv");
    }

    /**
     * Full integration of all real modules.
     * Export the whole system on a combined interval.
     */
    private static void exportFullIntegrationStage(CsvExporter exporter) throws Exception {
        MathFunction sin = new Sin(EPS);
        MathFunction cos = new Cos(sin);
        MathFunction tan = new Tan(sin, cos);
        MathFunction sec = new Sec(cos);

        MathFunction ln = new Ln(EPS);
        MathFunction log2 = new Log2(ln);
        MathFunction log5 = new Log5(ln);
        MathFunction log10 = new Log10(ln);

        MathFunction system = new FunctionSystem(
                sin, sec, tan,
                ln, log2, log5, log10
        );

        exporter.export(system, -8.0, 8.0, STEP, OUTPUT_DIR + "integration-full-system.csv");
    }

    private static void plotCsv(String csvPath, String chartTitle, String outputName) throws Exception {
        List<Double> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvPath))) {
            String line = reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) {
                    continue;
                }

                double x = Double.parseDouble(parts[0].trim());
                double y = Double.parseDouble(parts[1].trim());

                if (!Double.isNaN(y) && !Double.isInfinite(y)) {
                    xData.add(x);
                    yData.add(y);
                }
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
        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setYAxisMax(8.0);

        chart.addSeries("f(x)", xData, yData);

        BitmapEncoder.saveBitmap(chart, OUTPUT_DIR + outputName, BitmapEncoder.BitmapFormat.PNG);
    }

}