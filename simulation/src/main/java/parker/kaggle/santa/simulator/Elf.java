package parker.kaggle.santa.simulator;

import parker.kaggle.santa.simulator.events.BuildToy;
import java.sql.Timestamp;

/**
 *
 * @author Matt Parker
 */
public class Elf {

    public static final double MIN_PROD_RATING = 0.25f;
    public static final double MAX_PROD_RATING = 4.0;
    public static final double INITIAL_PROD_RATING = 1.0;
    
    int id = -1;
    double productivityRating = INITIAL_PROD_RATING;
    boolean building = false;
    
    Timestamp buildStarts;
    BuildToy toy;
    
    public Elf(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getProductivityRating() {
        return productivityRating;
    }

    public void setProductivityRating(double productivityRating) {
        this.productivityRating = productivityRating;
    }
            
    public void updateProductivityRating( double santionedHoursWorked, double unsanctionedHoursWorked ){
        this.productivityRating = this.productivityRating * Math.pow(1.02,santionedHoursWorked) * Math.pow(0.9, unsanctionedHoursWorked);
    }
    
    public double calcTimeToBuildToy( BuildToy toy ){
        return toy.getDuration() / productivityRating;
    }
    
    public boolean isBuilding(){
        return building;
    }
    
    public Timestamp assignToy( Timestamp buildStarts, BuildToy toy ){
        
        this.buildStarts = buildStarts;
        this.toy = toy;
        
        return null;  //Time when the toy will be completed.
        
    }
    
}
