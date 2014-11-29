package nparker.steiner;

import nparker.gapi.util.*;
import nparker.gapi.structure.*;
import java.util.Collections;
import java.io.*;

/**
 * <p>Title: Genetic Algorithm Application Programming Interface</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: IPFW</p>
 * @author Nathan Parker
 * @version 1.0
 */

public class SteinerGeneticApplication extends BaseGeneticApplication
{
    private BufferedWriter outFile;

    public SteinerGeneticApplication()
    {
        try
        {
            this.outFile = new BufferedWriter(new FileWriter("fitfloat.dat"));
        }
        catch (IOException ioex) {}
    }

    public boolean isConvergent()
    {
        Population pop = super.getPopulation();
        int popSize = pop.size();
        double totalFitness = 0D;

        try{
        for (int mem = 0; mem < popSize; mem++)
        {
            Chromosome member = (Chromosome) pop.get(mem);
            double fitVal = member.getFitness();
            totalFitness += fitVal;
            this.outFile.write(String.valueOf(fitVal));
            this.outFile.write(",");
        }
        this.outFile.newLine();
        this.outFile.flush();}
        catch (IOException ioex){}
        //Just printing out best member of population's details...
        //I'm not even sure if I should have an isConvergent method, honestly.
        Chromosome bestMember = null;

        //Get best member of population
        bestMember = (Chromosome) Collections.min(super.getPopulation());

        //Print out an "english" interpretation of the genes
        System.out.print("Best member's fitness this iteration: ");
        System.out.print(bestMember.getFitness());
        System.out.println();
        System.out.print("s1 = (" + bestMember.getGene(0) + ", " +
            bestMember.getGene(1) + "), connected to ");

        for (int i = 4; i < 8; i++)
        {
            if (((Boolean) bestMember.getGene(i)).booleanValue())
            {
                switch (i)
                {
                    case 4:
                        System.out.print("(0, 0), ");
                        break;
                    case 5:
                        System.out.print("(0, 1), ");
                        break;
                    case 6:
                        System.out.print("(1, 0), ");
                        break;
                    case 7:
                        System.out.print("(1, 1)");
                        break;
                }
            }
        }

        System.out.println();
        System.out.print("s2 = (" + bestMember.getGene(2) + ", " +
            bestMember.getGene(3) + "), connected to ");

        for (int i = 8; i < 12; i++)
        {
            if (((Boolean) bestMember.getGene(i)).booleanValue())
            {
                switch (i)
                {
                    case 8:
                        System.out.print("(0, 0), ");
                        break;
                    case 9:
                        System.out.print("(0, 1), ");
                        break;
                    case 10:
                        System.out.print("(1, 0), ");
                        break;
                    case 11:
                        System.out.print("(1, 1)");
                        break;
                }
            }
        }

        System.out.println();
        return false;
    }
}