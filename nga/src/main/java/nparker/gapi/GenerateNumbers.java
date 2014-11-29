import java.awt.geom.Point2D;

public class GenerateNumbers
{
    public static void main(String[] args)
    {
        String bits = "1001011111111100111000000111001100110100011011101011010111100111111111111101010101000101";
        Point2D.Double s1, s2;

        s1 = new Point2D.Double(convertEncoding(bits.substring(0, 20)),
                                convertEncoding(bits.substring(20, 40)));
        s2 = new Point2D.Double(convertEncoding(bits.substring(40, 60)),
                                convertEncoding(bits.substring(60, 80)));

        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);

    }

    public static double convertEncoding(String bits)
    {
        //Determine if the parameter is defined
        if (bits == null)
            //Assert: parameter is undefined; throw exception
            throw new IllegalArgumentException("bits is null");
        if (bits.equals(""))
            //Assert: parameter is empty; throw exception
            throw new IllegalArgumentException("bits is an empty string");

        int asInt = Integer.parseInt(bits, 2);
        String constructDouble = String.valueOf(asInt);
        constructDouble = "0." + constructDouble;
        return Double.parseDouble(constructDouble);
    }
}