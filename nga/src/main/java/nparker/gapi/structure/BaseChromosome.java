package nparker.gapi.structure;

/**
 * <p>Implements basic features usable by all types of chromosomes. This class
 * contains data to track both the fitness value and generation of this
 * chromosome. Also, this class provides concrete implementations of a few
 * of the methods that are specified in the <code>Chromosome</code> interface.
 * Finally, this class implements a mechanism that allows chromosomes to be
 * compared by fitness value.</p>
 *
 * <p>This class should provide both structure and reinforcement for the fact
 * that a chromosome is considered an array of objects for both programmatic
 * and processing purposes within the context of this API and extended
 * applications.</p>
 *
 * @author Nathan Parker
 * @version 1.0
 */

public abstract class BaseChromosome implements Chromosome, Comparable
{
    /**
     * The fitness of this chromosome.
     */
    private double fitness;

    /**
     * Represents what generation (iteration) this chromosome was created.
     */
    private int generation;

    public abstract Object getGene(int geneIndex);

    public abstract void setGene(int geneIndex, Object gene);

    public abstract int length();


    /**
     * Returns the fitness value of this chromosome.
     *
     * @return the fitness value of this chromosome.
     */
    public double getFitness()
    {
        return this.fitness;
    }

    /**
     * Sets the fitness value of this chromosome to the specified value.
     *
     * @param value the new fitness value.
     */
    public void setFitness(double value)
    {
        this.fitness = value;
    }

    /**
     * Returns the generation this chromosome belongs to. The generation, in
     * our case, represents the iteration during which this chromosome was
     * created.
     *
     * @return the generation of this chromosome.
     */
    public int getGeneration()
    {
        return this.generation;
    }

    /**
     * Sets the generation of this chromosome to the specified value.
     *
     * @param gen the new generation value.
     */
    public void setGeneration(int gen)
    {
        this.generation = gen;
    }

    /**
     * Returns the entire gene pattern of this chromosome.
     *
     * @return the entire gene patter of this chromsome as an
     *         <code>Object[]</code>.
     */
    public Object[] getGenePattern()
    {
        return this.getGenePattern(0, this.length() - 1);
    }

    /**
     * Returns the gene pattern of this chromosome between
     * <code>startIndex</code> and <code>endIndex</code>, inclusive.
     *
     * @param startIndex the index of the first gene to be returned.
     * @param endIndex   the index of the last gene to be returned.
     * @return           the gene pattern of this chromosome as an array of
     *                   <code>Object</code>s from <code>startIndex</code> to
     *                   <code>endIndex</code>, inclusive.
     */
    public Object[] getGenePattern(int startIndex, int endIndex)
    {
        //Determine if either of these array indices are negative
        if ((startIndex < 0) || (endIndex < 0))
            throw new IllegalArgumentException("Specified array indices " +
                "are negative: startIndex = " + startIndex + "; endIndex = " +
                endIndex);

        //Assert: startIndex and endIndex are nonnegative

        //Determine if there is a mismatch in the indices
        if (endIndex < startIndex)
            throw new IllegalArgumentException("Array indices mismatch: " +
                "startIndex = " + startIndex + "; endIndex = " + endIndex);

        //Assert: startIndex >= 0 && endIndex >= 0 && startIndex <= endIndex

        //Determine if endIndex is outside the range
        if (endIndex >= this.length())
            throw new ArrayIndexOutOfBoundsException("endIndex addresses an " +
                "an element beyond the last element in this chromosome. " +
                "endIndex = " + endIndex);

        //Assert: startIndex >= 0 && endIndex >= 0 && startIndex <= endIndex &&
        //  endIndex <= chromosome's logical length

        Object[] genePattern = new Object[endIndex - startIndex + 1];

        for (int i = startIndex; i <= endIndex; i++)
            genePattern[i] = this.getGene(i);

        return genePattern;
    }

    /**
     * <p>Compares this <code>BaseChromosome</code> to another
     * <code>Object</code> for order. To determine order, the
     * <code>Object</code> parameter is cast to a <code>Chromosome</code> and
     * then the fitness values of this object and the parameter are compared.
     * </p>
     *
     * <p>NOTE: natural ordering not consistent with equals necessarily as this
     * only compares the fitness value of two chromosomes to designate ordering
     * of these objects.</p>
     *
     * @param o the <code>Object</code> to be compared.
     * @return  a negative integer, zero, or a positive integer as this object
     *          is less than, equal to, or greater than the specified object.
     */
    public int compareTo(Object o)
    {
        final int LESS_THAN = -1;
        final int EQUALS = 0;
        final int GREATER_THAN = 1;

        Chromosome anotherChromosome = null;

        //Determine if the argument is defined
        if (o == null)
            //Assert: o is undefined; throw exception
            throw new IllegalArgumentException("parameter o is null within " +
                "BaseChromosome.compareTo()");

        //Determine if the argument is a Chromosome
        if (!(o instanceof Chromosome))
            //Assert: o is not a Chromosome; throw exception
            throw new IllegalArgumentException("parameter o is not a " +
                "Chromosome in BaseChromsome.compareTo()");

        //Compare the fitness values of this chromosome and the given argument
        anotherChromosome = (Chromosome) o;
        if (this.fitness > anotherChromosome.getFitness())
            return GREATER_THAN;
        else if (this.fitness < anotherChromosome.getFitness())
            return LESS_THAN;
        else
            return EQUALS;
    }
}