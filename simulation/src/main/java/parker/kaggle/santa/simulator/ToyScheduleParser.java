package parker.kaggle.santa.simulator;

import java.text.DateFormat;
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
    
    public static Toy parse( String lineItem ) throws Exception {
        
        Matcher m = pattern.matcher(lineItem);
        
        if ( m.find() ){
            Toy toy = new Toy();
            toy.setId( Integer.parseInt( m.group(1) ) );
            toy.setArrivalTime( df.parse( m.group(2) ) );
            toy.setDuration( Integer.parseInt( m.group(3) ) );
            
            System.out.println("g1: " + m.group(1) );
            System.out.println("g2: " + m.group(2) );
            System.out.println("g3: " + m.group(3) );
            
            return toy;
        }
        
        return null;
    }
    
}
