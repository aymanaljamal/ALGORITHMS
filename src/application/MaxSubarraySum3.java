package application;

public class MaxSubarraySum3 {

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

        // Outer loop to set the starting index of the subarray
        for (int i = 0; i < n; i++) {
            double sum = 0; // Initialize sum for the current subarray
            // Inner loop to set the ending index of the subarray
            for (int j = i; j < n; j++) {
                sum += A[j]; // Incrementally add A[j] to sum
                // Update max if the current sum is greater
                if (max < sum) {
                    max = sum;
                }
            }
        }
        return max; // Return the maximum sum found
    }
}

