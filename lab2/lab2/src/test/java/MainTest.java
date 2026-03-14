import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static final Path CSV_DIR = Paths.get("lab2/output/csv");
    private static final Path GRAPH_DIR = Paths.get("lab2/output/graph");

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(CSV_DIR);
        Files.createDirectories(GRAPH_DIR);
        cleanDirectory(CSV_DIR);
        cleanDirectory(GRAPH_DIR);
    }

    @AfterEach
    void tearDown() throws IOException {
        cleanDirectory(CSV_DIR);
        cleanDirectory(GRAPH_DIR);
    }

    @Test
    @DisplayName("Main.main should generate CSV and PNG output files")
    void main_shouldGenerateCsvAndPngFiles() throws Exception {
        Main.main(new String[0]);

        List<String> names = List.of(
                "Sin", "Cos", "Tan", "Sec",
                "Ln", "Log2", "Log5", "Log10",
                "FunctionSystem"
        );

        for (String name : names) {
            Path csv = CSV_DIR.resolve(name + ".csv");
            Path png = GRAPH_DIR.resolve(name + ".png");

            assertTrue(Files.exists(csv), "Missing CSV: " + csv);
            assertTrue(Files.size(csv) > 0, "Empty CSV: " + csv);

            assertTrue(Files.exists(png), "Missing PNG: " + png);
            assertTrue(Files.size(png) > 0, "Empty PNG: " + png);
        }
    }

    @Test
    @DisplayName("Generated Sin.csv should contain header and data rows")
    void main_shouldWriteValidCsvContent() throws Exception {
        Main.main(new String[0]);

        Path sinCsv = CSV_DIR.resolve("Sin.csv");
        assertTrue(Files.exists(sinCsv), "Sin.csv was not created");

        List<String> lines = Files.readAllLines(sinCsv);

        assertFalse(lines.isEmpty(), "Sin.csv is empty");
        assertEquals("x,result", lines.get(0).trim(), "Unexpected CSV header");
        assertTrue(lines.size() > 2, "Sin.csv should contain multiple data rows");

        String firstDataRow = lines.get(1).trim();
        assertFalse(firstDataRow.isBlank(), "First data row should not be blank");
        assertTrue(firstDataRow.contains(","), "First data row should contain comma");
    }

    @Test
    @DisplayName("plotCsv should throw IOException when CSV file does not exist")
    void plotCsv_shouldThrowIOExceptionWhenCsvFileDoesNotExist() throws Exception {
        Method plotMethod = Main.class.getDeclaredMethod(
                "plotCsv",
                String.class,
                String.class,
                String.class,
                double.class,
                double.class
        );
        plotMethod.setAccessible(true);

        InvocationTargetException exception = assertThrows(
                InvocationTargetException.class,
                () -> plotMethod.invoke(
                        null,
                        "lab2/output/csv/does-not-exist.csv",
                        "Missing",
                        "Missing",
                        -1.0,
                        1.0
                )
        );

        assertInstanceOf(IOException.class, exception.getCause());
    }

    private void cleanDirectory(Path dir) throws IOException {
        if (!Files.exists(dir)) {
            return;
        }

        try (var walk = Files.walk(dir)) {
            walk.sorted(Comparator.reverseOrder())
                    .filter(path -> !path.equals(dir))
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to delete " + path, e);
                        }
                    });
        }
    }
}