package module.utils;

import functions.MathFunction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stubs.TableStub;
import utils.CsvExporter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvExporterTest {

    @Test
    @DisplayName("CsvExporter should write header and all calculated values to the file")
    void shouldWriteHeaderAndAllCalculatedValues() throws IOException {
        MathFunction function = new TableStub(Map.of(
                0.0, 1.0,
                1.0, 2.0,
                2.0, 3.0
        ));

        Path tempFile = Files.createTempFile("csv-export-test", ".csv");

        CsvExporter exporter = new CsvExporter();
        exporter.export(function, 0.0, 2.0, 1.0, tempFile.toString());

        List<String> lines = Files.readAllLines(tempFile);

        assertEquals(4, lines.size());
        assertEquals("x,result", lines.get(0));
        assertEquals("0.0,1.0", lines.get(1));
        assertEquals("1.0,2.0", lines.get(2));
        assertEquals("2.0,3.0", lines.get(3));
    }

    @Test
    @DisplayName("CsvExporter should write NaN when the function returns NaN")
    void shouldWriteNaNWhenFunctionReturnsNaN() throws IOException {
        MathFunction function = new TableStub(Map.of(
                0.0, 1.0
        ));

        Path tempFile = Files.createTempFile("csv-export-test", ".csv");

        CsvExporter exporter = new CsvExporter();
        exporter.export(function, 0.0, 1.0, 1.0, tempFile.toString());

        List<String> lines = Files.readAllLines(tempFile);

        assertEquals("x,result", lines.get(0));
        assertEquals("0.0,1.0", lines.get(1));
        assertEquals("1.0,NaN", lines.get(2));
    }

    @Test
    @DisplayName("CsvExporter should create a non-empty output file")
    void shouldCreateNonEmptyOutputFile() throws IOException {
        MathFunction function = new TableStub(Map.of(
                0.0, 5.0
        ));

        Path tempFile = Files.createTempFile("csv-export-test", ".csv");

        CsvExporter exporter = new CsvExporter();
        exporter.export(function, 0.0, 0.0, 1.0, tempFile.toString());

        assertTrue(Files.exists(tempFile));
        assertTrue(Files.size(tempFile) > 0);
    }
}