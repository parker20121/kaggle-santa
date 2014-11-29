package nparker.gapi.util;

import nparker.gapi.operator.*;
import nparker.gapi.structure.*;
import java.util.Random;

/**
 * <p>Used to help unify the public interface for defining a genetic algorithm
 * operating context class. That is, when a genetic algorithm in this framework
 * is initialized, the context class should be defined in such a way as to hold
 * references to each of the pieces of the genetic algorithm.</p>
 *
 * @author Nathan Parker
 * @version 1.0
 */

public interface GeneticApplication
{
    /**
     * Sets the context of this genetic algorithm application to use the
     * specified crossover algorithm.
     *
     * @param strategy the given crossover algorithm.
     */
    public void setCrossover(CrossoverStrategy strategy);

    /**
     * Sets the context of this genetic algorithm application to use the
     * specified selection algorithm.
     *
     * @param strategy the given selection algorithm.
     */
    public void setSelection(SelectionStrategy strategy);

    /**
     * Sets the context of this genetic algorithm application to use the
     * specified mutation strategy.
     *
     * @param strategy the given mutation algorithm.
     */
    public void setMutation(MutationStrategy strategy);

    /**
     * Sets the context of this genetic algorithm application to use the
     * specified fitness function.
     *
     * @param function the given fitness function.
     */
    public void setFitnessFunction(FitnessFunction function);

    /**
     *
     * @param pop
     */
    public void setPopulation(Population pop);

    //public void setRandomNumberGenerator(RandomNumberGenerator rnd);
    public void setRandomNumberGenerator(Random rnd);

    public CrossoverStrategy getCrossover();

    public SelectionStrategy getSelection();

    public MutationStrategy getMutation();

    public FitnessFunction getFitnessFunction();

    public Population getPopulation();

    //public RandomNumberGenerator getRandomNumberGenerator();
    public Random getRandomNumberGenerator();

    public boolean isConvergent();

    public void init();
}