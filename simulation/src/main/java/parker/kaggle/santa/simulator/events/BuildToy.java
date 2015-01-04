package parker.kaggle.santa.simulator.events;

import java.sql.Timestamp;
import java.util.Date;
import parker.event.Event;

/**
 * Event signifying a toy is to built at a future time,
 * and the toy's building parameters.
 * 
 * @author Matt Parker
 */
public class BuildToy extends Event {

    /** The toy's identifier from the input file. */
    int id;
    
    /** The time the toy can be built. */
    Timestamp arrivalTime;
    
    /** The time it will take to build the toy, in minutes? */
    int duration;
    
    public BuildToy( int id, long arrivalTime, int duration ){
        super( arrivalTime );
        this.id = id;
        this.arrivalTime = new Timestamp(arrivalTime);
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
