package nparker.junk;
//package nparker.steiner;

import nparker.gapi.operator.SelectionStrategy;
import nparker.gapi.structure.*;
//import nparker.gapi.util.RandomNumberGenerator;
import java.util.*;

/**
 * <p>Title: Genetic Algorithm Application Programming Interface</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: IPFW</p>
 * @author Nathan Parker
 * @version 1.0
 */

public class StochasticUniversalSampling implements SelectionStrategy
{
    public Population select(Population p, Random rand)
    {
        double popTotalFitness = 0D;     //total fitness sum of population
        double sum = 0D;                 //cumulative fitness for mates chosen
        double threshold = 0D;           //threshold sum for mate selection
        Population selected = null;      //members selected for crossover
        Chromosome member = null;        //current population member reference

        //Determine if the population argument is defined
        if (p == null)
            //Assert: p is undefined; throw exception
            throw new IllegalArgumentException("Population parameter p is " +
                "null in StochasticUniversalSampling.select()");

        //Create the selected population structure
        selected = new BasePopulation();

        //Determine which access to use for the selection strategy
        if (p instanceof RandomAccess)
        {
            //Assert: p uses random access, so it is best to use a for loop
            //  and the get method

            //Compute the total fitness of all population members
            for (int i = 0; i < p.size(); i++)
            {
                member = (Chromosome) p.get(i);
                popTotalFitness += member.getFitness();
            }

            //Apply the SUS algorithm
            threshold = Math.random();
            for (int j = 0; j < p.size(); j++)
            {
                member = (Chromosome) p.get(j);
                sum += p.size() * member.getFitness() / popTotalFitness;

                while (sum > threshold)
                {

                }
            }
        }
        else
        {
            //Assert: p does not use random access, so it is best to use an
            //  iterator and a while loop

            //Compute the total fitness of all population members
            for (Iterator i = p.iterator(); i.hasNext(); )
            {
                member = (Chromosome) i.next();
                popTotalFitness += member.getFitness();
            }
        }

        return selected;
    }
}