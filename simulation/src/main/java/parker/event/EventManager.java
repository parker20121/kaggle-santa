package parker.event;

import java.util.PriorityQueue;

/**
 *
 * @author Matt Parker
 */
public class EventManager extends PriorityQueue<Event> {
    
    private static EventManager manager;
            
    private EventManager(){
        super();
    }
    
    public static EventManager getInstance(){
        
        if ( manager == null ){
            manager = new EventManager();
        }
        
        return manager;
        
    }
    
}
