import edu.princeton.cs.algs4.Picture;

import java.awt.*;
import java.util.Arrays;

public class SeamFinder {

    public static void main(String[] args) {

    }

    public static int[] findHorizontalSeam(Picture p) {
        Picture x = transpose(p);
        int[] seam = findSeam(x, x.height());

        return seam;
    }

    public static int[] findVerticalSeam(Picture p) {
        return findSeam(p, p.height());

    }

    private static void initializeTopRow(Picture p, double[][] M) {
        for (int i = 0; i < M.length; i += 1) {
            M[i][0] = Energy.getEnergy(p, i, 0);
        }

    }


    public static int[] findSeam(Picture p, int length) {
        int[] seam = new int[length];
        double[][] M = new double[p.width()][p.height()];
        initializeTopRow(p, M);
        seam[0] = minimumTopRow(M);
        int s = 1;
        for (int i = 1; i < p.height(); i += 1) {
            for (int j = 0; j < p.width(); j += 1) {

                //M[i][j] = Energy.getEnergy(p, i, j) +
                //        minimum(M[j - 1][i - 1], M[j][i - 1], M[j + 1][i - 1]);

                M[j][i] = Energy.getEnergy(p, i, j) + minEnergy(p, i, j);


            }
        }

        for (double[] row : M) {
            System.out.println(Arrays.toString(row));
        }

        return seam;
    }

    private static double minEnergy(Picture p, int i, int j) {
        boolean topLeft = true;
        boolean topRight = true;
        if (j - 1 < 0) topLeft = false;
        if (j + 1 > p.width()) topRight = false;
        if (!topLeft) {
            return minimum(Energy.getEnergy(p, i, j - 1), Energy.getEnergy(p, i + 1, j - 1));
        }
        if (!topRight) {
            return minimum(Energy.getEnergy(p, i - 1, j - 1), Energy.getEnergy(p, i, j - 1));
        }
        return minimum(minimum(Energy.getEnergy(p, i - 1, j - 1),
                                Energy.getEnergy(p, i, j - 1)),
                                Energy.getEnergy(p, i + 1, j - 1));
    }




    private static int minimumTopRow(double[][] M) {
        int index = 0;
        double minEnergy = M[0][0];
        for (int i = 0; i < M.length; i += 1) {
            if (M[i][0] < minEnergy) {
                index = i;
            }
        }
        return index;
    }


    private static double minimum(double e1, double e2, double e3) {
        return minimum(minimum(e1, e2), e3);
    }

    private static double minimum(double e1, double e2) {
        return (e1 < e2) ? e1 : e2;
    }

    private static Picture transpose(Picture p) {
        Picture x = new Picture(p.height(), p.width());

        for (int i = 0; i < p.width(); i += 1) {
            for (int j = 0; j < p.height(); j += 1) {
                Color c = p.get(i, j);
                x.set(j, i, c);
            }
        }
        return x;
    }


}
