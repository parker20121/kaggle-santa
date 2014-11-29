package nparker.steiner2d;

import nparker.gapi.structure.Chromosome;
import nparker.gapi.util.FitnessFunction;

/**
 * <p>The fitness function for <code>Steiner2DChromosome</code>s where the
 * distances between all of the points on the unit cube and the steiner points
 * are summed. The total distance between all of these points represents the
 * fitness for this chromosome.</p>
 *
 * <p>This fitness function only works on chromosomes of class
 * <code>Steiner2DChromosome</code>. Any other class will not get results
 * using this fitness function since this function relies upon the particular
 * gene pattern of the chromosome to be of a certain composition.</p>
 *
 * @author Nathan Parker
 * @version 1.0
 */

public class Steiner2DFitnessFunction implements FitnessFunction
{
    /**
     * Array index of x-value of the first point.
     */
    private static final int X0 = 0;

    /**
     * Array index of the y-value of the first point.
     */
    private static final int Y0 = 1;

    /**
     * Array index of the x-value of the second point.
     */
    private static final int X1 = 2;

    /**
     * Array index of the y-value of the second point.
     */
    private static final int Y1 = 3;

    /**
     * Represents one value for either an x-value or y-value for a coordinate
     * on the unit square.
     */
    private static final float COORDINATE_ZERO = 0F;

    /**
     * Represents one value for either an x-value or y-value for a coordinate
     * on the unit square.
     */
    private static final float COORDINATE_ONE = 1F;

    /**
     * Returns the total distance between the steiner points and the points on
     * the unit square that those points are connected to. Thus, the total
     * distance is a measure of the fitness of the given chromosome. If the
     * given chromosome does not have the correct connection array structure
     * where every point is connected to a steiner point then the total
     * distance returned will be assigned Double.POSITIVE_INFINITY. The
     * chromsome must be a <code>Steiner2DChromsome</code> in order for it
     * to be evaluated correctly.
     *
     * @param chromosome a <code>Steiner2DChromosome</code> to be evaluated.
     * @return the total distance between the steiner points and the points
     *         on the unit square as a measure of the fitness, or
     *         Double.POSITIVE_INFINITY if the chromosome's connection
     *         structure is in error.
     */
    public double evaluateFitness(Chromosome chromosome)
    {
        final int CONNECTION_OFFSET = 80;
        Steiner2DChromosome spc = null;  //chromosome to be evaluated
        String bits = null;              //bit string representation
        float x0;                        //first steiner point x-value
        float x1;                        //second steiner point x-value
        float y0;                        //first steiner point y-value
        float y1;                        //second steiner point y-value
        double totalDistance = 0D;       //fitness of this chromosome
        int conn;                        //loop counter for connections

        //Determine if the argument is defined
        if (chromosome == null)
            //Assert: chromosome is not defined; throw exception
            throw new IllegalArgumentException("Parameter chromosome is not " +
                "defined in Steiner2DFitnessFunction.evaluateFitness()");

        //Determine if the argument passed in is valid
        if (!(chromosome instanceof Steiner2DChromosome))
            throw new IllegalArgumentException("Given chromosome cannot be " +
                "evaluated by this fitness function. Class of chromosome = " +
                chromosome.getClass().getName());

        //Cast chromosome into a useable form
        spc = (Steiner2DChromosome) chromosome;

        //Get the encoding
        bits = spc.toBitString();

        //Get steiner point values
        x0 = this.convertEncoding(bits.substring(0, 20));
        y0 = this.convertEncoding(bits.substring(20, 40));
        x1 = this.convertEncoding(bits.substring(40, 60));
        y1 = this.convertEncoding(bits.substring(60, 80));

        //Calculate the distance between the steiner points and the points on
        //the unit square
        for (conn = CONNECTION_OFFSET;
             conn < CONNECTION_OFFSET + 8;
             conn++)
        {
            if (((Boolean) spc.getGene(conn)).booleanValue())
            {
                //Calculate the distance between to points and add that to
                //overall distance
                switch (conn - CONNECTION_OFFSET)
                {
                    case 0:
                        //Distance between steiner point (X0, Y0) and (0, 0)
                        totalDistance += this.distance(x0, y0,
                            this.COORDINATE_ZERO, this.COORDINATE_ZERO);
                        break;
                    case 1:
                        //Distance between steiner point (X0, Y0) and (0, 1)
                        totalDistance += this.distance(x0, y0,
                            this.COORDINATE_ZERO, this.COORDINATE_ONE);
                        break;
                    case 2:
                        //Distance between steiner point (X0, Y0) and (1, 0)
                        totalDistance += this.distance(x0, y0,
                            this.COORDINATE_ONE, this.COORDINATE_ZERO);
                        break;
                    case 3:
                        //Distance between steiner point (X0, Y0) and (1, 1)
                        totalDistance += this.distance(x0, y0,
                            this.COORDINATE_ONE, this.COORDINATE_ONE);
                        break;
                    case 4:
                        //Distance between steiner point (X1, Y1) and (0, 0)
                        totalDistance += this.distance(x1, y1,
                            this.COORDINATE_ZERO, this.COORDINATE_ZERO);
                        break;
                    case 5:
                        //Distance between steiner point (X1, Y1) and (0, 1)
                        totalDistance += this.distance(x1, y1,
                            this.COORDINATE_ZERO, this.COORDINATE_ONE);
                        break;
                    case 6:
                        //Distance between steiner point (X1, Y1) and (1, 0)
                        totalDistance += this.distance(x1, y1,
                            this.COORDINATE_ONE, this.COORDINATE_ZERO);
                        break;
                    case 7:
                        //Distance between steiner point (X1, Y1) and (1, 1)
                        totalDistance += this.distance(x1, y1,
                            this.COORDINATE_ONE, this.COORDINATE_ONE);
                        break;
                    default:
                        //conn counter trying to address an erroneous array
                        //value; throw exception
                        throw new ArrayIndexOutOfBoundsException("Steiner " +
                            "point fitness function trying to access " +
                            "values outside the array in " +
                            "SteinerFitnessFunction.evaluateFitness();" +
                            "conn = " + conn);
                }
            }
        }

        //Calculate the distance between the two steiner points themselves
        totalDistance += this.distance(x0, y0, x1, y1);

		//Make sure answer is valid; if not give a very bad score.
		//Each point must be connected to at least one steiner point
        for (conn = CONNECTION_OFFSET; conn < CONNECTION_OFFSET + 4; conn++)
        {
            if (!(((Boolean) spc.getGene(conn)).booleanValue() !=
                  ((Boolean) spc.getGene(conn + 4)).booleanValue()))
            {
                totalDistance = Double.POSITIVE_INFINITY;
                break;
            }
        }

        return totalDistance;
    }

    /**
     * Returns the distance between two points whose coordinates are all
     * represented as <code>Float</code>s.
     *
     * @param x0 x-value of the first point.
     * @param y0 y-value of the first point.
     * @param x1 x-value of the second point.
     * @param y1 y-value of the second point.
     * @return the distance between the two specified points.
     */
    private double distance(float x0, float y0, float x1, float y1)
    {
        double distanceOfX = 0D;     //distance between x-coordinates
        double distanceOfY = 0D;     //distance between y-coordinates

        distanceOfX = Math.pow(x1 - x0, 2D);
        distanceOfY = Math.pow(y1 - y0, 2D);

        return Math.sqrt(distanceOfX + distanceOfY);
    }

    /**
     * Returns the double number represented by an arbitrary string of bits in
     * a <code>BitPatternChromosome</code>.
     *
     * @param bits a string of bits from a <code>BitPatternChromosome</code>.
     * @return the converted <code>double</code> value of the bits in a range
     *         from 0 to 1.
     */
    private float convertEncoding(String bits)
    {
        //Determine if the parameter is defined
        if (bits == null)
            //Assert: parameter is undefined; throw exception
            throw new IllegalArgumentException("bits is null");
        if (bits.equals(""))
            //Assert: parameter is empty; throw exception
            throw new IllegalArgumentException("bits is an empty string");

        int asInt = Integer.parseInt(bits, 2);
        String constructFloat = String.valueOf(asInt);
        constructFloat = "0." + constructFloat;
        return Float.parseFloat(constructFloat);
    }
}