package nparker.steiner2d;

/**
 * <p>Title: Genetic Algorithm Application Programming Interface</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: IPFW</p>
 * @author Nathan Parker
 * @version 1.0
 */

import nparker.gapi.BitPatternChromosome;

public class Steiner2DChromosome extends BitPatternChromosome
{
    public static final int LENGTH = 88;

    public Steiner2DChromosome()
    {
        super(LENGTH);

        for (int i = 0; i < this.LENGTH; i++)
        {
            if (Math.random() > 0.5)
                super.setGene(i, Boolean.TRUE);
            else
                super.setGene(i, Boolean.FALSE);
        }
    }
}