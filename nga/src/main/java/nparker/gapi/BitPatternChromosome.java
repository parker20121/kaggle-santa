package nparker.gapi;

import nparker.gapi.structure.*;
import java.lang.Boolean;
import java.util.*;

/**
 * <p>Title: Genetic Algorithm Framework</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: IPFW</p>
 * @author Nathan Parker
 * @version 1.0
 */

public class BitPatternChromosome extends BaseChromosome implements Cloneable
{
    private BitSet genes;
    private int length;

    public BitPatternChromosome()
    {
        this.genes = new BitSet();
        super.setFitness(0D);
        this.length = this.genes.length();
    }

    /**
     * Creates a new chromosome with a gene bit array of the specified length.
     *
     * @param nbits  specified bit length of the internal gene array
     */
    public BitPatternChromosome(int nbits)
    {
        this.genes = new BitSet(nbits);
        super.setFitness(0D);
        this.length = nbits;
    }

    /**
     * Cloning this <code>Chromosome</code> produces a new
     * <code>Chromosome</code> that is equal to it. The clone is a deep copy
     * of original with the exact same mutation rate and gene pattern.
     *
     * @return reference to an exact copy of this object
     */
    public Object clone()
    {
        BitPatternChromosome clone = new BitPatternChromosome(this.length());
        clone.setGeneration(super.getGeneration());
        clone.setFitness(super.getFitness());
        clone.genes.or(this.genes);
        return clone;
    }

    public boolean equals(Object obj)
    {
        BitPatternChromosome anotherChromosome = null;

        //Determine if the parameter is a valid BitPatternChromosome instance
        if (!(obj instanceof BitPatternChromosome))
        {
            //Assert: obj is an instance of some other object, so
            //  will never be equal; return false
            return false;
        }

        //Determine if parameter passed refers to this object
        if (this == obj)
        {
            //Assert: parameter refers to this object; return true
            return true;
        }

        //Cast parameter to a usable Chromosome object
        anotherChromosome = (BitPatternChromosome) obj;

        //Determine if the chromosomes' gene encoding are the same length
        if (anotherChromosome.length() != this.length())
        {
            //Assert: lengths differ between chromosomes; do not compare
            return false;
        }

        return this.genes.equals(anotherChromosome.genes);
    }

    public Object getGene(int geneIndex)
    {
        return Boolean.valueOf(this.genes.get(geneIndex));
    }

    public Object[] getGenePattern()
    {
        return this.getGenePattern(0, this.length() - 1);
    }

    public Object[] getGenePattern(int startIndex, int endIndex)
    {
        Boolean[] genePattern = null;

        //Initialize Boolean array to the appropriate size
        genePattern = new Boolean[endIndex - startIndex + 1];

        //Create the object array
        for (int gene = startIndex; gene <= endIndex; gene++)
        {
            genePattern[gene] = Boolean.valueOf(this.genes.get(gene));
        }

        return genePattern;
    }

    public void setGene(int geneIndex, Object gene)
    {
        Boolean value = null;

        if ((geneIndex < 0) || (geneIndex >= this.length()))
            throw new ArrayIndexOutOfBoundsException("geneIndex addresses a " +
                "gene outside this chromsome. geneIndex = " + geneIndex);

        if ((gene instanceof Boolean) && (gene != null))
        {
            value = (Boolean) gene;
            this.genes.set(geneIndex, value.booleanValue());
        }
        else
        {
            throw new IllegalArgumentException("Given arguments not valid " +
                "for BitPatternChromsome.setGene()");
        }
    }

    /**
     * Returns the logical length of this <code>Chromosome</code>'s
     * gene encoding. This method returns the number of bits representing
     * the genes in this <code>Chromosome</code>.
     *
     * @return number of bits in the gene encoding
     */
    public int length()
    {
        return this.length;
    }

    /**
     * Returns a bit string representing the internal gene bit array.
     * The bit string is oriented such that gene[0] is at the rightmost
     * end of the string and gene[n] is at the leftmost end.
     *
     * @return <code>String</code> of bits represented the gene encoding
     */
    public String toLittleEndianBitString()
    {
        StringBuffer bitStr = new StringBuffer(this.length());

        for (int i = this.length() - 1; i >= 0; i--)
        {
            bitStr.append( (this.genes.get(i)) ? 1 : 0 );
        }

        return bitStr.toString();
    }

    public String toBitString()
    {
        int len = this.length();
        StringBuffer bitStr = new StringBuffer(len);

        for (int i = 0; i < len; i++)
        {
            bitStr.append( (this.genes.get(i)) ? 1 : 0 );
        }

        return bitStr.toString();
    }

    /**
     * Returns a string representation of this bit set. For every index
     * for which the genes contains a bit in the set state, the decimal
     * representation of that index is included in the result. Such
     * indices are listed in order from lowest to highest, separated by
     * ",&nbsp;" (a comma and a space) and surrounded by braces,
     * resulting in the usual mathematical notation for a set of
     * integers.
     *
     * @return <code>String</code> representation of the gene encoding
     * @see java.util.BitSet#toString()
     */
    public String toString()
    {
        return this.genes.toString();
    }
}