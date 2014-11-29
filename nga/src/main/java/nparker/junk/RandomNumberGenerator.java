package nparker.junk;
//package nparker.gapi.util;

/**
 * Title:        Genetic Algorithm Application Programming Interface
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      IPFW
 * @author Nathan Parker
 * @version 1.0
 */

public interface RandomNumberGenerator
{
    public boolean nextBoolean();

    public void nextBytes(byte[] bytes);

    public double nextDouble();

    public float nextFloat();

    public double nextGaussian();

    public int nextInt();

    public int nextInt(int n);

    public long nextLong();

    public void setSeed(long seed);
}