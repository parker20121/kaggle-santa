package nparker.steiner8d;

import nparker.gapi.structure.Chromosome;
import nparker.gapi.util.FitnessFunction;
import java.awt.geom.Point2D;

/**
 * <p>The fitness function for <code>Steiner8DChromosome</code>s where the
 * distances between all of the 8 random points within the unit square and the
 * steiner points are summed. The total distance between all of these points
 * represents the fitness for this chromosome.</p>
 *
 * <p>This fitness function only works on chromosomes of class
 * <code>Steiner8DChromosome</code>. Any other class will not get results
 * using this fitness function since this function relies upon the particular
 * gene pattern of the chromosome to be of a certain composition. Also, this
 * function relies on certain methods which are present in that particular
 * chromosome class.</p>
 *
 * @author Nathan Parker
 * @version 1.0
 */

public class Steiner8DFitnessFunction implements FitnessFunction
{
    /**
     * Contains the 8 random points that are within the unit square that we
     * are comparing the distance between the Steiner points and these.
     */
    private Point2D.Double[] ptsInBounds;

    /**
     * Returns the total distance between the steiner points and the points on
     * the unit square that those points are connected to. Thus, the total
     * distance is a measure of the fitness of the given chromosome. If the
     * given chromosome does not have the correct connection array structure
     * where every point is connected to a steiner point then the total
     * distance returned will be assigned Double.POSITIVE_INFINITY. The
     * chromsome must be a <code>Steiner8DChromsome</code> in order for it
     * to be evaluated correctly.
     *
     * @param chromosome a <code>Steiner8DChromosome</code> to be evaluated.
     * @return the total distance between the steiner points and the points
     *         on the unit square as a measure of the fitness, or
     *         Double.POSITIVE_INFINITY if the chromosome's connection
     *         structure is in error.
     */
    public double evaluateFitness(Chromosome chromosome)
    {
        final int CONNECTION_OFFSET = 80;
        final int FIRST_STEINER_PT = 1;
        final int SECOND_STEINER_PT = 2;
        Steiner8DChromosome spc = null;  //chromosome to be evaluated
        Point2D.Double first = null;     //first steiner point
        Point2D.Double second = null;    //second steiner point
        int whichSteiner = 0;            //index of which steiner point (1 or 2)
        double totalDistance = 0D;       //fitness of this chromosome

        //Determine if the argument is defined
        if (chromosome == null)
            //Assert: chromosome is not defined; throw exception
            throw new IllegalArgumentException("Parameter chromosome is not " +
                "defined in Steiner8DFitnessFunction.evaluateFitness()");

        //Determine if the argument passed in is valid
        if (!(chromosome instanceof Steiner8DChromosome))
            throw new IllegalArgumentException("Given chromosome cannot be " +
                "evaluated by this fitness function. Class of chromosome = " +
                chromosome.getClass().getName());

        //Cast chromosome into a useable form
        spc = (Steiner8DChromosome) chromosome;

        //Get steiner point values
        first = spc.getS1();
        second = spc.getS2();

        //Calculate the distance between the steiner points and the points on
        //the unit square
        for (int p = 0; p < this.ptsInBounds.length; p++)
        {
            //Determine which Steiner point is connected to this random point
            whichSteiner = spc.isConnected(p);

            //Calculate the distance
            switch (whichSteiner)
            {
                case FIRST_STEINER_PT:
                    totalDistance += first.distance(this.ptsInBounds[p]);
                    break;
                case SECOND_STEINER_PT:
                    totalDistance += second.distance(this.ptsInBounds[p]);
                    break;
                default:
                    throw new IllegalArgumentException("whichSteiner does " +
                        "not refer to a valid Steiner point index. " +
                        "whichSteiner = " + whichSteiner);
            }

        }

        //Calculate the distance between the two steiner points themselves
        totalDistance += first.distance(second);

        //Make sure answer is valid; if not give a very bad score.
        //Each point must be connected to at least one steiner point
        /*for (int conn = 0; conn < 4; conn++)
        {
            if (spc.isConnected(conn) != spc.isConnected(conn + 4))
            {
                totalDistance = Double.POSITIVE_INFINITY;
                break;
            }
        }*/

        return totalDistance;
    }

    /**
     * Sets the points that are in bounds to those specified by the parameter.
     * Note that because this is the <code>Steiner8DFitnessFunction</code>
     * class, the array should contain 8 points that are within the bounds of
     * the unit square, inclusive.
     *
     * @param pts array of points to be used to solve this problem.
     */
    public void setPoints(Point2D.Double[] pts)
    {
        //Determine if the parameter is defined
        if (pts == null)
            //Assert: parameter is undefined; throw exception
            throw new IllegalArgumentException("pts is null");

        this.ptsInBounds = pts;
    }
}