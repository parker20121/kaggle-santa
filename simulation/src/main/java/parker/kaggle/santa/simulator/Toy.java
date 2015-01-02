package parker.kaggle.santa.simulator;

import java.util.Date;

/**
 *
 * @author Matt Parker
 */
public class Toy implements Comparable {

    int id;
    Date arrivalTime;
    int duration;
    
    public Toy(){
        
    }
    
    public Toy( int id, Date arrivalTime, int duration ){
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int compareTo(Object o) {
        Date time = (Date)o;
        return arrivalTime.compareTo(time);
    }
   
}
