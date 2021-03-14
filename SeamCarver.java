
import edu.princeton.cs.algs4.Picture;

import java.awt.Color;
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
        int[] horizontalSeam = new int[picture.width()];

        Picture oldPicture = picture;
        picture = transpose(picture);
        int[] seam = findSeam(picture.height());
        picture = oldPicture;
        return seam;

    }

    private int[] findSeam(int length) {
        int[] verticalSeam = new int[length];

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

    /*Returns the index of the minimum energy of the pixels at
      indices (x1, y), (x2, y), (x3, y).*/
    private int minimumEnergy(int y, int x1, int x2, int x3) {
        return minimum(minimum(x1, x2, y), x3, y);

    }
    /*Returns the minimum of pixels (x1, y) and (x2, y) */
    private int minimum(int x1, int x2, int y) {
        return (energy(x1, y) < energy(x2, y)) ? x1 : x2;
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != picture.width()) {
            throw new IllegalArgumentException();
        }
        SeamRemover.removeHorizontalSeam(picture, findHorizontalSeam());
    }


    public void removeVerticalSeam(int[] seam) {
        if (seam.length != picture.height()) {
            throw new IllegalArgumentException();
        }
        SeamRemover.removeVerticalSeam(picture, findVerticalSeam());
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
        Picture p = new Picture("images/joker.jpg");
        SeamCarver sc = new SeamCarver(p);

        SCUtility.showEnergy(sc);

    }

}
