package itmo.lab1.things;

import itmo.lab1.states.Emotion;

/**
 * Ford Prefect is a fictional character and alien researcher from Betelgeuse in 
 * Douglas Adams' The Hitchhiker's Guide to the Galaxy series.
 * As a, field reporter for the Guide, he was stranded on Earth for 15 years, 
 * posing as an actor, before rescuing his friend Arthur Dent 
 * just before the planet's destruction. 
 */
public final class FordPrefect {
    public FordPrefect(){}

    public Experience hearRemark(Remark r){
        if (r.impact() >= 70) return new Experience(Emotion.SHOCKED, 80);
        if (r.impact() >= 30) return new Experience(Emotion.PROUD, 10);
        return new Experience(Emotion.PROUD, 0);
    }
}
