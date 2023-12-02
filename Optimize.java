// Optimize Class
// Luke Reinbach
// 10/05/2023

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

public class Optimize {
    
    public static void main (String[] args) throws IOException {
       int a;
       int b;
       int c;
       int d;
       int e;
       int f;
       int g;
       int h;
       int i;
       int min = Integer.MAX_VALUE;
       int combo = 0;
        
        // Read in file of student numbers
        for (a = 0; a <= 4; a++) {
            for (b = 0; b <= 4; b++) {
                for (c = 0; c <= 4; c++) {
                    for (d = 0; d <= 4; d++) {
                        for (e = 0; e <= 4; e++) {
                            for (f = 0; f <= 4; f++) {
                                for (g = 0; g <= 4; g++) {
                                    for (h = 0; h <= 4; h++) {
                                        for (i = 0; i <= 4; i++) {
                                            FileReader data = new FileReader ("mydata.txt");
                                            BufferedReader br = new BufferedReader(data);

                                            final HashTable hashTable = new LPHashTable(37);
                                            final int[] weights = {a, b, c, d, e, f, g, h, i};
                                            hashTable.setWeights(weights);
                                            hashTable.resetProbeCount();

                                            String line = br.readLine();
                                            while (line != null) {
                                                hashTable.insert(line);
                                                line = br.readLine();
                                            }
                                            int pc = hashTable.getProbeCount();

                                            if (pc < min) {
                                                min = pc;
                                                combo = 1;
                                            }
                                            else if (pc == min) {
                                                combo++;
                                            }
                                            br.close();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        FileWriter file = new FileWriter("results.txt");
        file.write(min + " " + combo);
        file.close();
    }
}
