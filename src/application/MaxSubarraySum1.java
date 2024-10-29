package application;


public class MaxSubarraySum1 {

    public static void main(String[] args) {
        // Example input
        double[] inputArray = {1.5, -2.3, 3.7, 4.2, -1.0, 2.5, -3.1, 5.0};

        // Finding maximum sum of contiguous subarray
        double maxSum = findMaxSubarraySum(inputArray);
        System.out.println("Maximum sum of any contiguous subarray: " + maxSum);
    }

    public static double findMaxSubarraySum(double[] A) {
        double max = Double.NEGATIVE_INFINITY; // Initialize max to the smallest possible value

        int n = A.length;

        // Check all possible subarrays
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                double sum = 0; // Initialize sum for the current subarray
                // Calculate the sum of the current subarray A[i] to A[j]
                for (int k = i; k <= j; k++) {
                    sum += A[k];
                }
                // Update max if the current sum is greater
                if (max < sum) {
                    max = sum;
                }
            }
        }
        return max; // Return the maximum sum found
    }
}
