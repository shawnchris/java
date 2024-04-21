package ebay;

/**
 * You are given an image, represented as a matrix of integers, where each integer corresponds to a color. The number in
 * the ith (0-based) row and jth (0-based) column represents the color of the pixel in the ith row and jth column of the
 * image.
 * Your task is to increase the sharpness of the image. In order to do that, you need to replace each number of the
 * matrix with the average of the numbers in the neighboring cells. We assume that two cells are neighbors if they share
 * at least one corner. The cell itself is not considered part of the average; only the 8 surrounding neighbours (or
 * fewer if the cell is against an edge).
 */
public class Oa3 {
    double[][] solution(int[][] img) {
        double[][] sharpened = new double[img.length][img[0].length];
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                int sum = 0;
                int count = 0;
                for (int di = -1; di < 2; di++) {
                    for (int dj = -1; dj < 2; dj++) {
                        int ii = i + di;
                        int jj = j + dj;
                        if (ii < 0 || ii >= img.length || jj < 0 || jj >= img[0].length || (ii == i && jj == j)) {
                            continue;
                        }
                        sum += img[ii][jj];
                        count++;
                    }
                }
                sharpened[i][j] = (double)sum / count;
            }
        }
        return sharpened;
    }
}
