package parker.event;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Matt Parker
 */
public class Event extends Timestamp {
    
    public Event(long time){
        super(time);
    }
    
    public Event( Timestamp timestamp ){
        super( timestamp.getTime() );
    }
    
    public Event( Date date ){
        super( date.getTime() );
    }
    
}
