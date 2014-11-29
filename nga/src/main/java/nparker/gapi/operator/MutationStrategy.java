package nparker.gapi.operator;

import nparker.gapi.structure.Chromosome;
import java.util.Random;

/**
 * Provides facilities for mutating a single <code>Chromosome</code> within
 * a genetic algorithm. Unlike <code>CrossoverStrategy</code> and
 * <code>SelectionStrategy</code>, we do not operate on an entire population
 * at one time. Instead, we operate on a single <code>Chromosome</code> at a
 * time. This could be extend to operate across an entire population at a given
 * time, though this would also force a change in the main algorithm driving
 * this application.
 *
 * @author Nathan Parker
 * @version 1.0
 */

public interface MutationStrategy
{
    public Chromosome mutate(Chromosome chromosome, Random rand);
}