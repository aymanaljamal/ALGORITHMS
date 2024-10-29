package application;

public class ElementUniqueness2 {

    public static void main(String[] args) {
        // Example input array
        int[] inputArray = {5, 7, 6, 3, 0, 6, 5, 9, 11, 16, 3, 5, 10};

        // Check for element uniqueness
        boolean hasDuplicates = checkElementUniqueness(inputArray);
        if (hasDuplicates) {
            System.out.println("There are duplicates in the array.");
        } else {
            System.out.println("All elements are unique.");
        }
    }

    public static boolean checkElementUniqueness(int[] array) {
        // Create an integer array of size 106 to count occurrences
        int[] count = new int[106]; // 0 to 105

        // Iterate through the input array
        for (int num : array) {
            // Increment the count for the encountered number
            count[num]++;

            // If count exceeds 1, we found a duplicate
            if (count[num] > 1) {
                return true; // Duplicate found
            }
        }
        return false; // No duplicates found
    }
}
