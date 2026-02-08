package itmo.lab1.things;

import itmo.lab1.states.Emotion;

/** 
 * Emotional level
 */
public final class Experience {
    private final Emotion emotion;
    private final int shockLevel;

    public Experience(Emotion emotion, int shockLevel){
        if (shockLevel < 0 || shockLevel > 100) {
            throw new IllegalArgumentException("shockLevel must be in [0,100]");
        }
        this.emotion = emotion;
        this.shockLevel = shockLevel;
    }

    public Emotion emotion() {
        return emotion;
    }

    public int shockLevel(){
        return shockLevel;
    }
}
