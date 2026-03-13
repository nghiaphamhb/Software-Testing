package module.stubs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import stubs.TableStub;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TableStubTest {

    private static final double DELTA = 1e-9;

    @Test
    @DisplayName("TableStub should return the mapped value for an existing argument")
    void shouldReturnMappedValueForExistingArgument() {
        TableStub stub = new TableStub(Map.of(
                -1.0, 2.5,
                0.0, 0.0,
                2.0, -3.7
        ));

        assertEquals(2.5, stub.calculate(-1.0), DELTA);
        assertEquals(0.0, stub.calculate(0.0), DELTA);
        assertEquals(-3.7, stub.calculate(2.0), DELTA);
    }

    @Test
    @DisplayName("TableStub should return NaN for an argument missing from the table")
    void shouldReturnNaNForMissingArgument() {
        TableStub stub = new TableStub(Map.of(
                1.0, 10.0
        ));

        assertTrue(Double.isNaN(stub.calculate(2.0)));
    }
}