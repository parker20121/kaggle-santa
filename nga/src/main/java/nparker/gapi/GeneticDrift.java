

/**
 * <p>Title: Genetic Algorithm Application Programming Interface</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: IPFW</p>
 * @author Nathan Parker
 * @version 1.0
 */
import nparker.gapi.BitPatternChromosome;
import nparker.gapi.util.*;
import nparker.gapi.structure.*;
import java.io.*;
import java.util.*;

public class GeneticDrift implements Metric
{
    /**
     * @todo Not implemented correctly. Currently looks at two different types
     * of chromosomes (BitPatternChromosomes and SteinerPointChromsomes). The
     * chromosome type needs to be uniform.
     * @param pop the <code>Population</code> to be examined.
     */
    public void calculate(Population pop)
    {
        BitPatternChromosome bestMember = null;      //member used to compare for drift
        BitPatternChromosome otherMember = null;     //member to be compared against
        Boolean[] bestPattern = null;      //best member's allele/gene pattern
        Boolean[] otherPattern = null;     //comparing member's gene pattern
        int popSize = pop.size();          //total population size
        int count = 0;
        double geneticDrift = 0D;

        //Retrieve the best member in the population
        bestMember = (BitPatternChromosome) Collections.min(pop);

        //Remove the best member from the population so that we do not compare
        //it with itself (and we need to replace it later)
        pop.remove(bestMember);

        //Get the best member's gene pattern
        bestPattern = (Boolean[]) bestMember.getGenePattern();

        //Compare each member of the population to the best member and
        //calculate the genetic drift
        for (int member = 0; member < popSize; member++)
        {
            //Retrieve the member of the population to be compared
            otherMember = (BitPatternChromosome) pop.get(member);

            //Get the member's gene pattern
            otherPattern = (Boolean[]) otherMember.getGenePattern();

            //Perform the comparison
            for (int gene = 0; gene < bestPattern.length; gene++)
            {
                //Determine if the genes at this position are unequal
                if (bestPattern[gene] != otherPattern[gene])
                {
                    count++;
                }
            }
        }

        //Calculate genetic drift
        geneticDrift = (2D * count) / ((double) (bestPattern.length * popSize));

        //Output genetic drift value
        System.out.println("Genetic drift value = " + geneticDrift);

        //Add best member back into the population
        pop.add(bestMember);
    }

    public void evaluate(GeneticApplication ga)
    {
        this.calculate(ga.getPopulation());
    }

    public void calculate(FileOutputStream fos, Population pop)
        throws IOException
    {
        throw new UnsupportedOperationException("Operation not implemented");
    }
}