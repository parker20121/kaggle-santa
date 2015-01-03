package parker.kaggle.santa.scheduler;

import java.util.TreeMap;
import parker.event.Event;
import parker.event.EventManager;
import parker.kaggle.santa.simulator.Elf;
import parker.kaggle.santa.simulator.events.BuiltToy;

/**
 *
 * @author Matt
 */
public class FuzzyScheduler {
    
    TreeMap<Integer, Elf> elfPool;
    EventManager manager;
            
    public FuzzyScheduler( TreeMap<Integer, Elf> elfPool, EventManager manager ){
        this.elfPool = elfPool;
        this.manager = manager;
    }
    
    public void calc() throws Exception {
    
        while ( true ){
            
            Event nextEvent = manager.remove();
            
            if ( nextEvent instanceof BuildToy.class ){
                    
            } else { 
                //assume builttoy event
                BuiltToy builtToyEvent = (BuiltToy)nextEvent;
               
                builtToyEvent.getElf().updateProductivityRating(santionedHoursWorked, unsanctionedHoursWorked);
            }
                    
        }
        
    }
    
}
