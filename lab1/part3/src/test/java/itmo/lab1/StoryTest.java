package itmo.lab1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import itmo.lab1.events.GearShift;
import itmo.lab1.states.Emotion;
import itmo.lab1.states.EngineState;
import itmo.lab1.things.Car;
import itmo.lab1.things.Driver;
import itmo.lab1.things.Experience;
import itmo.lab1.things.FordPrefect;
import itmo.lab1.things.Remark;

public class StoryTest{
    private Driver driver;
    private Car car;

    @BeforeEach
    void setUp(){
        driver = new Driver();
        car = new Car(4);
    }

    @Test
    @DisplayName("Not a downshift: 4->5 => no shock 🧪")
    void notADownshiftNoShock() {
        Experience e = driver.shift(car, new GearShift(4, 5, 5));
        assertEquals(Emotion.PROUD, e.emotion());
        assertEquals(EngineState.NORMAL, car.engineState());
    }

    @Test
    @DisplayName("Safe shift: 4->3 => no shock 🧪 ")
    void safeShiftNoShock(){
        Experience exp = driver.shift(car, new GearShift(4, 3, 3));

        assertEquals(Emotion.PROUD, exp.emotion());
        assertEquals(0, exp.shockLevel());
        assertEquals(EngineState.NORMAL, car.engineState());
        assertEquals(Emotion.PROUD, driver.emotion());
    }

    @Test
    @DisplayName("Small mistake: 4->2 instead of 3 => Little shock 🧪")
    void smallMistake() {
        Experience exp = driver.shift(car, new GearShift(4, 2, 3));

        assertEquals(Emotion.PROUD, exp.emotion());
        assertTrue(exp.shockLevel() <= 10);
        assertEquals(EngineState.NORMAL, car.engineState());
    }

    @Test
    @DisplayName("Big mistake: 4->1 instead of 3 => shocked and engine stressed 🧪")
    void bigMistakeShock() {
        Experience e = driver.shift(car, new GearShift(4, 1, 3));

        assertEquals(Emotion.SHOCKED, e.emotion());
        assertTrue(e.shockLevel() >= 70);
        assertEquals(EngineState.STRESSED, car.engineState());
        assertEquals(Emotion.SHOCKED, driver.emotion());
    }

    @Test
    @DisplayName("Ford with strong remark ~ driver with shock feeling 🧪")
    void fordSame() {
        FordPrefect ford = new FordPrefect();

        Experience driverShock = driver.shift(car, new GearShift(4, 1, 3));
        Experience fordFeeling = ford.hearRemark(new Remark(90));

        assertEquals(driverShock.emotion(), fordFeeling.emotion());
        assertEquals(driverShock.shockLevel(), fordFeeling.shockLevel());
    }

    @Test
    @DisplayName("Ford with weak remark is not ~ driver with shock feeling 🧪")
    void weakRemarkNotSame() {
        FordPrefect ford = new FordPrefect();

        Experience driverShock = driver.shift(car, new GearShift(4, 1, 3));
        Experience fordFeeling = ford.hearRemark(new Remark(10));

        assertTrue(
            driverShock.emotion() != fordFeeling.emotion()
            || driverShock.shockLevel() != fordFeeling.shockLevel()
        );
    }

    @Test
    @DisplayName("remark impact out of range throws 🧪")
    void remarkValidation() {
        assertThrows(IllegalArgumentException.class, () -> new Remark(-1));
        assertThrows(IllegalArgumentException.class, () -> new Remark(101));
    }
}
