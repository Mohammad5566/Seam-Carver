
import edu.princeton.cs.algs4.Picture;

/**
 * @author Mohammad Mahmud
 * CS 61B SP18 HW5: Seam Carving
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

    public double energy(int x, int y) {
        checkIndex(x, y);
        return Energy.getEnergy(picture, x, y);
    }

    public int[] findHorizontalSeam() {
        int[] horizontalSeam = new int[picture.height()];
        return null;
    }

    public int[] findVerticalSeam() {
        int[] verticalSeam = new int[picture.width()];

        return null;
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != picture.width()) {
            throw new IllegalArgumentException();
        }


    }

    public void removeVerticalSeam(int[] seam) {
        if (seam.length != picture.height()) {
            throw new IllegalArgumentException();
        }
        
    }

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


        System.out.println(sc.energy(2, 0));

        Picture energyPicture = SCUtility.toEnergyPicture(sc);
        energyPicture.show();

        energyPicture.save("nyc-energy");

    }

}
