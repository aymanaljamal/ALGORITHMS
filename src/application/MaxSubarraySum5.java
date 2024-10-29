package application;

public class MaxSubarraySum5 {

    public static void main(String[] args) {
        // Example input array
        int[] inputArray = {5, 2, -1, -3, -5, 6, 4, -1, 2, 5, -3, 1, -4, 5};

        // Finding maximum sum of contiguous subarray
        int maxSum = findMaxSubarraySum(inputArray);
        System.out.println("Maximum sum of any contiguous subarray: " + maxSum);
    }

    public static int findMaxSubarraySum(int[] A) {
        int maxEndHere = 0; // Maximum sum ending at the current position
        int maxSoFar = Integer.MIN_VALUE; // Maximum sum found so far

        // Iterate through the array
        for (int i = 0; i < A.length; i++) {
            maxEndHere = Math.max(0, maxEndHere + A[i]); // Update maxEndHere
            maxSoFar = Math.max(maxSoFar, maxEndHere); // Update maxSoFar
        }

        return maxSoFar; // Return the maximum sum found
    }
}
