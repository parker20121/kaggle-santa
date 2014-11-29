package nparker.steiner8d;

/**
 * Contains references to all of the different pieces that make up this
 * genetic algorithm application. This is the container object used in solving
 * the 8-point Steiner problem.
 *
 * @author Nathan Parker
 * @version 1.0
 */

import nparker.gapi.util.*;
import nparker.gapi.structure.*;
import java.io.*;
import java.util.*;
import java.awt.geom.Point2D;

public class Steiner8DGeneticApp extends BaseGeneticApplication
{
    /**
     * The number of times we want to see the same best-to-date fitness value
     * before considering the system convergent.
     */
    public static final int MAX_TIMES_CONVERGENT = 25;

    /** Buffer to route our output. */
    private BufferedWriter outputBuffer;

    /**
     * Tracks the number of times we have seen the same best-to-date fitness
     * value.
     */
    private int numTimesConvergent;

    /** Fitness value of the best member of the population to date. */
    private double bestFitnessToDate;

    /**
     * Creates a new 8-point Steiner problem container. In addition to creating
     * the container, it opens the output file for writing.
     */
    public Steiner8DGeneticApp()
    {
        super();
        try
        {
            this.outputBuffer = new BufferedWriter(
                new FileWriter("8dtrial.out"));
        }
        catch (IOException ioex)
        {
            System.err.println(ioex.getMessage());
            ioex.printStackTrace();
        }

        this.numTimesConvergent = 0;
    }

    /**
     * Returns a reference to the output file for this genetic algorithm
     * application.
     *
     * @return a reference to the output buffer for this GA application.
     */
    public BufferedWriter getOutputFile()
    {
        return this.outputBuffer;
    }

    /**
     * Performs necessary initialization of this application prior to the main
     * algorithm being ran. This method generates the 8 random points within the
     * unit square that the Steiner points are trying to create a minimal
     * spanning tree with. These points are then placed in the fitness function
     * and then written out to both standard output and the output file.
     */
    public void init()
    {
        //Create the random points array to be used by the fitness function
        Point2D.Double[] pts = new Point2D.Double[8];
        Random rand = super.getRandomNumberGenerator();
        Steiner8DFitnessFunction fitFunction = (Steiner8DFitnessFunction)
            super.getFitnessFunction();

        for (int p = 0; p < pts.length; p++)
        {
            pts[p] = new Point2D.Double(rand.nextDouble(), rand.nextDouble());
        }

        //Set up these points in the fitness function for later use
        fitFunction.setPoints(pts);

        //Write these points to the file
        try
        {
            for (int p = 0; p < pts.length; p++)
            {
                this.outputBuffer.write(pts[p].toString());
                this.outputBuffer.newLine();
            }
            this.outputBuffer.flush();
        }
        catch (IOException ioex)
        {
            System.err.println(ioex.getMessage());
            ioex.printStackTrace();
        }
        finally
        {
            //Write these points out to the console
            for (int x = 0; x < pts.length; x++)
            {
                System.out.println(pts[x]);
            }
        }
    }

    /**
     * Determines if this application is convergent. Here we define convergent
     * to be dependent upon whether the fitness value of the best member to date
     * has changed in the last <code>MAX_TIMES_CONVERGENT</code> number of
     * iterations or not. If the best fitness value has not changed in that
     * number of iterations, we will say the system has converged to a solution.
     *
     * @return <code>true</code> if the best fitness value has not changed in
     *         the last <code>MAX_TIMES_CONVERGENT</code>; <code>false</code>
     *         otherwise.
     */
    public boolean isConvergent()
    {
        Population pop = super.getPopulation();
        Chromosome bestMember = (Chromosome) Collections.min(pop);

        //Determine if the best fitness value for the system has changed
        if (bestMember.getFitness() == this.bestFitnessToDate)
        {
            //Assert: the best fitness value has not changed; increment the
            //  number of times we have seen this best fitness value
            this.numTimesConvergent++;
        }
        else
        {
            //Assert: the best fitness value has changed; update the best to
            //  date fitness value for the system and reset the number of times
            //  we have seen this value to zero
            this.bestFitnessToDate = bestMember.getFitness();
            this.numTimesConvergent = 0;
        }

        //Determine if we have a system that is as close to converging as we
        //care to wait on
        if (this.numTimesConvergent < this.MAX_TIMES_CONVERGENT)
        {
            //Assert: numTimesConvergent < MAX_TIMES_CONVERGENT, so we are still
            //  not completely sure we want to consider the best member, thus
            //  the system is not convergent; return false
            return false;
        }
        else
        {
            //Assert: numTimesConvergent >= MAX_TIMES_CONVERGENT, so the best
            //  member has been the best member for at least
            //  MAX_TIMES_CONVERGENT iterations; system is convergent by our
            //  definition so return true
            return true;
        }
    }
}