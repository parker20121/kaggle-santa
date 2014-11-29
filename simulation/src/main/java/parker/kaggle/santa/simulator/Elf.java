package parker.kaggle.santa.simulator;

/**
 *
 * @author Matt Parker
 */
public class Elf {

    int id;
    double productivityRating = 1f;
    
    public Elf(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
}
