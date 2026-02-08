package itmo.lab1.things;

/**
 *  Remark to Ford Prefect
 */
public final class Remark {
    private final int impact; // 0..100

    public Remark(int impact) {
        if (impact < 0 || impact > 100) {
            throw new IllegalArgumentException("impact must be in [0,100]");
        }
        this.impact = impact;
    }

    public int impact() { return impact; }
}