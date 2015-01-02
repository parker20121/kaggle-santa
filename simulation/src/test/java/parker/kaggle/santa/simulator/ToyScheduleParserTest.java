package parker.kaggle.santa.simulator;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Matt
 */
public class ToyScheduleParserTest {
   
    @Test
    public void testParser() throws Exception {
        
        String toyConfig = "2,2014 1 1 0 1,5";
        Toy toy = ToyScheduleParser.parse( toyConfig );
        
        Calendar cal = new GregorianCalendar();
        cal.set( Calendar.YEAR, 2014 );
        cal.set( Calendar.MONTH, Calendar.JANUARY );
        cal.set( Calendar.DAY_OF_MONTH, 1 );
        cal.set( Calendar.HOUR, 0 );
        cal.set( Calendar.MINUTE, 1 );
        
        //Calendar arrivalTime = new GregorianCalendar();
        //arrivalTime.setTime( toy.getArrivalTime() );
        
        //Assert.assertEquals( 2, toy.getId() );
        //Assert.assertEquals( 0, cal.compareTo(arrivalTime) );
        //Assert.assertEquals( 5, toy.getDuration() );
        
    }
    
}
