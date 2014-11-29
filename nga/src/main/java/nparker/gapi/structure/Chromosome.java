package nparker.gapi.structure;

/**
 * Class <code>Chromosome</code> unifies chromosomes across genetic algorithms
 * by giving a uniform access to all chromsomes in a particular population.
 * Generically, we want to be able to represent a <code>Chromosome</code> in
 * a genetic algorithm population as an array of any type of genes, not just
 * bits. This interface allows us to represent a chromsome as a disparate
 * array of any objects unified within the <code>Chromosome</code> structure.
 *
 * @author Nathan Parker
 * @version 1.0
 */

public interface Chromosome
{
    /**
     * Returns the gene within the <code>Chromosome</code> at the specified
     * index. If the gene is a primitive, then it should be wrapped in one
     * of the Java standard classes associated with that primitive.
     *
     * @param geneIndex position of the gene within the <code>Chromosome</code>,
     *                  which is being thought of as an array of gene
     *                  <code>Object</code>s.
     * @return the gene at the specified index.
     */
    public Object getGene(int geneIndex);

    /**
     * Sets the gene at the specified position within the <code>Chromsome</code>
     * to the given gene.
     *
     * @param geneIndex position of the gene to be set within the structure of
     *                  this <code>Chromsome</code>.
     * @param gene new value to set the gene to.
     */
    public void setGene(int geneIndex, Object gene);

    /**
     * Returns the fitness value of this <code>Chromosome</code>.
     *
     * @return the fitness value of this <code>Chromosome</code>.
     */
    public double getFitness();

    /**
     * Sets the fitness value of this <code>Chromosome</code> to the value
     * specified by the parameter.
     *
     * @param value the new fitness value.
     */
    public void setFitness(double value);

    /**
     * Returns the generation of this <code>Chromosome</code>.
     *
     * @return the generation of this <code>Chromosome</code>.
     */
    public int getGeneration();

    /**
     * Sets the generation of this <code>Chromosome</code> to the value
     * specified by the parameter.
     *
     * @param gen the new generation value.
     */
    public void setGeneration(int gen);

    /**
     * Returns the entire gene pattern of this <code>Chromosome</code>. The
     * entire genetic makeup of the chromosome is treated as an array of
     * <code>Object</code>s for review purposes by outsiders. Obviously, a
     * problem-specific chromosome implementation may be made up as it likes,
     * but the implementation must be able to translate to an array.
     *
     * @return an array of <code>Object</code>s composing the entire gene
     *         pattern of this <code>Chromosome</code>.
     */
    public Object[] getGenePattern();

    /**
     * Returns the gene pattern of this <code>Chromosome</code> from
     * <code>startIndex</code> to <code>endIndex</code>, inclusive. Whether or
     * not specific implementations of this interface return an array from
     * <code>startIndex</code> to <code>endIndex</code>, inclusive, is not a
     * hard and fast rule as the main algorithm in the class
     * <code>MainTest</code> never makes use of this method. The range can be
     * modified to be from <code>startIndex</code> to <code>endIndex - 1</code>,
     * inclusive, but implementations that deviate from the original
     * specification should make note of such a deviation.
     *
     * @param startIndex the index of the first gene to be returned.
     * @param endIndex   the index of the last gene to be returned.
     * @return           the gene pattern of this <code>Chromosome</code> from
     *                   <code>startIndex</code> to <code>endIndex</code>,
     *                   inclusive.
     */
    public Object[] getGenePattern(int startIndex, int endIndex);

    /**
     * Returns the number of genes in this <code>Chromosome</code>. This is
     * intended to be interpretted as the length of the <code>Object</code>
     * array that is returned by the <code>getGenePattern()</code>. That is,
     * the value returned by this method should be consistent with the
     * <code>length</code> member of that particular array. Deviations or
     * alterations from this interpretation of the implementation of this
     * method in problem-specific implementations of this interface should make
     * note of their alterations to this definition.
     *
     * @return the number of genes in this <code>Chromosome</code>.
     */
    public int length();

    //public void setGenePattern(Object[] genePattern);
}