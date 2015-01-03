package parker.kaggle.santa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.commons.io.FileUtils;
import parker.event.EventManager;
import parker.kaggle.santa.simulator.ArrivalDateFormat;
import parker.kaggle.santa.simulator.Elf;
import parker.kaggle.santa.simulator.events.BuildToy;
import parker.kaggle.santa.simulator.ToyScheduleParser;

/**
 *
 * @author Matt Parker
 */
public class Simulator {
   
        /** Number of elves used to build toys. */
    public static final String ELVES = "elves";
         
    public static void main ( String args[] ){
    
        if ( args.length != 2 ){
            System.out.println("usage: java parker.kaggle.santa.Simulator [toy schedule] [results file] ");
            System.exit(0);
        } 
        
        DateFormat df = new ArrivalDateFormat();      
        BufferedWriter results = null;
        TreeMap<Integer, Elf> elfPool = new TreeMap<Integer,Elf>();
        Properties simulatorProperties = new Properties();
        Set<BuildToy> toys = new TreeSet<BuildToy>();
        
        try {

                //Initialize elves to work on toys.
            simulatorProperties.load( new FileReader("simulator.properties") );            
            int numberOfElves = Integer.parseInt( simulatorProperties.getProperty(ELVES) );
            
            for( int i=0; i<numberOfElves; i++ ){
                elfPool.put( i, new Elf(i) );
            }
            
                //Build schedule.
            List<String> toyScheduleItems = FileUtils.readLines( new File( args[0], "utf-8" ) ); 
            toyScheduleItems.remove(0);  // remove the file header.
            
            EventManager manager = EventManager.getInstance();
            
            for ( String config : toyScheduleItems ){
                BuildToy buildToyEvent = ToyScheduleParser.parse(config);
                manager.add( buildToyEvent );
            }
            
            FuzzyScheduler scheduler = new FuzzyScheduler( elfPool, manager );
            
            results = new BufferedWriter( new FileWriter( args[1] ) );
            
        } catch (Exception e) {
            System.out.println( e.toString() );
            e.printStackTrace();
        }
        
    }
    
}
