package nparker.junk;
//package nparker.gapi.util;

//import nparker.gapi.util.RandomNumberGenerator;
import java.util.Random;

/**
 *
 *
 * @author Nathan Parker
 * @version 1.0
 */

public class JavaRandom extends Random implements RandomNumberGenerator
{
    public JavaRandom()
    {
        super();
    }

    public JavaRandom(long seed)
    {
        super(seed);
    }

    public boolean nextBoolean()
    {
        return super.nextBoolean();
    }

    public void nextBytes(byte[] bytes)
    {
        super.nextBytes(bytes);
    }

    public double nextDouble()
    {
        return super.nextDouble();
    }

    public float nextFloat()
    {
        return super.nextFloat();
    }

    public double nextGaussian()
    {
        return super.nextGaussian();
    }

    public int nextInt()
    {
        return super.nextInt();
    }

    public int nextInt(int n)
    {
        return super.nextInt(n);
    }

    public long nextLong()
    {
        return super.nextLong();
    }

    public void setSeed(long seed)
    {
        super.setSeed(seed);
    }
}
