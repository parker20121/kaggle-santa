package nparker.steiner;

import nparker.gapi.operator.MutationStrategy;
import java.util.Random;
import nparker.gapi.structure.*;

/**
 * Mutation strategy for the steiner point problem. Considering the
 * individual genes in a <code>SteinerPointChromosome</code> we can introduce
 * variation into the particular gene by either flipping the value if the gene
 * is a <code>Boolean</code> or varying it across the given range if it is
 * a <code>Float</code>.
 *
 * @author Nathan Parker
 * @version 1.0
 */

public class SteinerMutation implements MutationStrategy
{
    public Chromosome mutate(Chromosome chromosome, Random rand)
    {
        int geneToMutate = 0;     //index of the gene to be mutated

        //Determine if the chromosome to be mutated is defined
        if (chromosome == null)
            throw new IllegalArgumentException("chromosome is undefined in " +
                "SteinerMutation.mutate()");

        //Determine if the random number generator is defined
        if (rand == null)
            throw new IllegalArgumentException("random number generator not " +
                "defined in SteinerMutation.mutate()");

        //Determine if the chromosome in question is a SteinerPointChromosome
        if (!(chromosome instanceof SteinerPointChromosome))
            throw new ClassCastException("chromosome not a valid " +
                "SteinerPointChromsome in SteinerMutation.mutate()");

        //Decide which gene index to mutate
        geneToMutate = rand.nextInt(chromosome.length());

        //Mutate the gene
        switch (geneToMutate)
        {
            case 0:
            case 1:
            case 2:
            case 3:
                //Assert: geneToMutate refers to a Float gene
                chromosome.setGene(geneToMutate, new Float(Math.random()));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                //Assert: geneToMutate refers to a Boolean gene
                Boolean bool = (Boolean) chromosome.getGene(geneToMutate);
                if (bool.booleanValue())
                    chromosome.setGene(geneToMutate, Boolean.FALSE);
                else
                    chromosome.setGene(geneToMutate, Boolean.TRUE);
                break;
        }

        //Though not necessary, return a reference to the mutated chromosome
        return chromosome;
    }
}