package parker.kaggle.santa.simulator;

/**
 *
 * @author Matt Parker
 */
public class TimeResult {

    double sanctionedTime = 0d;
    double unsanctionedTime = 0d;
    
    long startTime;
    long endTime;
    
    public TimeResult( long startTime, long endTime ){
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public double getUnsanctionedTime(){
        return unsanctionedTime;
    }
    
    public double getSanctionedTime(){
        return sanctionedTime;
    }
    
}
