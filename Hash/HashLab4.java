import java.util.*;
import java.io.*;

public class HashLab4 {
    public static String[] realHashTable = new String[128];
    public static int[] probes = new int[128];
    public static String[] originalStrings = new String[128];

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("c:/Users/Esdeath/Desktop/Hash/Words200D16.txt");
        Scanner scanner = new Scanner(file);

        int i = 0;
        while (scanner.hasNextLine() && i < 128) {
            String line = scanner.nextLine();
            originalStrings[i] = line;
            i++;
        }
        scanner.close();

        for (i = 0; i < originalStrings.length; i++) {
            int hashKey = BurrisPoorlyMadeHashFunction(originalStrings[i]);
            eightysix(originalStrings[i], "Linear", i);
            System.out.println(originalStrings[i] + " " + i + " " + hashKey);
        }

        // Print the hash table
        for (int j = 0; j < realHashTable.length; j++) {
            System.out.println("Slot " + j + ": " + realHashTable[j]);
        }
    }

    public static Integer slice(String str, int start, int end) {
        // Performes slicing function
        if (start < 0) {
            start = 0;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (start >= end) {
            return 0;
        }
        return str.charAt(start) + str.charAt(end);
    }

    public static int BurrisPoorlyMadeHashFunction(String word) {
        int key;
        double power = Math.pow(2, 10);
        if (word.length() >= 9) {
            key = Math.abs(((slice(word, 0, 1)) / 256 + (slice(word, 2, 3)) / 277 + word.charAt(4)) / (int) power
                    + word.charAt(5) / 313 + word.charAt(6) / 3 + word.charAt(9));
            key = key % 128; // Ensure the key is within the array size
        } else {
            // Handles the case when the word is too short (less than 9 characters)
            key = 0;
        }

        return key;
    }

    public static void eightysix(String word, String linOrRand, int i) {
        int key = BurrisPoorlyMadeHashFunction(word);
        int numberOfProbes = 1;

        if (linOrRand.equals("Linear")) {
            while (realHashTable[key] != null) {
                key = (key + 1) % 128;
                numberOfProbes++;
            }
            probes[i] = numberOfProbes;
            realHashTable[key] = word;
        }
    }

    public static void firstminMaxAvgProbes(int[] array, int end) {
        int max = -1, min = 800000000;
        int sum = 0;

        for (int i = end - 25; i < end; i++) {
            sum += array[i];
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
        }

        double average = sum / 25.0;
        System.out.println();
        System.out.println("Minimum probes is: " + min);
        System.out.println("Maximum probes is: " + max);
        System.out.println("Average probes is: " + average);
    }
}
