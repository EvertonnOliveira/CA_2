// Auto-generated file header by NetBeans (ignore these two lines)
// Click the links above to change the license or edit the template

// Declares this class belongs to the "CA_2" package
package CA_2;

// Imports everything needed to read files (BufferedReader, FileReader, IOException)
import java.io.*;
// Imports everything needed to use lists (List, ArrayList)
import java.util.*;

// Defines a public class responsible for reading employee data from a file
public class ApplicantsFileReader {

    // A static method that reads a CSV file and returns a list of Employee objects
    // "throws IOException" means it will pass any file error up to whoever called this method
    public static List<Employee> readFile(String path) throws IOException {

        // Creates an empty list that will hold all the employees read from the file
        List<Employee> list = new ArrayList<>();

        // Opens the file at the given path for reading — automatically closes it when done
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;       // Will hold the text of each line as we read it
            int lineNumber = 0; // Tracks which line number we are currently on

            // Reads one line at a time until there are no more lines (null = end of file)
            while ((line = br.readLine()) != null) {

                lineNumber++; // Increases the line counter by 1 for each line read

                // Skips the first line (header row) and any blank lines — moves to the next line
                if (lineNumber == 1 || line.trim().isEmpty()) continue;

                // Splits the line into parts using comma as the separator
                // e.g. "John,Doe,..." becomes ["John", "Doe", ...]
                String[] p = line.split(",");

                // If the line doesn't have exactly 9 columns, it's invalid — skip it
                if (p.length != 9) continue;

                // Tries to create an Employee object from the 9 columns
                try {
                    list.add(new Employee(
                        p[0].trim(), // Column 0: first name (removes extra spaces)
                        p[1].trim(), // Column 1: last name
                        p[2].trim(), // Column 2: (e.g. ID or date of birth)
                        p[3].trim(), // Column 3: (e.g. position or department)
                        Double.parseDouble(p[4].trim()), // Column 4: salary — converts text to a decimal number
                        p[5].trim(), // Column 5: (e.g. another field)
                        p[6].trim(), // Column 6:
                        p[7].trim(), // Column 7:
                        p[8].trim()  // Column 8: last column
                    ));

                // If the salary text can't be converted to a number, catch the error
                } catch (NumberFormatException e) {
                    // Prints a warning message showing which line had the bad salary value
                    System.out.println("[WARNING] Invalid salary on line " + lineNumber);
                }
            }
        } // File is automatically closed here

        // Returns the completed list of Employee objects to whoever called this method
        return list;
    }
}