
import edu.princeton.cs.algs4.Picture;

import java.util.Arrays;

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

    /*
    The findVerticalSeam() method returns an array of length H
    such that entry x is the column number of the pixel to be
    removed from row x of the image. */
    public int[] findHorizontalSeam() {
        int[] horizontalSeam = new int[picture.height()];
        return null;
    }

    /*MY APPROACH*/
    public int[] findVerticalSeam() {
        int[] verticalSeam = new int[picture.height()];

        int row = 0;
        double minTopRowEnergy = energy(0, 0);
        int minEnergyIndex = 0;
        for (int i = 1; i < picture.width(); i += 1) {
            double energy = energy(i, row);
            if (energy < minTopRowEnergy) {
                minTopRowEnergy = energy;
                minEnergyIndex = i;
            }
        }
        verticalSeam[0] = minEnergyIndex;

        row += 1;
        for (int i = 1; i < verticalSeam.length; i += 1) {
            if (row >= picture.height()) break;
            int rowMinEnergyIndex = minimumEnergy(row,
                    minEnergyIndex + 1, minEnergyIndex, minEnergyIndex - 1);
            verticalSeam[i] = rowMinEnergyIndex;
            minEnergyIndex = rowMinEnergyIndex;
            row += 1;
        }

        return verticalSeam;
    }


    /*Returns the index of the minimum energy of the pixels at
    indices (x1, y), (x2, y), (x3, y).*/
    private int minimumEnergy(int y, int x1, int x2, int x3) {
        double energy1 = energy(x1, y);
        double energy2 = energy(x2, y);
        double energy3 = energy(x3, y);

        System.out.println("Energy 1 : " + energy1);
        System.out.println("Energy 2 : " + energy2);
        System.out.println("Energy 3 : " + energy3);



        int minEnergyIndex;
        if (energy1 < energy2) minEnergyIndex = x1;
        else if (energy3 < energy1) minEnergyIndex = x3;
        else minEnergyIndex = x2;
        return minEnergyIndex;
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
        Picture p = new Picture("images/6x5.png");
        SeamCarver sc = new SeamCarver(p);

        int[] realVerticalSeam = {3, 4, 3, 2, 2};
        int[] testVerticalSeam = sc.findVerticalSeam();

        System.out.println(Arrays.toString(realVerticalSeam));
        System.out.println(Arrays.toString(testVerticalSeam));

    }

}
