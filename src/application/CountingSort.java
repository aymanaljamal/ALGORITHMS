package application;
import java.util.Arrays;

public class CountingSort {

    public static void main(String[] args) {
        // Example input array (1 to k)
        int[] A = {4, 2, 2, 8, 3, 3, 1, 5, 4};
        int k = 8; // Maximum value in A

        // Perform counting sort
        int[] sortedArray = countingSort(A, k);

        // Output the sorted array
        System.out.println("Sorted array: " + Arrays.toString(sortedArray));
    }

    public static int[] countingSort(int[] A, int k) {
        int n = A.length; // Length of input array
        int[] b = new int[n]; // Output array
        int[] c = new int[k + 1]; // Counting array

        // Step 1: Initialize counting array
        for (int i = 1; i <= k; i++) {
            c[i] = 0;
        }

        // Step 2: Count occurrences of each number
        for (int i = 0; i < n; i++) {
            c[A[i]]++;
        }

        // Step 3: Construct the output array
        int count = 0; // Initialize index for output array
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < c[i]; j++) {
                b[count++] = i; // Place the number i in the output array as many times as it occurs
            }
        }

        return b; // Return the sorted array
    }
}
