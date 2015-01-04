package parker.kaggle.santa.simulator;

import parker.kaggle.santa.simulator.events.BuildToy;
import java.sql.Timestamp;

/**
 *
 * @author Matt Parker
 */
public class Elf {

    public static final double MIN_PROD_RATING = 0.25;
    public static final double MAX_PROD_RATING = 4d;
    public static final double INITIAL_PROD_RATING = 1d;
    
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
        
        productivityRating = productivityRating * Math.pow(1.02,santionedHoursWorked) * Math.pow(0.9, unsanctionedHoursWorked);
        
        if ( productivityRating < MIN_PROD_RATING ){
            productivityRating = MIN_PROD_RATING;
        }
        
        if ( productivityRating > MAX_PROD_RATING ){
            productivityRating = MAX_PROD_RATING;                    
        }
                
    }
    
    /**
     * Update the elf's productivity rating based on the assigned toy.
     */
    public void updateProductivityRating() throws Exception {
        
        if ( toy != null ){
            double sanctionedHours = 0d;
            double unsanctionedHours = 0d;
            updateProductivityRating( sanctionedHours, unsanctionedHours );
        } else {
            throw new Exception("No assigned toy to update the Elf's productivity rating.");
        }
        
    }
    
    public double calcTimeToBuildToy( BuildToy toy ){
        return toy.getDuration() / productivityRating;
    }
    
    public boolean isBuilding(){
        return building;
    }
    
    public void setBuilding( boolean building ){
        this.building = building;
    }
    
    public Timestamp assignToy( Timestamp buildStarts, BuildToy toy ){
        
        this.buildStarts = buildStarts;
        this.toy = toy;
        
        return null;  //Time when the toy will be completed.
        
    }
    
}
