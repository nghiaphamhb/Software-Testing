package itmo.lab1.things;

import itmo.lab1.events.GearShift;
import itmo.lab1.states.Emotion;
import itmo.lab1.states.EngineState;

/** 
 * Driver in this story
 */
public final class Driver {
    private Emotion emotion; 

    public Driver(){
        this.emotion = Emotion.PROUD;
    }

    public Emotion emotion() {
        return emotion;
    }

    public Experience shift(Car car, GearShift s){
        int mistake = Math.abs(s.toGear() - s.intendedGear());
        int drop = s.fromGear() - s.toGear();

        Experience exp;
        if(drop <= 0){ // increment higher gear number
            exp = new Experience(Emotion.PROUD, 0); 
        } else if (mistake == 0) { // correctly shift 
            exp = new Experience(Emotion.PROUD, 0); 
        } else if (mistake == 1) { // a little shock
            exp = new Experience(Emotion.PROUD, 10); 
        } else { // big shock (4->1 instead of 3)
            exp = new Experience(Emotion.SHOCKED, 80); 
        }

        car.setCurrentGear(s.toGear());
        car.setEngineState(exp.emotion() == Emotion.SHOCKED ? EngineState.STRESSED : EngineState.NORMAL);
        emotion = exp.emotion();

        return exp;
    }
}
