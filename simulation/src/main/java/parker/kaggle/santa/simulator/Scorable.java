package parker.kaggle.santa.simulator;

import parker.kaggle.santa.simulator.events.BuildToy;

/**
 *
 * @author Matt Parker
 */
public interface Scorable  {

    public double score( BuildToy toy, Elf elf, long timeOfDay );
    
}
