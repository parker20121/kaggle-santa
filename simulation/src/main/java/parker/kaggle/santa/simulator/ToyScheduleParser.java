package parker.kaggle.santa.simulator;

import parker.kaggle.santa.simulator.events.BuildToy;
import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create a toy based on its configuration in the toy schedule.
 * 
 * @author Matt
 */
public class ToyScheduleParser {
    
    private static final Pattern pattern = Pattern.compile("(\\d+),(\\w+),(\\d+)");
    private static final DateFormat df = new ArrivalDateFormat();
    
    public static BuildToy parse( String lineItem ) throws Exception {
        
        Matcher m = pattern.matcher(lineItem);
        
        if ( m.find() ){
            
            int id = Integer.parseInt( m.group(1) );
            Date arrivalTime = df.parse( m.group(2) );
            int duration = Integer.parseInt( m.group(3) );
            
            System.out.print("id: " + m.group(1) );
            System.out.print(" arrivalTime: " + m.group(2) );
            System.out.println(" duration: " + m.group(3) );
            
            return new BuildToy( id, arrivalTime, duration );
        }
        
        throw new Exception("Coudn't parse config: " + lineItem );
        
    }
    
}
