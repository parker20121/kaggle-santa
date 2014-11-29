package nparker.steiner8d;

/**
 * Mutation strategy for the steiner point problem. Considering the
 * individual genes in a <code>Steiner8DChromosome</code> we can introduce
 * variation into the particular gene by flipping the value since the gene
 * is a <code>Boolean</code> or bit.
 *
 * @author Nathan Parker
 * @version 1.0
 */

import nparker.gapi.operator.MutationStrategy;
import java.util.Random;
import nparker.gapi.structure.*;

public class Steiner8DMutation implements MutationStrategy
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
        if (!(chromosome instanceof Steiner8DChromosome))
            throw new ClassCastException("chromosome not a valid " +
                    "Steiner8DChromsome in Steiner8DMutation.mutate()");

        //Decide which gene index to mutate
        geneToMutate = rand.nextInt(chromosome.length());

        //Mutate the gene
        if (rand.nextBoolean())
            chromosome.setGene(geneToMutate, Boolean.TRUE);
        else
            chromosome.setGene(geneToMutate, Boolean.FALSE);

        //Though not necessary, return a reference to the mutated chromosome
        return chromosome;
    }
}