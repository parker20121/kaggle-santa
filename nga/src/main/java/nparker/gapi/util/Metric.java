package nparker.gapi.util;

import nparker.gapi.structure.*;
import java.io.FileOutputStream;

/**
 * Defines a way of performing calculations across the
 * <code>GeneticApplication</code> or any part of a particular genetic
 * algorithm. Any methods here that are not implemented from this interface
 * should throw <code>UnsupoortedOperationException</code>.
 *
 * @author Nathan Parker
 * @version 1.0
 */

public interface Metric
{
    /**
     * Runs the calculations of this <code>Metric</code> on the specified
     * genetic application.
     *
     * @param ga the <code>GeneticApplication</code> to be examined.
     */
    public void evaluate(GeneticApplication ga);
}