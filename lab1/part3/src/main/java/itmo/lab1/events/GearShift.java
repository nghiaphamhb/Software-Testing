package itmo.lab1.events;

/**
 * Action to change the gear number
 */
public final class GearShift {
    private final int fromGear;
    private final int toGear;
    private final int intendedGear;

    public GearShift(int fromGear, int toGear, int intendedGear) {
        this.fromGear = fromGear;
        this.toGear = toGear;
        this.intendedGear = intendedGear;
    }

    public int fromGear() { return fromGear; }
    public int toGear() { return toGear; }
    public int intendedGear() { return intendedGear; }
}
