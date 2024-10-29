package application;

public class ElementUniqueness1 {

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
        // Create a byte array of size 100,000 (105 bytes)
        byte[] seen = new byte[100001]; // 0-100000

        // Iterate through the input array
        for (int num : array) {
            // Check if the number has already been seen
            if (seen[num] == 1) {
                return true; // Duplicate found
            } else {
                // Mark this number as seen
                seen[num] = 1; // Change byte from 0 to 1
            }
        }
        return false; // No duplicates found
    }
}
