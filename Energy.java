
import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

/**
 * Utility class that calculates the energy of a
 * pixel on an picture.
 */

public class Energy {

    /*
    Takes in a Picture, and a pixel(x, y) on that Picture
    and returns the energy of that pixel.
     */
    public static double getEnergy(Picture p, int x, int y) {

        Color x2 = getColor(p, x + 1, y);
        Color x1 = getColor(p, x - 1, y);

        Color y2 = getColor(p, x, y + 1);
        Color y1 = getColor(p, x, y - 1);

        return energyGradient(x2, x1) + energyGradient(y2, y1);
    }

    /*
    Calculates the absolute differences in the red, green, and blue
    components of 2 colors. Returns sum of the square of the differences.
     */
    private static double energyGradient(Color c1, Color c2) {
        double R2 = c2.getRed();
        double G2 = c2.getGreen();
        double B2 = c2.getBlue();

        double R1 = c1.getRed();
        double G1 = c1.getGreen();
        double B1 = c1.getBlue();

        double RDiff = abs(R2, R1);
        double GDiff = abs(G2, G1);
        double BDiff = abs(B2, B1);

        return square(RDiff) + square(GDiff) + square(BDiff);
    }

    /*Gets the Color object associated with a particular pixel.*/
    private static Color getColor(Picture p, int x, int y) {
        if (x >= p.width()) x = 0;
        if (y >= p.height()) y = 0;
        if (x < 0) x = p.width() - 1;
        if (y < 0) y = p.height() - 1;
        return new Color(p.getRGB(x, y));
    }

    /*Returns absolute value of difference between x and y.*/
    private static double abs(double x, double y) {
        return (x - y < 0) ? (x - y) * -1 : (x - y);
    }

    /*Returns square of x.*/
    private static double square(double x) {
        return x * x;
    }
}
