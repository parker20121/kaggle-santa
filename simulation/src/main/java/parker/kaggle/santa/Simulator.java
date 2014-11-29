package parker.kaggle.santa;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Matt Parker
 */
public class Simulator {
    
    public static void main ( String args[] ){
    
        BufferedWriter results = null;
        
        if ( args.length != 2 ){
            System.out.println("usage: java parker.kaggle.santa.Simulator [toy schedule] [results file] ");
            System.exit(0);
        } 
        
        try {

            List<String> toySchedule = FileUtils.readLines( new File( args[0] ) );            
            results = new BufferedWriter( new FileWriter( args[1] ) );
            
        } catch (Exception e) {
            
        }
    }
    
}
