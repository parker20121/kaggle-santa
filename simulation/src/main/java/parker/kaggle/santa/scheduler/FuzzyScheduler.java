package parker.kaggle.santa.scheduler;

import java.util.TreeMap;
import net.sourceforge.jFuzzyLogic.FIS;
import parker.event.Event;
import parker.event.EventManager;
import parker.kaggle.santa.simulator.Elf;
import parker.kaggle.santa.simulator.events.BuildToy;
import parker.kaggle.santa.simulator.events.BuiltToy;

/**
 *
 * @author Matt
 */
public class FuzzyScheduler {
    
    TreeMap<Integer, Elf> elfPool;
    EventManager manager;
        
    FIS fis;
    
    public FuzzyScheduler( TreeMap<Integer, Elf> elfPool, EventManager manager ){
        
        this.elfPool = elfPool;
        this.manager = manager;
        
    }
    
    public void calc() throws Exception {
    
        while ( true ){
            
            Event nextEvent = manager.remove();
            
            if ( nextEvent instanceof BuildToy ){
                
                BuildToy toy = (BuildToy)nextEvent;
                Elf assignedElf = schedule( toy );
                
                if ( assignedElf == null ){
                        //Reschedule toy
                    
                } else {
                        //Assign toy.
                    assignedElf.assignToy( toy.getArrivalTime(), toy );
                }
                
            } else { 
                //Assume BuiltToy event
                BuiltToy builtToyEvent = (BuiltToy)nextEvent;                
                builtToyEvent.getElf().updateProductivityRating();
                builtToyEvent.getElf().setBuilding(false);
            }
                    
        }
        
    }
    
    public void init( String fclFileName){
        
       fis = FIS.load( fclFileName, true ); 
    }
    
    public Elf schedule( BuildToy toy ){
    
        double minPrority = Double.MAX_VALUE;
        Elf assignedElf = null;
        
        for ( Elf elf : elfPool.values() ){
            
            if ( !elf.isBuilding() ){
                
                fis.setVariable( "productivity_rating", elf.getProductivityRating() );
                fis.setVariable( "duration", toy.getDuration() );

                double processingDuration = toy.getDuration()/elf.getProductivityRating();
                double sanctionedWorkHours = 0.0;
                double unsanctionedWorkHours = 0.0;
                
                fis.setVariable( "processing_duration", processingDuration );
                fis.setVariable( "sanctioned_work_hours", sanctionedWorkHours );
                fis.setVariable( "unsanctioned_work_hours", unsanctionedWorkHours );
                
                fis.evaluate();

                double priority = fis.getVariable("priority").getDefuzzifier().defuzzify();
        
                if ( priority < minPrority ){
                    assignedElf = elf; 
                }
                
            }
            
        }
        
        return assignedElf;
       
    }
    
}
