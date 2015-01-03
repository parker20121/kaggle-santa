package parker.kaggle.santa.simulator.events;

import java.util.Date;
import parker.event.Event;
import parker.kaggle.santa.simulator.Elf;

/**
 * Event signify a toy has been built and 
 * elf statistics should be updated accordingly.
 * 
 * @author Matt Parker
 */
public class BuiltToy extends Event {
    
    /** The elf assigned to build the toy. */
    Elf elf;
    
    /** The time the toy will be finished being built. */
    Date finishTime;
    
    /** The time the elf started building the toy. */
    Date startTime;

    /**  The toy being built by this elf. */
    BuildToy toy;
    
    public BuiltToy( Date startTime, Date finishTime, Elf elf, BuildToy toy ){
        super( finishTime );
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.elf = elf;
        this.toy = toy;
    }

    public Elf getElf() {
        return elf;
    }

    public void setElf(Elf elf) {
        this.elf = elf;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
        
}
