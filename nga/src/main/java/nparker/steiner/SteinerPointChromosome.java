package nparker.steiner;

import nparker.gapi.structure.BaseChromosome;

/**
 * <p>Represents a chromosome in a Steiner Point genetic algorithm problem. With
 * this class, instead of representing the solution as a bit pattern, here it
 * is being represented as an array of genes which are <code>Object</code>s.
 * Specifically, each of the coordinates of the two steiner points are
 * represented as <code>Float</code>s while each of the connections between
 * the steiner points and the points on the unit square are represented as
 * <code>Boolean</code>s. Thus, the composition of a given chromosome will be
 * an array with the following composition:</p>
 *
 * <ol start="0">
 *    <li>x-coordinate of the first steiner point (x<sub>0</sub>).</li>
 *    <li>y-coordinate of the first steiner point (y<sub>0</sub>).</li>
 *    <li>x-coordinate of the second steiner point (x<sub>1</sub>).</li>
 *    <li>y-coordinate of the second steiner point (y<sub>1</sub>).</li>
 *    <li>(x<sub>0</sub>, y<sub>0</sub>) connected to (0, 0).</li>
 *    <li>(x<sub>0</sub>, y<sub>0</sub>) connected to (0, 1).</li>
 *    <li>(x<sub>0</sub>, y<sub>0</sub>) connected to (1, 0).</li>
 *    <li>(x<sub>0</sub>, y<sub>0</sub>) connected to (1, 1).</li>
 *    <li>(x<sub>1</sub>, y<sub>1</sub>) connected to (0, 0).</li>
 *    <li>(x<sub>1</sub>, y<sub>1</sub>) connected to (0, 1).</li>
 *    <li>(x<sub>1</sub>, y<sub>1</sub>) connected to (1, 0).</li>
 *    <li>(x<sub>1</sub>, y<sub>1</sub>) connected to (1, 1).</li>
 * </ol>
 *
 * @author Nathan Parker
 * @version 1.0
 */

public class SteinerPointChromosome extends BaseChromosome
{
    /**
     * Number of genes prior to beginning of the connections array.
     */
    protected final int CONNECTION_OFFSET = 4;

    /**
     * x-coordinate of the first Steiner point.
     */
    private Float x0;

    /**
     * y-coordinate of the first Steiner point.
     */
    private Float y0;

    /**
     * x-coordinate of the second Steiner point.
     */
    private Float x1;

    /**
     * y-coordinate of the second Steiner point.
     */
    private Float y1;

    /**
     * Bit array representing connections between the Steiner points and the
     * points <code>(0, 0), (0, 1), (1, 0), (1, 1)</code>.
     */
    private Boolean[] connections;

    /**
     * Creates a <code>SteinerPointChromsome</code> with randomly assigned
     * values for the coordinates of the two steiner points and connections
     * array. This constructor uses Math.random() to generate the random
     * numbers used in creating the two steiner points and generating the
     * connections array.
     */
    public SteinerPointChromosome()
    {
        //Initialize each data member to some random value
        this.x0 = new Float(Math.random());
        this.y0 = new Float(Math.random());
        this.x1 = new Float(Math.random());
        this.y1 = new Float(Math.random());

        //Initialize connections array
        this.connections = new Boolean[8];
        for (int i = 0; i < connections.length; i++)
        {
            if (Math.random() < 0.5)
                this.connections[i] = Boolean.FALSE;
            else
                this.connections[i] = Boolean.TRUE;
        }

        //Initialize fitness value
        super.setFitness(0D);
    }

    /**
     * Creates a <code>SteinerPointChromsome</code> using the specified float
     * values to create the two steiner points and a randomly generating the
     * connections array. This constructor uses Math.random() to generate the
     * random numbers used in generating the connections array.
     *
     * @param x0 x-value of the first steiner point.
     * @param y0 y-value of the first steiner point.
     * @param x1 x-value of the second steiner point.
     * @param y1 y-value of the second steiner point.
     */
    public SteinerPointChromosome(float x0, float y0, float x1, float y1)
    {
        this.x0 = new Float(x0);
        this.y0 = new Float(y0);
        this.x1 = new Float(x1);
        this.y1 = new Float(y1);

        //Initialize connections array
        this.connections = new Boolean[8];
        for (int i = 0; i < connections.length; i++)
        {
            if (Math.random() < 0.5)
                this.connections[i] = Boolean.FALSE;
            else
                this.connections[i] = Boolean.TRUE;
        }

        //Initialize fitness value
        super.setFitness(0D);
    }

    /**
     * Creates a <code>SteinerPointChromosome</code> using the specified float
     * values to create the two steiner points and the specified boolean array
     * to create the array of connections.
     *
     * @param x0   x-value of the first steiner point.
     * @param y0   y-value of the first steiner point.
     * @param x1   x-value of the second steiner point.
     * @param y1   y-value of the second steiner point.
     * @param conn array representing the connections between the given steiner
     *             points and the points on the unit square.
     */
    public SteinerPointChromosome(float x0, float y0, float x1, float y1,
        boolean[] conn)
    {
        if ((conn == null) || (conn.length != 8))
            throw new IllegalArgumentException("Connections array not " +
                "properly defined.");

        this.x0 = new Float(x0);
        this.y0 = new Float(y0);
        this.x1 = new Float(x1);
        this.y1 = new Float(y1);

        this.connections = new Boolean[conn.length];
        for (int i = 0; i < this.connections.length; i++)
            this.connections[i] = Boolean.valueOf(conn[i]);
    }

    public Object getGene(int geneIndex)
    {
        //Retrieve the appropriate gene
        switch (geneIndex)
        {
            case 0:
                return this.x0;
            case 1:
                return this.y0;
            case 2:
                return this.x1;
            case 3:
                return this.y1;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return this.connections[geneIndex - this.CONNECTION_OFFSET];
            default:
                throw new ArrayIndexOutOfBoundsException("geneIndex = " +
                    geneIndex + " references a gene outside the pattern in " +
                    "this pattern in SteinerPointChromosome.getGene().");
        }
    }

    /**
     *
     * @param geneIndex
     * @param gene
     */
    public void setGene(int geneIndex, Object gene)
    {
        if (gene == null)
            throw new IllegalArgumentException("parameter gene is null in " +
                "SteinerPointChromosome.setGene()");

        switch (geneIndex)
        {
            case 0:
                if (!(gene instanceof Number))
                    throw new ClassCastException();

                this.x0 = new Float(((Number) gene).floatValue());
                break;

            case 1:
                if (!(gene instanceof Number))
                    throw new ClassCastException();

                this.y0 = new Float(((Number) gene).floatValue());
                break;

            case 2:
                if (!(gene instanceof Number))
                    throw new ClassCastException();

                this.x1 = new Float(((Number) gene).floatValue());
                break;

            case 3:
                if (!(gene instanceof Number))
                    throw new ClassCastException();

                this.y1 = new Float(((Number) gene).floatValue());
                break;

            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                if (!(gene instanceof Boolean))
                    throw new ClassCastException();

                if (((Boolean) gene).booleanValue())
                    this.connections[geneIndex - this.CONNECTION_OFFSET] =
                        Boolean.TRUE;
                else
                    this.connections[geneIndex - this.CONNECTION_OFFSET] =
                        Boolean.FALSE;
                break;

            default:
                throw new ArrayIndexOutOfBoundsException("geneIndex = " +
                    geneIndex + " references a gene outside the pattern in " +
                    "this pattern in SteinerPointChromosome.setGene().");
        }
    }

    /**
     * Returns the number of genes in this chromosome.
     *
     * @return the number of genes.
     */
    public int length()
    {
        return this.CONNECTION_OFFSET + this.connections.length;
    }
}