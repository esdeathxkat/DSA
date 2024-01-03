import java.util.*;
import java.io.*;

public class Hash {
    private String[] hash = new String[128];
    private double loadFactor;
    private String probeMethod;
    private String hashMethod;

    public Hash(double load, String probe, String hash) {
        this.loadFactor = load;
        this.probeMethod = probe;
        this.hashMethod = hash;
    }

    public void hash() {
        if (hashMethod.equals("Burris")) {
            if (probeMethod.equals("Linear")) {
                try {
                    finalHash(loadFactor, hashMethod, probeMethod);
                } catch (FileNotFoundException e) {
                    System.out.println("test");
                }
            } else if (probeMethod.equals("Random")) {
                try {
                    finalHash(loadFactor, hashMethod, probeMethod);
                } catch (FileNotFoundException e) {
                    System.out.println("test");
                }
            } else {
                System.out.println("Error wrong selection");
            }
        } else if (hashMethod.equals("MyHash")) {
            if (probeMethod.equals("Linear")) {
                try {
                    finalHash(loadFactor, hashMethod, probeMethod);
                } catch (FileNotFoundException e) {
                    System.out.println("test");
                }
            } else if (probeMethod.equals("Random")) {
                try {
                    finalHash(loadFactor, hashMethod, probeMethod);
                } catch (FileNotFoundException e) {
                    System.out.println("test");
                }
            } else {
                System.out.println("Error wrong selection");
            }
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
        int key = 0;

        if (word.length() >= 10) {
            int slice1 = slice(word, 0, 1);
            int slice2 = slice(word, 2, 3);
            int slice3 = word.charAt(4);
            int slice4 = word.charAt(5);
            int slice5 = word.charAt(6);
            int slice6 = word.charAt(9);

            double power = Math.pow(2, 10);

            key = (int) (((slice1 / 256.0 + slice2 / 277.0 + slice3) / power +
                    slice4 / 313.0 + slice5 / 3.0 + slice6) % 128);
        }
        return key;
    }

    public int myHash(String word) {
        int key = 0;
        long prime = 12582917;
        for (int i = 0; i < word.length(); i++) {
            key = (key * (int) prime + word.charAt(i)) % 128;
        }
        return key;
    }

    public void averageWhole(int[] array) {
        double average = 0;
        double sum = 0;
        int temp = 0;
        for (int i = 0; i < 127; i++) {
            sum = sum + array[i];
            if (array[i] != 0) {
                temp++;
            }
        }
        average = sum / temp;
        System.out.println(average);
    }

    public void firstminMaxAvgProbes(int[] array, int end) {
        int max = -1, min = 800000000;
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

    public int genRandom(int t) {
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

    public void display(int[] array) {
        String str;
        int key = 0;
        int probes;
        System.out.println("Index                  " + "    Word                " + " Probes"
                + "                  Original Hash");
        for (int i = 0; i < 128; i++) {
            probes = array[i];
            if (hash[i] == null) {
                str = "empty           ";
                key = -1;
            } else {
                str = hash[i];
                if (hashMethod.equals("Burris")) {
                    key = BurrisPoorlyMadeHashFunction(str);
                } else if (hashMethod.equals("MyHash")) {
                    key = myHash(str);
                }
            }
            System.out.println(String.format("%-15s", String.valueOf(i)) + "|        " + String.format("%-15s", str)
                    + " |       " + String.format("%-15s", String.valueOf(probes))
                    + "|            " + String.format("%-15s", String.valueOf(key)));
        }
    }
    

    public void finalHash(double loadFact, String hashMethod, String probeMethod) throws FileNotFoundException {
        int[] probes = new int[128];
        int[] realprobes = new int[128];
        double end = loadFact * 128;
        System.out.println("\nfinalHash" + " " + hashMethod + " " + probeMethod);
        File file = new File("c:/Users/Esdeath/Desktop/Hash/Words200D16.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            for (int i = 0; i <= 127 * loadFact; i++) {
                String line = scanner.nextLine();
                int maxprobes = 1;
                if (hashMethod.equals("Burris")) {
                    int key = BurrisPoorlyMadeHashFunction(line);
                    if (probeMethod.equals("Linear")) {
                        while (hash[key] != null) {
                            key = key + 1;
                            maxprobes = maxprobes + 1;
                            if (key == 128) {
                                key = 0;
                            }
                        }
                        realprobes[key] = maxprobes;
                        probes[i] = maxprobes;
                        hash[key] = line;
                    } else if (probeMethod.equals("Random")) {
                        int temp = key;
                        while (hash[temp] != null) {
                            temp = (genRandom(maxprobes) + key) % 128;
                            maxprobes = maxprobes + 1;
                        }
                        realprobes[temp] = maxprobes;
                        probes[i] = maxprobes;
                        hash[temp] = line;
                    }
                } else if (hashMethod.equals("MyHash")) {
                    int key = myHash(line);
                    if (probeMethod.equals("Linear")) {
                        while (hash[key] != null) {
                            key = key + 1;
                            maxprobes = maxprobes + 1;
                            if (key == 128) {
                                key = 0;
                            }
                        }
                        realprobes[key] = maxprobes;
                        probes[i] = maxprobes;
                        hash[key] = line;
                    } else if (probeMethod.equals("Random")) {
                        int temp = key;
                        while (hash[temp] != null) {
                            temp = (genRandom(maxprobes) + key) % 128;
                            maxprobes = maxprobes + 1;
                        }
                        realprobes[temp] = maxprobes;
                        probes[i] = maxprobes;
                        hash[temp] = line;
                    }
                }
            }
            display(realprobes);
            System.out.print("\n\nFirst 25 keys:");
            firstminMaxAvgProbes(probes, 25);
            System.out.print("\n\nLast 25 keys:");
            firstminMaxAvgProbes(probes, (int) end);
            System.out.print("\n\nTotal Average:");
            averageWhole(probes);
            System.out.println("\n\nTheoretical Probes:");
            theoretical();
        }
        scanner.close();
    }

    public void theoretical() {
        double theo = 1 - (loadFactor / 2);
        theo = theo / (1 - loadFactor);
        System.out.println("Theoretical Probes: " + theo);
    }

    public static void main(String[] args) {
        //Hash Mylin86 = new Hash(.86, "Linear", "MyHash");
        Hash Myrand86 = new Hash(.86, "Random", "MyHash");
        //Hash Brand86 = new Hash(.86, "Random", "Burris");
        //Hash Blin86 = new Hash(.86, "Linear", "Burris");
        //Blin86.hash();
        //Brand86.hash();
        //Mylin86.hash();
        Myrand86.hash();
    }
}
