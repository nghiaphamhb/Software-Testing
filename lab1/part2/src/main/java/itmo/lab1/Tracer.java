package itmo.lab1;

import java.util.ArrayList;
import java.util.List;

/**
 * Trace algorithm's steps
 */
public class Tracer {
    private final List<String> steps;

    public Tracer(){
        steps = new ArrayList<>();
    }

    public void hit(String p) {
        steps.add(p);
    }

    public List<String> steps() {
        return steps;
    }    

    @Override
    public String toString() {
        return String.join("->", steps);
    }
}
