

/**
 * Title:        Genetic Algorithm Application Programming Interface
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      IPFW
 * @author Nathan Parker
 * @version 1.0
 */
import nparker.gapi.util.*;
import nparker.gapi.structure.*;
import nparker.gapi.operator.*;
import java.util.*;
import java.io.*;

public class MainTest
{
    public static void main(String[] args)
    {
        //final String PROPERTY_FILE_PATH = "genetic.properties";

        Properties gaProperties = null;   //hash table of the properties
        Class classToLoad = null;         //next class to be loaded dynamically
        GeneticApplication ga = null;     //this genetic application
        Random rand = null;               //reference to random number geerator
        Population population = null;     //reference to the population in ga
        Population selected = null;       //selected population members
        Population offspring = null;      //resultant children from crossover
        Chromosome member = null;         //reference to a population member
        FitnessFunction fitFunction = null;//reference to fitness function in ga
        Metric[] metrics = null;          //metrics to be ran on this GA
        double mutationRate = 0D;         //mutation rate per chromosome
        int maxIterations = 0;            //number of iterations for the GA
        int popSize = 0;                  //number of members in GA population

        //Load the properties from the file
        gaProperties = new Properties();
        try
        {
            //gaProperties.load(new FileInputStream(PROPERTY_FILE_PATH));
            gaProperties.load(new FileInputStream(args[0]));
        }
        catch (FileNotFoundException fnfex)
        {
            System.err.println("Could not find properties file: " + args[0]);
        }
        catch (IOException ioex)
        {
            System.err.println("Error occurred with I/O on file: " + args[0]);
            ioex.printStackTrace();
        }

        //Load the genetic application with the appropriate algorithms
        try
        {
            //Load the genetic application class
            classToLoad = Class.forName(gaProperties.getProperty(
                GeneticPropertyConstants.GENETIC_ALGORITHM_APPLICATION_CLASS));
            ga = (GeneticApplication) classToLoad.newInstance();

            //Load the population class
            classToLoad = Class.forName(gaProperties.getProperty(
                GeneticPropertyConstants.POPULATION_CLASS));
            ga.setPopulation((Population) classToLoad.newInstance());

            //Load the mutation strategy class
            classToLoad = Class.forName(gaProperties.getProperty(
                GeneticPropertyConstants.MUTATION_STRATEGY));
            ga.setMutation((MutationStrategy) classToLoad.newInstance());

            //Load the crossover strategy class
            classToLoad = Class.forName(gaProperties.getProperty(
                GeneticPropertyConstants.CROSSOVER_STRATEGY));
            ga.setCrossover((CrossoverStrategy) classToLoad.newInstance());

            //Load the selection strategy class
            classToLoad = Class.forName(gaProperties.getProperty(
                GeneticPropertyConstants.SELECTION_STRATEGY));
            ga.setSelection((SelectionStrategy) classToLoad.newInstance());

            //Load the random number generator class
            classToLoad = Class.forName(gaProperties.getProperty(
                GeneticPropertyConstants.RANDOM_NUMBER_GENERATOR_CLASS));
            ga.setRandomNumberGenerator((Random) classToLoad.newInstance());

            //Load the fitness function class
            classToLoad = Class.forName(gaProperties.getProperty(
                GeneticPropertyConstants.FITNESS_FUNCTION));
            ga.setFitnessFunction((FitnessFunction) classToLoad.newInstance());
        }
        catch (ClassNotFoundException cnfex)
        {
            cnfex.printStackTrace();
        }
        catch (IllegalAccessException iaex)
        {
            iaex.printStackTrace();
        }
        catch (InstantiationException iex)
        {
            iex.printStackTrace();
        }

        //Continue setting up the genetic algorithm mutation and crossover
        //rates
        mutationRate = Double.valueOf(gaProperties.getProperty(
            GeneticPropertyConstants.MUTATION_RATE)).doubleValue();
        maxIterations = Integer.valueOf(gaProperties.getProperty(
            GeneticPropertyConstants.MAX_ITERATIONS)).intValue();
        popSize = Integer.valueOf(gaProperties.getProperty(
            GeneticPropertyConstants.POPULATION_SIZE)).intValue();

        //Initialize the population
        try
        {
            //Initialize class to be loaded to be the chromosome instance
            classToLoad = Class.forName(gaProperties.getProperty(
                GeneticPropertyConstants.CHROMOSOME_CLASS));

            //Retrieve a reference to the genetic algorithm's population
            population = ga.getPopulation();

            //Retrieve reference to the fitness function for future operations
            fitFunction = ga.getFitnessFunction();

            //Instantiate popSize new members of the population
            for (int i = 0; i < popSize; i++)
            {
                member = (Chromosome) classToLoad.newInstance();
                member.setGeneration(0);
                //member.setFitness(fitFunction.evaluateFitness(member));
                population.add(member);
            }
        }
        catch (ClassNotFoundException cnfex)
        {
            cnfex.printStackTrace();
        }
        catch (IllegalAccessException iaex)
        {
            iaex.printStackTrace();
        }
        catch (InstantiationException iex)
        {
            iex.printStackTrace();
        }

        //Retrieve the set of metrics to be ran
        StringTokenizer stok = new StringTokenizer(
            gaProperties.getProperty(GeneticPropertyConstants.METRICS), " ,");

        //Determine if there are any metrics to load
        if (stok.countTokens() != 0)
        {
            //Assert: there are metrics to load; load them into the array
            metrics = new Metric[stok.countTokens()];
            int metricIndex = 0;

            while (stok.hasMoreTokens())
            {
                try
                {
                    classToLoad = Class.forName(stok.nextToken());
                    metrics[metricIndex] = (Metric) classToLoad.newInstance();
                    metricIndex++;
                }
                catch (ClassNotFoundException cnfex)
                {
                    cnfex.printStackTrace();
                }
                catch (IllegalAccessException iaex)
                {
                    iaex.printStackTrace();
                }
                catch (InstantiationException iex)
                {
                    iex.printStackTrace();
                }
            }
        }
        else
        {
            //Assert: no metrics to load; ignore metrics
            metrics = null;
        }

        //Retrieve a reference to the random number generator
        rand = ga.getRandomNumberGenerator();

        //Perform other genetic application initialization here
        ga.init();

        //Run the algorithm
        for (int cycle = 1; cycle <= maxIterations; cycle++)
        {
            //Evaluate fitness of all members
            if (population instanceof RandomAccess)
            {
                //Assert: the population is RandomAccess; use FOR loop cycling
                for (int n = 0; n < popSize ; n++)
                {
                    member = (Chromosome) population.get(n);
                    member.setFitness(fitFunction.evaluateFitness(member));
                }
            }
            else
            {
                //Assert: the population is not RandomAccess; use iterator
                for (Iterator i = population.iterator(); i.hasNext(); )
                {
                    member = (Chromosome) i.next();
                    member.setFitness(fitFunction.evaluateFitness(member));
                }
            }

            //Determine if we have metrics to run on this application
            if (metrics != null)
            {
                //Assert: some number of metrics have been instantiated;
                //  run them
                for (int m = 0; m < metrics.length; m++)
                {
                    metrics[m].evaluate(ga);
                }
            }

            //Perform selection
            selected = ga.getSelection().select(population, rand);

            //Perform crossover
            offspring = ga.getCrossover().crossChromosomes(selected, rand);

            //Set generation
            if (offspring instanceof RandomAccess)
            {
                //Assert: population is RandomAccess; use FOR loop cycling
                for (int n = 0, size = offspring.size(); n < size ; n++)
                {
                    member = (Chromosome) offspring.get(n);
                    member.setGeneration(cycle);
                }
            }
            else
            {
                //Assert: population is not RandomAccess; use iterator
                for (Iterator i = offspring.iterator(); i.hasNext(); )
                {
                    member = (Chromosome) i.next();
                    member.setGeneration(cycle);
                }
            }

            //Perform mutation on both the original population and the offspring
            if (population instanceof RandomAccess)
            {
                //Assert: population is RandomAccess; use FOR loop cycling
                for (int n = 0; n < popSize ; n++)
                {
                    if (rand.nextDouble() < mutationRate)
                    {
                        member = (Chromosome) population.get(n);
                        ga.getMutation().mutate(member, rand);
                    }
                }
            }
            else
            {
                //Assert: population is not RandomAccess; use iterator
                for (Iterator i = population.iterator(); i.hasNext(); )
                {
                    member = (Chromosome) i.next();

                    if (rand.nextDouble() < mutationRate)
                    {
                        ga.getMutation().mutate(member, rand);
                    }
                }
            }

            if (offspring instanceof RandomAccess)
            {
                //Assert: population is RandomAccess; use FOR loop cycling
                for (int n = 0, size = offspring.size(); n < size ; n++)
                {
                    if (rand.nextDouble() < mutationRate)
                    {
                        member = (Chromosome) offspring.get(n);
                        ga.getMutation().mutate(member, rand);
                        member.setFitness(fitFunction.evaluateFitness(member));
                    }
                }
            }
            else
            {
                //Assert: population is not RandomAccess; use iterator
                for (Iterator i = offspring.iterator(); i.hasNext(); )
                {
                    member = (Chromosome) i.next();

                    if (rand.nextDouble() < mutationRate)
                    {
                        ga.getMutation().mutate(member, rand);
                    }
                }
            }

            //Evaluate fitness of offspring members
            if (offspring instanceof RandomAccess)
            {
                //Assert: the population is RandomAccess; use FOR loop cycling
                for (int n = 0, size = offspring.size(); n < size ; n++)
                {
                    member = (Chromosome) offspring.get(n);
                    member.setFitness(fitFunction.evaluateFitness(member));
                }
            }
            else
            {
                //Assert: the population is not RandomAccess; use iterator
                for (Iterator i = offspring.iterator(); i.hasNext(); )
                {
                    member = (Chromosome) i.next();
                    member.setFitness(fitFunction.evaluateFitness(member));
                }
            }

            //Adjust population so that only the best members remain while
            //still fitting in the maximum population size requirement
            //NOTE: this will need adjusting depending on whether the problem
            //is a min or max problem - need to think of a solution for this.
            //NOTE: should optimize this using same IF statement to check
            //for java.util.RandomAccess marker interface.
            population.addAll(offspring);
            Collections.sort(population);

            try
            {
                //Initialize class to be loaded to be the population
                classToLoad = Class.forName(gaProperties.getProperty(
                    GeneticPropertyConstants.POPULATION_CLASS));

                //Create a new population and copy it
                Population newPop = (Population) classToLoad.newInstance();
                newPop.addAll(population.subList(0, popSize));

                //Update references
                population = newPop;
                ga.setPopulation(newPop);
            }
            catch (ClassNotFoundException cnfex)
            {
                cnfex.printStackTrace();
            }
            catch (IllegalAccessException iaex)
            {
                iaex.printStackTrace();
            }
            catch (InstantiationException iex)
            {
                iex.printStackTrace();
            }

            //Call isConvergent() - which is under debate at this point
            if (ga.isConvergent()) break;
        }
    }
}