package application;
public class ElementUniqueness3 {

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
        // Create a bit array with 105 bits (13 bytes for 0 to 105)
        byte[] bitArray = new byte[106 / 8 + 1]; // to accommodate all values from 0 to 105

        int count = 0; // To track number of duplicates
        for (int num : array) {
            int byteIndex = num / 8; // Determine byte position
            int bitIndex = num % 8;   // Determine bit position within the byte

            // Check if the bit is already set
            if ((bitArray[byteIndex] & (1 << bitIndex)) == 0) {
                // If not set, set the bit
                bitArray[byteIndex] |= (1 << bitIndex);
            } else {
                // If set, we have a duplicate
                count++;
                return true; // Early return if a duplicate is found
            }
        }
        return false; // No duplicates found
    }
}
