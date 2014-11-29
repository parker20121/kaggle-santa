package nparker.steiner8d;

import nparker.gapi.util.GeneticApplication;
import nparker.gapi.util.Metric;
import nparker.gapi.structure.*;
import java.io.*;
import java.util.Collections;

/**
 * Performs the genetic drift calculation on the population of this 8-point
 * Steiner problem application.
 * @author Nathan Parker
 * @version 1.0
 */

public class Steiner8DGeneticDrift implements Metric
{
    public void evaluate(GeneticApplication ga)
    {
        Steiner8DChromosome bestMember = null;   //best member of population
        Steiner8DChromosome otherMember = null;  //other member of population
        Steiner8DGeneticApp sga = null;          //specific reference to app
        BufferedWriter out = null;               //output file reference
        Population pop = null;                   //population reference
        int popSize = 0;                         //total population size
        int count = 0;                           //total differing bits
        int chromosomeLength = 0;                //total number of genes
        double geneticDrift = 0D;                //total measure

        //Determine if the parameter is defined
        if (ga == null)
            //Assert: parameter is undefined; throw exception
            throw new IllegalArgumentException("ga is null");

        //Cast parameter to correct class
        sga = (Steiner8DGeneticApp) ga;

        //Initialize remaining variables
        out = sga.getOutputFile();
        pop = sga.getPopulation();
        bestMember = (Steiner8DChromosome) Collections.min(pop);
        chromosomeLength = bestMember.length();

        //Remove the best member from the population so that we do not compare
        //it with itself (and we need to replace it later)
        pop.remove(bestMember);

        //Get the population size (adjusting it for the removal of the best)
        popSize = pop.size();

        //Compare each member of the population to the best member and
        //calculate the genetic drift
        for (int member = 0; member < popSize; member++)
        {
            //Retrieve the member of the population to be compared
            otherMember = (Steiner8DChromosome) pop.get(member);

            //Get the member's gene pattern
            //otherPattern = (Boolean[]) otherMember.getGenePattern();

            //Perform the comparison
            for (int gene = 0; gene < chromosomeLength; gene++)
            {
                //Determine if the genes at this position are unequal
                if (!(bestMember.getGene(gene).equals(otherMember.getGene(gene))))
                {
                    count++;
                }
            }
        }

        //Calculate genetic drift
        geneticDrift = (2D * count) / ((double) (chromosomeLength * popSize));

        //Add best member back into the population
        pop.add(bestMember);

        //Get population size with all members in it
        popSize = pop.size();

        //Write information out to the file
        try
        {
            //Write population tag information first
            out.write("<population geneticDrift=\"");
            out.write(Double.toString(geneticDrift));
            out.write("\">");
            out.newLine();

            //Write all population members to the file
            for (int i = 0; i < popSize; i++)
            {
                otherMember = (Steiner8DChromosome) pop.get(i);
                out.write("   ");
                out.write(otherMember.toString());
                out.newLine();
                out.flush();
            }

            //Write end tag for population
            out.write("</population>");
            out.newLine();

            //Flush buffer contents to file
            out.flush();
        }
        catch (IOException ioex)
        {
            System.err.println(ioex.getMessage());
            ioex.printStackTrace();
        }
    }
}