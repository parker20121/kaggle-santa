package nparker.gapi.util;

import nparker.gapi.operator.*;
import nparker.gapi.structure.Population;
import java.util.Random;

/**
 * Utility base class for creating a basic genetic algorithm application.
 *
 * @author Nathan Parker
 * @version 1.0
 */

public abstract class BaseGeneticApplication implements GeneticApplication
{
    private CrossoverStrategy crossover;
    private SelectionStrategy selection;
    private MutationStrategy mutation;
    private FitnessFunction fitFunction;
    private Population population;
    private Random rand;

    public void setCrossover(CrossoverStrategy strategy)
    {
        this.crossover = strategy;
    }

    public void setSelection(SelectionStrategy strategy)
    {
        this.selection = strategy;
    }

    public void setMutation(MutationStrategy strategy)
    {
        this.mutation = strategy;
    }

    public void setFitnessFunction(FitnessFunction function)
    {
        this.fitFunction = function;
    }

    public void setPopulation(Population pop)
    {
        this.population = pop;
    }

    public void setRandomNumberGenerator(Random rnd)
    {
        this.rand = rnd;
    }

    public CrossoverStrategy getCrossover()
    {
        return this.crossover;
    }

    public SelectionStrategy getSelection()
    {
        return this.selection;
    }

    public MutationStrategy getMutation()
    {
        return this.mutation;
    }

    public FitnessFunction getFitnessFunction()
    {
        return this.fitFunction;
    }

    public Population getPopulation()
    {
        return this.population;
    }

    public Random getRandomNumberGenerator()
    {
        return this.rand;
    }

    /**
     * <p>Called by the main algorithm to initialize this genetic algorithm
     * application. It is always called after the entire default application
     * has been set up. That is, once all of the classes have been loaded from
     * the properties file and those have been hooked into this application,
     * this method is called.</p>
     *
     * <p>A subclass of <code>BaseGeneticApplication</code> should override
     * this method if it has initialization to perform.</p>
     *
     * <p>The implementation of this method provided by the
     * <code>BaseGeneticApplication</code> class does nothing.</p>
     */
    public void init()
    {
    }

    public abstract boolean isConvergent();
}