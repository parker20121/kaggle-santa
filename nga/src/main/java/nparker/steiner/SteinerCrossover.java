package nparker.steiner;

import nparker.gapi.structure.*;
import nparker.gapi.operator.CrossoverStrategy;
import java.util.Collections;
import java.util.Random;

/**
 * Performs single-point crossover on the selected <code>Population</code>
 * of <code>SteinerPointChromosome</code>s and returns the resultant offspring.
 *
 * @author Nathan Parker
 * @version 1.0
 */

public class SteinerCrossover implements CrossoverStrategy
{
    private static final float CROSSOVER_RATE = 0.75F;
    /**
     * Performs the crossover operation over two parents and returns their
     * children as an array. This method performs single-point crossover on
     * the two parents using the specified crossover point.
     *
     * @param parent1 the first parent for cross-breeding.
     * @param parent2 the second parent for cross-breeding.
     * @param crosspoint the point at which genes are spliced across both
     *                   parents.
     * @return the two children of these parents as an array.
     */
    public Chromosome[] crossChromosomes(Chromosome parent1, Chromosome parent2,
        int crosspoint)
    {
        Chromosome[] children = null;     //array of offspring to be returned
        int length = -1;                  //overall length of a chromosome
        int p1gene = 0;                   //loop counter for parent1 genes
        int p2gene = 0;                   //loop counter for parent2 genes

        //Determine if parent1 is defined
        if (parent1 == null)
            //Assert: parent1 is undefined; throw exception
            throw new IllegalArgumentException("parent1 is undefined in " +
                "SteinerCrossover.crossChromosomes(Chromosome, Chromosome, " +
                "int)");

        //Determine if parent2 is defined
        if (parent2 == null)
            //Assert: parent2 is undefined; throw exception
            throw new IllegalArgumentException("parent1 is undefined in " +
                "SteinerCrossover.crossChromosomes(Chromosome, Chromosome, " +
                "int)");

        //Determine if the parent chromosomes are of the same type
        if ((!(parent1 instanceof SteinerPointChromosome)) ||
            (!(parent2 instanceof SteinerPointChromosome)))
            //Assert: parent1 not of the same type as parent2; throw exception
            throw new ClassCastException("parent1 and/or parent2 is not of " +
               "type SteinerPointChromosome in SteinerCrossover." +
               "crossChromosomes(Chromosome, Chromosome, int)");

        //Determine if the specified crossover point is within range
        if ((crosspoint < 0) || (crosspoint > parent1.length()))
            //Assert: crosspoint outside chromosome bounds; throw exception
            throw new ArrayIndexOutOfBoundsException("crosspoint addresses a " +
                "gene index outside the length of these chromosomes in " +
                "SteinerCrossover.crossChromosomes(Chromosome, Chromosome, " +
                "int)");

        //Initialize necessary variables
        children = new Chromosome[2];
        children[0] = new SteinerPointChromosome();
        children[1] = new SteinerPointChromosome();
        length = parent1.length();

        //Perform crossover for child 0
        for (p1gene = 0; p1gene < crosspoint; p1gene++)
            children[0].setGene(p1gene, parent1.getGene(p1gene));
        for (p2gene = crosspoint; p2gene < length; p2gene++)
            children[0].setGene(p2gene, parent2.getGene(p2gene));

        //Perform crossover for child 1
        for (p2gene = 0; p2gene < crosspoint; p2gene++)
            children[1].setGene(p2gene, parent2.getGene(p2gene));
        for (p1gene = crosspoint; p1gene < length; p1gene++)
            children[1].setGene(p1gene, parent1.getGene(p1gene));

        return children;
    }

    /**
     * Takes the population of selected chromosomes and breeds a new population
     * of chromosomes. The population created from the breeding is the same
     * size as the population specified by the given selected population. The
     * population created from the breeding contains only the children of the
     * parents which are taken from the selected population. The random number
     * generator is used to specify a random crossover point for each of the
     * breeding cycles and which parent breeds with which parent.
     *
     * @param p <code>Population</code> of selected chromosomes.
     * @param rand random number generator used for determining crossover point
     *             and mate selection.
     * @return <code>Population</code> created from the breeding which only
     *         contains the offspring.
     */
    public Population crossChromosomes(Population p, Random rand)
    {
        Population offspring = null;     //total population of offspring
        Chromosome[] children = null;    //children array returned from crossing
        int popSize = 0;                 //size of the populations
        int parent1 = 0;                 //index of the first parent
        int parent2 = 0;                 //index of the second parent
        int bestIndex = 0;               //index of most fit selected member
        int length = 0;                  //length of a chromosome

        //Determine if the population argument is defined
        if ((p == null) || (p.size() <= 0))
            throw new IllegalArgumentException("p is not defined in " +
                "SteinerCrossover.crossChromosomes(Population, RandomNumber" +
                "Generator)");

        //Determine if the random number generator is defined
        if (rand == null)
            throw new IllegalArgumentException("rand is not defined in " +
                "SteinerCrossover.crossChromosomes(Population, RandomNumber" +
                "Generator)");

        //Initialize local variables
        popSize = p.size();
        offspring = new BasePopulation();
        length = ((Chromosome) p.get(0)).length();

        //Commence crossover process
        while (offspring.size() < popSize)
        {
            //Randomize the selected population
            Collections.shuffle(p, rand);

            //Choose two parents at random
            parent1 = rand.nextInt(p.size());
            parent2 = rand.nextInt(p.size());

            //Determine if the parent indices are equal
            if (parent1 == parent2)
            {
                //Assert: parent indices are equal; change one of them to match
                //  the best parent
                bestIndex = p.indexOf(Collections.min(p));

                if (parent1 == bestIndex)
                {
                    //Assert: both indices == bestIndex; change either with
                    //  a new index
                    parent1 = rand.nextInt(p.size());
                }
                else
                {
                    //Assert: neither index equals bestIndex; change the worse
                    //  parent
                    double p1fitness, p2fitness, worse;
                    p1fitness = ((Chromosome) p.get(parent1)).getFitness();
                    p2fitness = ((Chromosome) p.get(parent2)).getFitness();

                    //Determine the worse fitness value of the two
                    worse = Math.max(p1fitness, p2fitness);

                    //Determine which parent's fitness matches the worse
                    if (p1fitness == worse)
                        //Assert: p1fitness == worse; change parent1 index
                        parent1 = bestIndex;
                    else
                        //Assert: p2fitness == worse; change parent2 index
                        parent2 = bestIndex;
                }
            }

            if (rand.nextFloat() < this.CROSSOVER_RATE)
            {
            children = this.crossChromosomes((Chromosome) p.get(parent1),
                (Chromosome) p.get(parent2), rand.nextInt(length));
            }
            else
            {
                children = new Chromosome[2];
                children[0] = (Chromosome) p.get(parent1);
                children[1] = (Chromosome) p.get(parent2);
            }

            //Add those children to the offspring population
            for (int i = 0; i < children.length; i++)
                offspring.add(children[i]);
        }

        //Return the offspring
        return offspring;
    }
}