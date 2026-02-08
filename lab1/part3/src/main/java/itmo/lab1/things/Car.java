package itmo.lab1.things;

import itmo.lab1.states.EngineState;

/**
 * Just a car
 */
public final class Car {
    private int currentGear;
    private EngineState engineState;

    public Car(int initialGear){
        this.currentGear = initialGear;
        this.engineState = EngineState.NORMAL;
    }

    public int currentGear() { return currentGear; }
    public EngineState engineState() { return engineState; }

    void setCurrentGear(int gear) { this.currentGear = gear; }
    void setEngineState(EngineState state) { this.engineState = state; }
}
