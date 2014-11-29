package nparker.steiner;

import nparker.gapi.structure.Chromosome;
import nparker.gapi.util.FitnessFunction;

/**
 * <p>The fitness function for <code>SteinerPointChromosome</code>s where the
 * distances between all of the points on the unit cube and the steiner points
 * are summed. The total distance between all of these points represents the
 * fitness for this chromosome.</p>
 *
 * <p>This fitness function only works on chromosomes of class
 * <code>SteinerPointChromosome</code>. Any other class will not get results
 * using this fitness function since this function relies upon the particular
 * gene pattern of the chromosome to be of a certain composition.</p>
 *
 * @author Nathan Parker
 * @version 1.0
 */

public class SteinerFitnessFunction implements FitnessFunction
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
    private static final Float COORDINATE_ZERO = new Float(0F);

    /**
     * Represents one value for either an x-value or y-value for a coordinate
     * on the unit square.
     */
    private static final Float COORDINATE_ONE = new Float(1F);

    /**
     * Returns the total distance between the steiner points and the points on
     * the unit square that those points are connected to. Thus, the total
     * distance is a measure of the fitness of the given chromosome. If the
     * given chromosome does not have the correct connection array structure
     * where every point is connected to a steiner point then the total
     * distance returned will be assigned Double.POSITIVE_INFINITY. The
     * chromsome must be a <code>SteinerPointChromsome</code> in order for it
     * to be evaluated correctly.
     *
     * @param chromosome a <code>SteinerPointChromosome</code> to be evaluated.
     * @return the total distance between the steiner points and the points
     *         on the unit square as a measure of the fitness, or
     *         Double.POSITIVE_INFINITY if the chromosome's connection
     *         structure is in error.
     */
    public double evaluateFitness(Chromosome chromosome)
    {
        SteinerPointChromosome spc = null;//chromosome to be evaluated
        Object[] spcPattern = null;       //gene pattern of parameter chromosome
        double totalDistance = 0D;        //fitness of this chromosome
        int conn;                         //loop counter for connections

        //Determine if the argument is defined
        if (chromosome == null)
            //Assert: chromosome is not defined; throw exception
            throw new IllegalArgumentException("Parameter chromosome is not " +
                "defined in SteinerFitnessFunction.evaluateFitness()");

        //Determine if the argument passed in is valid
        if (!(chromosome instanceof SteinerPointChromosome))
            throw new IllegalArgumentException("Given chromosome cannot be " +
                "evaluated by this fitness function. Class of chromosome = " +
                chromosome.getClass().getName());

        //Cast chromosome into a useable form
        spc = (SteinerPointChromosome) chromosome;

        //Get the encoding
        spcPattern = spc.getGenePattern();

        //Calculate the distance between the steiner points and the points on
        //the unit square
        for (conn = spc.CONNECTION_OFFSET;
             conn < spc.CONNECTION_OFFSET + 8;
             conn++)
        {
            if (((Boolean) spcPattern[conn]).booleanValue())
            {
                //Calculate the distance between to points and add that to
                //overall distance
                switch (conn)
                {
                    case 4:
                        //Distance between steiner point (X0, Y0) and (0, 0)
                        totalDistance += this.distance(
                            (Float) spcPattern[this.X0],
                            (Float) spcPattern[this.Y0],
                            this.COORDINATE_ZERO,
                            this.COORDINATE_ZERO);
                        break;
                    case 5:
                        //Distance between steiner point (X0, Y0) and (0, 1)
                        totalDistance += this.distance(
                            (Float) spcPattern[this.X0],
                            (Float) spcPattern[this.Y0],
                            this.COORDINATE_ZERO,
                            this.COORDINATE_ONE);
                        break;
                    case 6:
                        //Distance between steiner point (X0, Y0) and (1, 0)
                        totalDistance += this.distance(
                            (Float) spcPattern[this.X0],
                            (Float) spcPattern[this.Y0],
                            this.COORDINATE_ONE,
                            this.COORDINATE_ZERO);
                        break;
                    case 7:
                        //Distance between steiner point (X0, Y0) and (1, 1)
                        totalDistance += this.distance(
                            (Float) spcPattern[this.X0],
                            (Float) spcPattern[this.Y0],
                            this.COORDINATE_ONE,
                            this.COORDINATE_ONE);
                        break;
                    case 8:
                        //Distance between steiner point (X1, Y1) and (0, 0)
                        totalDistance += this.distance(
                            (Float) spcPattern[this.X1],
                            (Float) spcPattern[this.Y1],
                            this.COORDINATE_ZERO,
                            this.COORDINATE_ZERO);
                        break;
                    case 9:
                        //Distance between steiner point (X1, Y1) and (0, 1)
                        totalDistance += this.distance(
                            (Float) spcPattern[this.X1],
                            (Float) spcPattern[this.Y1],
                            this.COORDINATE_ZERO,
                            this.COORDINATE_ONE);
                        break;
                    case 10:
                        //Distance between steiner point (X1, Y1) and (1, 0)
                        totalDistance += this.distance(
                            (Float) spcPattern[this.X1],
                            (Float) spcPattern[this.Y1],
                            this.COORDINATE_ONE,
                            this.COORDINATE_ZERO);
                        break;
                    case 11:
                        //Distance between steiner point (X1, Y1) and (1, 1)
                        totalDistance += this.distance(
                            (Float) spcPattern[this.X1],
                            (Float) spcPattern[this.Y1],
                            this.COORDINATE_ONE,
                            this.COORDINATE_ONE);
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
        totalDistance += this.distance((Float) spcPattern[this.X0],
            (Float) spcPattern[this.Y0], (Float) spcPattern[this.X1],
            (Float) spcPattern[this.Y1]);

		//Make sure answer is valid; if not give a very bad score.
		//Each point must be connected to at least one steiner point
        for (conn = spc.CONNECTION_OFFSET;
             conn < spc.CONNECTION_OFFSET + 4;
             conn++)
        {
            if (!(((Boolean) spcPattern[conn]).booleanValue() !=
                  ((Boolean) spcPattern[conn + 4]).booleanValue()))
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
    private double distance(Float x0, Float y0, Float x1, Float y1)
    {
        double distanceOfX = 0D;     //distance between x-coordinates
        double distanceOfY = 0D;     //distance between y-coordinates

        distanceOfX = Math.pow(x1.doubleValue() - x0.doubleValue(), 2D);
        distanceOfY = Math.pow(y1.doubleValue() - y0.doubleValue(), 2D);

        return Math.sqrt(distanceOfX + distanceOfY);
    }
}