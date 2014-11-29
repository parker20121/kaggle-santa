package nparker.steiner8d;

/**
 *
 *
 * @author Nathan Parker
 * @version 1.0
 */

import nparker.gapi.BitPatternChromosome;
import java.awt.geom.Point2D;

public class Steiner8DChromosome extends BitPatternChromosome
{
    /** Maximum number of bits in this bit-wise chromosome. */
    public static final int LENGTH = 88;

    /**
     * Creates a new bit-wise chromosome which is tailored to solve the 8-point
     * Steiner problem. The bit-wise chromosome has an internal length of 88
     * bits. This uses Java's random number generator in the
     * <code>java.lang.Math</code> to determine the initial settings of each
     * bit in the bit string and not the random number generator set in the
     * <code>GeneticApplication</code> class in this package.
     *
     * @see java.lang.Math#random()
     */
    public Steiner8DChromosome()
    {
        super(LENGTH);

        for (int i = 0; i < this.LENGTH; i++)
        {
            if (Math.random() > 0.5D)
                super.setGene(i, Boolean.TRUE);
            else
                super.setGene(i, Boolean.FALSE);
        }
    }

    /**
     * Returns the first Steiner point represented by this chromosome. As far
     * as the bit-wise representation is concerned, the first Steiner point
     * concerns the bits between positions 0 through 40.
     *
     * @return the first Steiner point in this chromosome.
     */
    public Point2D.Double getS1()
    {
        String bits = super.toBitString();
        double x1 = this.convertEncoding(bits.substring(0, 20));
        double y1 = this.convertEncoding(bits.substring(20, 40));
        return new Point2D.Double(x1, y1);
    }

    /**
     * Returns the second Steiner point represented by this chromosome. As far
     * as the bit-wise representation is concerned, the second Steiner point
     * concerns the bits between positions 40 through 80.
     *
     * @return the second Steiner point in this chromosome.
     */
    public Point2D.Double getS2()
    {
        String bits = super.toBitString();
        double x2 = this.convertEncoding(bits.substring(40, 60));
        double y2 = this.convertEncoding(bits.substring(60, 80));
        return new Point2D.Double(x2, y2);
    }

    /**
     * Returns which Steiner point is connected to the specified random point.
     * Because there are 8 random points in this problem, indexed 0 to 7, this
     * method tells us which of the two Steiner points is connected by
     * examining the connection bits within the chromosome. If the specified
     * random point is connected to the first Steiner point, a 1 is returned.
     * If the specified random point is connected to the second Steiner point,
     * a 2 is returned.
     *
     * @param pt index of which random point to test in range of 0 to 7,
     *           inclusive.
     * @return   1 if the specified point is connected to the first Steiner
     *           point; 2 otherwise.
     */
    public int isConnected(int pt)
    {
        final int CONNECTION_OFFSET = 80;
        final int FIRST_STEINER_PT = 1;
        final int SECOND_STEINER_PT = 2;

        //Determine if the parameter is within range
        if ((pt < 0) || (pt > 7))
            //Assert: parameter out of range; throw exception
            throw new IllegalArgumentException("pt specifies a connection " +
                "bit that is out of range. pt must be between 0 and 7. pt " +
                "= " + pt);

        //Determine which steiner point the requested random point is connected
        if (((Boolean) super.getGene(CONNECTION_OFFSET + pt)).booleanValue())
        {
            //Assert: gene at specified position is set, that is, the bit = 1;
            //  interpret this to mean that the point pt is connected to the
            //  second Steiner point; return 2
            return SECOND_STEINER_PT;
        }
        else
        {
            //Assert: gene at specified position is set, that is, the bit = 0;
            //  interpret this to mean that the point pt is connected to the
            //  first Steiner point; return 1
            return FIRST_STEINER_PT;
        }
    }

    /**
     * Returns a <code>String</code> representation of this chromosome. This
     * <code>String</code> representation is formatted to be read out as XML.
     * The <code>String</code> contains the bit-wise representation of the
     * chromosome, the generation, and the fitness value.
     *
     * @return <code>String</code> representation of this chromosome.
     */
    public String toString()
    {
        StringBuffer xmlStr = new StringBuffer("<chromosome ");
        xmlStr.append("gen=\"");
        xmlStr.append(super.getGeneration());
        xmlStr.append('\"');
        xmlStr.append(' ');
        xmlStr.append("fitness=\"");
        xmlStr.append(super.getFitness());
        xmlStr.append('\"');
        xmlStr.append(' ');
        xmlStr.append("genes=\"");
        xmlStr.append(super.toBitString());
        xmlStr.append('\"');
        xmlStr.append(' ');
        xmlStr.append("/>");

        return xmlStr.toString();
    }

    /**
     * Returns the double number represented by an arbitrary string of bits in
     * a <code>BitPatternChromosome</code>.
     *
     * @param bits a string of bits from a <code>BitPatternChromosome</code>.
     * @return the converted <code>double</code> value of the bits in a range
     *         from 0 to 1.
     */
    private double convertEncoding(String bits)
    {
        //Determine if the parameter is defined
        if (bits == null)
            //Assert: parameter is undefined; throw exception
            throw new IllegalArgumentException("bits is null");
        if (bits.equals(""))
            //Assert: parameter is empty; throw exception
            throw new IllegalArgumentException("bits is an empty string");

        int asInt = Integer.parseInt(bits, 2);
        String constructDouble = String.valueOf(asInt);
        constructDouble = "0." + constructDouble;
        return Double.parseDouble(constructDouble);
    }
}