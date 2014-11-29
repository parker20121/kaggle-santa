package parker.kaggle.santa.simulator;

/**
 *
 * @author Matt Parker
 */
public interface Scorable  {

    public double score( Toy toy, Elf elf, long timeOfDay );
    
}
