package application;

import java.util.Arrays;

public class MatrixChainMultiplication {

    // Function to perform Matrix Chain Multiplication
    public static void main(String[] args) {
        // Dimensions of the matrices (A, B, C, D, E, F)
        int[] dimensions = {4, 2, 3, 1, 2, 2, 3}; // r array for dimensions
        int n = dimensions.length - 1; // Number of matrices

        // Initialize the cost and split matrices
        int[][] cost = new int[n][n];
        int[][] best = new int[n][n];

        // Calculate the minimum cost of multiplication
        for (int length = 2; length <= n; length++) { // length of the chain
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1; // end index of the chain
                cost[i][j] = Integer.MAX_VALUE; // Initialize to max value
                // Try all possible split points
                for (int k = i; k < j; k++) {
                    int t = cost[i][k] + cost[k + 1][j] + dimensions[i] * dimensions[k + 1] * dimensions[j + 1];
                    if (t < cost[i][j]) {
                        cost[i][j] = t; // Update minimum cost
                        best[i][j] = k; // Update best split point
                    }
                }
            }
        }

        // Print the minimum cost and the optimal parenthesization
        System.out.println("Minimum cost of multiplication: " + cost[0][n - 1]);
        System.out.println("Optimal parenthesization: ");
        printOptimalParenthesization(best, 0, n - 1);
    }

    // Function to print the optimal parenthesization
    private static void printOptimalParenthesization(int[][] best, int i, int j) {
        if (i == j) {
            System.out.print("A" + (i + 1));
        } else {
            System.out.print("(");
            printOptimalParenthesization(best, i, best[i][j]);
            printOptimalParenthesization(best, best[i][j] + 1, j);
            System.out.print(")");
        }
    }
    
//    If ( i = j ) ➔ m[ I, j ] = 0 
//    If ( i < j ) ➔ m[ i, j ] = min { m[ i, k ] + m[ k+1, j ] + pi-1 * pk * pj } 
//                                        i ≤ k ≤ j
//    If ( i > j ) ➔ x
}
