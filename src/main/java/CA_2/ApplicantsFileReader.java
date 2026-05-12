/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.io.*;
import java.util.*;

public class ApplicantsFileReader {

    public static List<Employee> readFile(String path) throws IOException {
        List<Employee> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                // Skip header line and blank lines
                if (lineNumber == 1 || line.trim().isEmpty()) continue;

                String[] p = line.split(",");
                if (p.length != 9) continue;

                try {
                    list.add(new Employee(
                        p[0].trim(), p[1].trim(), p[2].trim(), p[3].trim(),
                        Double.parseDouble(p[4].trim()),
                        p[5].trim(), p[6].trim(), p[7].trim(), p[8].trim()
                    ));
                } catch (NumberFormatException e) {
                    System.out.println("[WARNING] Invalid salary on line " + lineNumber);
                }
            }
        }
        return list;
    }
}