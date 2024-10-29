package application;

public class LCS {
	// Function to calculate the length of LCS and build the 'b' table
	public static int[][] computeLCSLength(char[] x, char[] y, int[][] b) {
		int m = x.length;
		int n = y.length;
		int[][] c = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			c[i][0] = 0;
		}
		for (int j = 0; j <= n; j++) {
			c[0][j] = 0;
		}

		// Fill the table using dynamic programming
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (x[i - 1] == y[j - 1]) { // Characters match
					c[i][j] = c[i - 1][j - 1] + 1;
					b[i][j] = 1; // Diagonal (↖)
				} else if (c[i - 1][j] >= c[i][j - 1]) { // Take value from top
					c[i][j] = c[i - 1][j];
					b[i][j] = 2; // Up (↑)
				} else { // Take value from left
					c[i][j] = c[i][j - 1];
					b[i][j] = 3; // Left (←)
				}
			}
		}
		return c;
	}

	// Function to print the LCS by backtracking using the 'b' table
	public static void printLCS(int[][] b, char[] x, int i, int j) {
		if (i == 0 || j == 0) {
			return;
		}
		if (b[i][j] == 1) { // Diagonal
			printLCS(b, x, i - 1, j - 1);
			System.out.print(x[i - 1]); // Part of LCS
		} else if (b[i][j] == 2) { // Up
			printLCS(b, x, i - 1, j);
		} else { // Left
			printLCS(b, x, i, j - 1);
		}
	}

	// Main function to test LCS
	public static void main(String[] args) {
		String x = "ABCADC";
		String y = "MKBLAD";
		char[] X = x.toCharArray();
		char[] Y = y.toCharArray();
		int m = X.length;
		int n = Y.length;
		int[][] b = new int[m + 1][n + 1];

		int[][] c = computeLCSLength(X, Y, b);

		System.out.print("LCS of " + x + " and " + y + " is: ");
		printLCS(b, X, m, n);
	}
	/*
	 * x = < x1, x2, . . ., xi >
	 * y = < y1, y2, . . ., yj >  
	 *   |0                                            if ( i = 0 )  OR ( j = 0 )
	 *   |c [ i-1, j-1 ] + 1                           if xi = yj
	 *   |max ( c [ i-1, j ], c [ i , j-1 ] )          if xi ≠ yj 

	 * 
	 */
}
