package nparker.steiner2d;

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

public class Steiner2DGeneticApplication extends BaseGeneticApplication
{
    private BufferedWriter outFile;

    public Steiner2DGeneticApplication()
    {
        try
        {
            this.outFile = new BufferedWriter(new FileWriter("fitbits.dat"));
        }
        catch (IOException ioex)
        {
        }
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
        }catch (IOException ioex){}
        double avgFit = totalFitness / popSize;
        System.out.println("Average fitness of " + popSize + " members: "
            + avgFit);

        //Just printing out best member of population's details...
        //I'm not even sure if I should have an isConvergent method, honestly.
        Steiner2DChromosome bestMember = null;
        String bits = null;

        //Get best member of population
        bestMember = (Steiner2DChromosome) Collections.min(super.getPopulation());
        bits = bestMember.toBitString();

        //Print out an "english" interpretation of the genes
        System.out.print("Best member's fitness this iteration: ");
        System.out.print(bestMember.getFitness());
        System.out.println();
        System.out.println("Bit pattern: " + bits);
        System.out.println("s1 = (" + this.convertEncoding(bits.substring(0, 20))
            + ", " + this.convertEncoding(bits.substring(20, 40)) + ")");
        System.out.println("s2 = (" + this.convertEncoding(bits.substring(40, 60))
            + ", " + this.convertEncoding(bits.substring(60, 80)) + ")");
        System.out.print("s1 = (" + bestMember.getGene(80) + ", " +
            bestMember.getGene(81) + ", " + bestMember.getGene(82) + ", " +
            bestMember.getGene(83) + "), connected to ");

        for (int i = 80; i < 84; i++)
        {
            if (((Boolean) bestMember.getGene(i)).booleanValue())
            {
                switch (i)
                {
                    case 80:
                        System.out.print("(0, 0), ");
                        break;
                    case 81:
                        System.out.print("(0, 1), ");
                        break;
                    case 82:
                        System.out.print("(1, 0), ");
                        break;
                    case 83:
                        System.out.print("(1, 1)");
                        break;
                }
            }
        }

        System.out.println();
        System.out.print("s2 = (" + bestMember.getGene(84) + ", " +
            bestMember.getGene(85) + ", " + bestMember.getGene(86) + ", " +
            bestMember.getGene(87) + "), connected to ");

        for (int i = 84; i < 88; i++)
        {
            if (((Boolean) bestMember.getGene(i)).booleanValue())
            {
                switch (i)
                {
                    case 84:
                        System.out.print("(0, 0), ");
                        break;
                    case 85:
                        System.out.print("(0, 1), ");
                        break;
                    case 86:
                        System.out.print("(1, 0), ");
                        break;
                    case 87:
                        System.out.print("(1, 1)");
                        break;
                }
            }
        }

        System.out.println();
        System.out.println();
        return false;
    }

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