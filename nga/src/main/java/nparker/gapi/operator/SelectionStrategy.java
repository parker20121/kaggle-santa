package nparker.gapi.operator;

import nparker.gapi.structure.Population;
import java.util.Random;

/**
 * Title:        Genetic Algorithm Application Programming Interface
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      IPFW
 * @author Nathan Parker
 * @version 1.0
 */

public interface SelectionStrategy
{
    //Return either a Population of selected Chromosomes, an array of selected
    //Chromosomes, or an array of indices of selected Chromosomes
    public Population select(Population p, Random rand);
}