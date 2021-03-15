
import edu.princeton.cs.algs4.Picture;
import java.awt.Color;

/**
 * @author Mohammad Mahmud
 * CS 61B SP18 Seam Carving
 * This class represents a data type that resizes
 * a W-by-H image using the seam-carving technique.
 */

public class SeamCarver {

    private Picture picture;

    public SeamCarver(Picture picture) {
        this.picture = picture;
    }

    public Picture picture() {
        return picture;
    }

    public int width() {
        return picture.width();
    }

    public int height() {
        return picture.height();
    }

    /*Calls helper method in Energy class to get energy of pixel*/
    public double energy(int x, int y) {
        checkIndex(x, y);
        return Energy.getEnergy(picture, x, y);
    }

    /*This method takes in the length of the seam to be computed.
    * First, find the minimum energy at the top row of the image.
    * Use the index of the minimum top row energy to find the remaining
    * indices of the path of lowest total energy in image.
    * At each row, check the row above's left, center, and right column
    * The minimum energy of the 3 pixels is added to the minimum energy path.
    * */
    private int[] findSeam(int length) {
        int[] seam = new int[length];
        seam[0] = topRowMinEnergyIndex();
        int row = 1;
        for (int i = 1; i < length; i += 1) {
            int topBest = seam[i - 1];
            seam[i] = minimumEnergy(topBest - 1,
                                    topBest,
                                    topBest + 1, row);
            row += 1;
        }

        return seam;
    }

    /*Returns index of pixel with lowest energy in first row. */
    private int topRowMinEnergyIndex() {
        int minIndex = 0;
        double minEnergy = energy(0, 0);
        for (int i = 1; i < picture.width(); i += 1) {
            double currEnergy = energy(i, 0);
            if (currEnergy < minEnergy) {
                minEnergy = currEnergy;
                minIndex = i;
            }
        }
        return minIndex;
    }

    /*Returns the index of the minimum energy of the pixels at
      indices (x1, y), (x2, y), (x3, y). */
    private int minimumEnergy(int x1, int x2, int x3, int y) {
        boolean left = x1 > 0;
        boolean right = x3 < picture.width();
        if (!left) return minimum(x2, x3, y);
        if (!right) return minimum(x1, x2, y);
        else return minimum(minimum(x1, x2, y), x3, y);

    }
    /*Returns the minimum of pixels (x1, y) and (x2, y) */
    private int minimum(int x1, int x2, int y) {
        return (energy(x1, y) < energy(x2, y)) ? x1 : x2;
    }

    /*Transpose the image and find the seam, then
    * transpose the image again to get back original image. */
    public int[] findHorizontalSeam() {
        picture = transpose(picture);
        int[] seam = findSeam(picture.height());
        picture = transpose(picture);
        return seam;
    }

    /*Call helper method to find the vertical seam of this picture.*/
    public int[] findVerticalSeam() {
        return findSeam(picture.height());
    }

    /*Call SeamRemover method to remove given horizontal seam. */
    public void removeHorizontalSeam(int[] seam) {
        picture = SeamRemover.removeHorizontalSeam(picture, seam);
    }

    /*Call SeamRemover method to remove given vertical seam. */
    public void removeVerticalSeam(int[] seam) {
        picture = SeamRemover.removeVerticalSeam(picture, seam);
    }

    /*Transpose a Picture by flipping its row and column pixels.
    Pixel (x, y) on Picture 1 becomes pixel (y, x) on Picture 2 */
    private Picture transpose(Picture p) {
        Picture x = new Picture(p.height(), p.width());
        for (int i = 0; i < p.width(); i += 1) {
            for (int j = 0; j < p.height(); j += 1) {
                Color c = p.get(i, j);
                x.set(j, i, c);
            }
        }
        return x;
    }

    /*Throw Exception if indices are out of bounds */
    private void checkIndex(int x, int y) {
        if (x < 0 || x >= picture.width()) {
            throw new IndexOutOfBoundsException();
        }
        if (y < 0 || y >= picture.height()) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static void main(String[] args) {
        Picture p = new Picture("images/nyc.jpg");
        SeamCarver sc = new SeamCarver(p);

        /*Reduce nyc.jpg dimensions by 40% by removing 512 vertical seams
        and 270 horizontal seams. 1280x675 --> 768x405. */
        for (int i = 0; i < 512; i += 1) {
            sc.removeVerticalSeam(sc.findVerticalSeam());
        }

        for (int i = 0; i < 270; i += 1) {
            sc.removeHorizontalSeam(sc.findHorizontalSeam());
        }

        sc.picture().save("output-nyc.jpg");

    }
}
