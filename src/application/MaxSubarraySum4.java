package application;

public class MaxSubarraySum4 {

    public static void main(String[] args) {
        // Example input
        double[] inputArray = {1.5, -2.3, 3.7, 4.2, -1.0, 2.5, -3.1, 5.0};

        // Finding maximum sum of contiguous subarray
        double maxSum = findMaxSubarraySum(inputArray);
        System.out.println("Maximum sum of any contiguous subarray: " + maxSum);
    }

    public static double findMaxSubarraySum(double[] A) {
        int n = A.length;

        // Step 1: Create the accumulation array
        double[] accumArray = new double[n + 1];
        accumArray[0] = 0; // Initialize the first element

        // Fill the accumulation array
        for (int i = 1; i <= n; i++) {
            accumArray[i] = accumArray[i - 1] + A[i - 1]; // A[i-1] because A is zero-indexed
        }

        // Step 2: Find the maximum sum using the accumulation array
        double max = Double.NEGATIVE_INFINITY; // Initialize max to the smallest possible value

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                double sum = accumArray[j] - accumArray[i - 1]; // Calculate sum using the accumulation array
                if (max < sum) {
                    max = sum; // Update max if the current sum is greater
                }
            }
        }
        return max; // Return the maximum sum found
    }
}
