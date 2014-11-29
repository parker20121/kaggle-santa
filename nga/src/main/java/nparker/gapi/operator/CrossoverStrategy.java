package nparker.gapi.operator;

import nparker.gapi.structure.*;
import java.util.Random;

/**
 * This class allows for mating to occur between chromsomes in a population.
 * Classes implementing this interface may implement either or both methods,
 * but if any methods are not implemented they should throw
 * <code>UnsupportedOperationException</code>.
 *
 * @author Nathan Parker
 * @version 1.0
 */

public interface CrossoverStrategy
{
    public Chromosome[] crossChromosomes(Chromosome parent1, Chromosome parent2,
        int crosspoint);

    public Population crossChromosomes(Population p,
        Random rand);
}