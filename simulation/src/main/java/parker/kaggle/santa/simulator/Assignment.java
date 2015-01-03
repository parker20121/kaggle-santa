package parker.kaggle.santa.simulator;

import parker.kaggle.santa.simulator.events.BuildToy;

/**
 *
 * @author Matt Parker
 */
public class Assignment {

    Elf elf;
    BuildToy toy;
    long startTime;
    
    public Assignment( Elf elf, BuildToy toy, long startTime ){
        this.elf = elf;
        this.toy = toy;
        this.startTime = startTime;
    }

    public Elf getElf() {
        return elf;
    }

    public void setElf(Elf elf) {
        this.elf = elf;
    }

    public BuildToy getToy() {
        return toy;
    }

    public void setToy(BuildToy toy) {
        this.toy = toy;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    
}
