import java.util.*;
import java.io.*;

public class Hash {

    public static String[] realHashTable = new String[128];
    public static int[] probes = new int[128];
    public static String[] originalStrings = new String[128];

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("c:/Users/Esdeath/Desktop/Hash/Words200D16.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            for (int i = 0; i <= 127; i++) {
                System.out.println("in for loop" + i); // puts all of my strings from file
                // into an array
                String line = scanner.nextLine();
                originalStrings[i] = line;
            }
            break;
        }
        scanner.close();
        for (int i = 0; i < originalStrings.length; i++) { // pritns my array to make
            // sure it works
            System.out.println(originalStrings[i] + " " + i + " " +
                    burisHash(originalStrings[i]));
        }
        for (int i = 1; i < 128; i++)
            System.out.println(genRandom(i));
        // for (int i = 0; i < originalStrings.length * .4; i++) { // using forty
        // percent to add things the the hash table
        // // with the key but add linear probing first
        // String temp = originalStrings[i];
        // fortypercent(temp, "Linear", i);
        // }
        // for (int i = 0; i < realHashTable.length; i++) { // prints out my hash table
        // for 40% full
        // System.out.println(realHashTable[i] + " " + i);
        // }
        // for (int i = 0; i < originalStrings.length * .4; i++) { // using forty
        // percent to add things the the hash table
        // with the key but add linear probing first
        // String temp = originalStrings[i];
        // fortypercent(temp, "Linear", i);
        // }
        // for (int i = 0; i < realHashTable.length; i++) { // prints out my hash table
        // for 40% full
        // System.out.println(realHashTable[i] + " " + i);
        // }
        //
        // for (int i = 0; i < probes.length; i++) { // prints out probes
        // System.out.println(probes[i]);
        // }

        // hash fortey = new hash(127, "burris", "test"); //makes a new hash package
        // with these values half function so i can use non static function
    } // could always do with user inputs

    /**
     * public void getString(int index){ //just prints out my strings for a certain
     * index
     * System.out.println(originalStrings[index]);
     * }
     * hash(int size, String method, String LinorRan){ //lets me choose which new to
     * use when i make it
     * if(method.equalsIgnoreCase("burris")){
     * getString(8);
     * }
     */

    public static int burisHash(String word) { // gives me my burris hash keys
        int key;
        double power = Math.pow(2, 16);
        key = Math.abs(
                (word.charAt(4) * 256 + word.charAt(1) + 317 + word.charAt(8)) / (int) power + word.charAt(5) - 10);
        return key;
    }

    public static void fortypercent(String word, String linOrRand, int i) {

        int key = burisHash(word);
        int numberOfProbes = 1;
        if (linOrRand == "Linear") {
            while (realHashTable[key] != null) {
                key = key + 1;
                numberOfProbes = numberOfProbes + 1;
                if (key == 128) {
                    key = 0;
                }
            }
            // System.out.println(i + " break " + numberOfProbes);
            probes[i] = numberOfProbes;
            realHashTable[key] = word;
            if (i > 50) {
                firstminMaxAvgProbes(probes, 25);
                firstminMaxAvgProbes(probes, 51);
            }
        } else if (linOrRand == "Random") { // make the random probing here probing for this
            int temp = key;
            while (realHashTable[temp] != null) {
                temp = key + genRandom(numberOfProbes);
                if (temp > 128) {
                    temp = temp % 128;
                }
                numberOfProbes = numberOfProbes + 1;
                if (numberOfProbes > 127) {
                    System.out.println("Error hash table full");
                    break;
                }
            }
            probes[i] = numberOfProbes;
            realHashTable[temp] = word;
            if (i > 50) {
                firstminMaxAvgProbes(probes, 25);
                firstminMaxAvgProbes(probes, 51);
            }
        }
    }

    public static void firstminMaxAvgProbes(int[] array, int end) { // fix this so that it gets the correct
                                                                    // values for probes
        int max = -1, min = 800000000; // start and end variable to that we knwo where to start and stop
        int sum = 0;
        double average = 0;
        for (int i = end - 25; i < end; i++) {
            sum = sum + array[i];
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        average = sum / 25.0;
        System.out.println();
        System.out.println("Minimum probes is: " + min);
        System.out.println("Maximum probes is: " + max);
        System.out.println("Average probes is: " + average);
    }

    public static int genRandom(int t) {
        int temp = (int) Math.pow(2, 9);
        int r = 1;
        int q = 0;
        int p = 0;
        while (p != t) {
            r = r * 5;
            r = r % temp;
            q = r / 4;
            p++;
        }
        return q;
    }

}

// make a function for a display
// function for average min and max probes