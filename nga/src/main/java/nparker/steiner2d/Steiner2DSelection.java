package nparker.steiner2d;

import nparker.gapi.structure.*;
import nparker.gapi.operator.SelectionStrategy;
import java.util.Random;
import java.util.Collections;

/**
 *
 * @author Nathan Parker
 * @version 1.0
 */

public class Steiner2DSelection implements SelectionStrategy
{
    public Population select(Population p, Random rand)
    {
        int numSelected = 0;                //total number to select
        Chromosome bestMember = null;       //best chromosome in p
        Chromosome memberSelected = null;   //newly selected member
        Population selected = null;         //all selected members of p

        //Determine if p is defined
        if ((p == null) || (p.size() <= 0))
            //Assert: p is undefined; throw exception
            throw new IllegalArgumentException("p is undefined in " +
                "SteinerSelection.select()");

        //Determine if rand is defined
        if (rand == null)
            //Assert: rand is undefined; throw exception
            throw new IllegalArgumentException("rand is undefined in " +
                "SteinerSelection.select()");

        //Initialize selected population
        selected = new BasePopulation();

        //Select 33% of the population for breeding
        numSelected = (int) (p.size() * 0.33);

        //Sort the population based on fitness (we are assuming that each
        //member of the population already has their correct fitness value
        //determined ahead of time). This should order them from best fitness
        //(minimum) to worst (maximum).
        Collections.sort(p);

        //Mark the best member of the population as selected
        bestMember = (Chromosome) Collections.min(p);
        selected.add(bestMember);
        numSelected--;

        //Select members of the population randomly
        for (int i = 0; i < numSelected; i++)
        {
            memberSelected = (Chromosome) p.get(rand.nextInt(p.size()));

            //Make sure that the best member isn't selected more than once
            while (memberSelected != bestMember)
                memberSelected = (Chromosome) p.get(rand.nextInt(p.size()));

            selected.add(memberSelected);
        }

        return selected;
    }
}