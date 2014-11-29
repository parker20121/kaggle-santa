package nparker.gapi.util;

import nparker.gapi.structure.Chromosome;

/**
 * This is way too fun.
 *
 * @author Nathan Parker
 * @version 1.0
 */

public interface FitnessFunction
{
    public double evaluateFitness(Chromosome chromosome);
}