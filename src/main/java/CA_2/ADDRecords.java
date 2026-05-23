package CA_2;

import java.util.ArrayList; // Allows us to create a dynamic list
import java.util.List;      // Allows us to use List to store Employee objects
import java.util.Scanner;   // Allows us to read user input from the terminal
import CA_2.Manager.ManagerType;       // Imports the ManagerType enum from Manager class
import CA_2.Department.DepartmentType; // Imports the DepartmentType enum from Department class

/**
 * ADDRecords.java
 * Handles adding new employees to the Bank Organisation System.
 * Validates all inputs before storing the new record in memory.
 */
public class ADDRecords {

    // Stores all employees added during this session
    // Static so it persists across multiple calls to addNewEmployee()
    private static List<Employee> newlyAdded = new ArrayList<>();

    /**
     * PUBLIC entry point — called from Main.java when user selects ADD NEW EMPLOYEE.
     * Asks the user for Name, Manager Type and Department, validates each input,
     * then adds the new employee to the main list and displays confirmation.
     *
     * @param employees the main employee list to add the new record to
     * @param sc        the Scanner from Main (reused to avoid conflicts)
     */
    public static void addNewEmployee(List<Employee> employees, Scanner sc) {

        System.out.println("\n========= Add New Employee =========");

        // ----------------------------------------------------------------
        // STEP 1: Get the employee name — cannot be empty
        // ----------------------------------------------------------------
          // STEP 1: Get the employee name — cannot be empty or numeric
        System.out.print("Please input the Employee Name: ");
        String name = sc.nextLine().trim();

        // Keep asking until the user enters a non-empty name
        while (name.isEmpty() || !isValidName(name)) {
            if (name.isEmpty()) {
                System.out.println("[ERROR] Name cannot be empty. Please try again.");
            } else {
                System.out.println("[ERROR] Invalid name. Please enter a real name (letters only).");
            }
            System.out.print("Please input the Employee Name: ");
            name = sc.nextLine().trim();
        }

        // ----------------------------------------------------------------
        // STEP 2: Choose Manager Type — must be a valid option (1, 2 or 3)
        // ----------------------------------------------------------------
        Manager.displayManagerTypes(); // Shows the list: 1. Senior Manager, 2. Manager, 3. Junior Manager

        ManagerType managerType = null; // Start with null — loop runs until valid option selected

        while (managerType == null) {
            System.out.print("\nEnter your choice: ");

            if (sc.hasNextInt()) {          // Check if user entered a number
                int choice = sc.nextInt();  // Read the number
                sc.nextLine();              // Clear the input buffer after nextInt()
                managerType = ManagerType.fromInt(choice); // Convert number to ManagerType enum
            } else {
                sc.nextLine(); // Discard invalid non-numeric input
            }

            // If fromInt() returned null, the number was not valid
            if (managerType == null) {
                System.out.println("[ERROR] Invalid choice! Please select a valid Manager Type.");
            }
        }

        // ----------------------------------------------------------------
        // STEP 3: Choose Department — must be a valid option (1 to 5)
        // ----------------------------------------------------------------
        Department.displayDepartmentTypes(); // Shows: 1. Finance, 2. HR, 3. IT, 4. Marketing, 5. Sales

        DepartmentType departmentType = null; // Start with null — loop runs until valid option selected

        while (departmentType == null) {
            System.out.print("\nEnter your choice: ");

            if (sc.hasNextInt()) {           // Check if user entered a number
                int choice = sc.nextInt();   // Read the number
                sc.nextLine();               // Clear the input buffer after nextInt()
                departmentType = DepartmentType.fromInt(choice); // Convert number to DepartmentType enum
            } else {
                sc.nextLine(); // Discard invalid non-numeric input
            }

            // If fromInt() returned null, the number was not valid
            if (departmentType == null) {
                System.out.println("[ERROR] Invalid choice! Please select a valid Department.");
            }
        }

        // ----------------------------------------------------------------
        // STEP 4: Create the new Employee object and add to the list
        // ----------------------------------------------------------------
        Employee newEmployee = new Employee(
            getFirstName(name),          // Extract first name from full name
            getLastName(name),           // Extract last name from full name
            "N/A",                       // Gender — not collected in this version
            "N/A",                       // Email — not collected in this version
            0.0,                         // Salary — not collected in this version
            departmentType.getLabel(),   // Department selected by user (e.g. "IT")
            managerType.getLabel(),      // Position/Manager Type selected by user
            managerType.getLabel(),      // Job Title — same as Manager Type for now
            "Bank"                       // Company — fixed as "Bank" for this system
        );

        employees.add(newEmployee);    // Add to the main employee list in memory
        newlyAdded.add(newEmployee);   // Also add to the newly added list for display

        // ----------------------------------------------------------------
        // STEP 5: Display confirmation and show all newly added records
        // ----------------------------------------------------------------
        System.out.println("\n\"" + name + "\" has been added as \""
                + managerType.getLabel() + "\" to \""
                + departmentType.getLabel() + "\" successfully!");

        displayNewlyAdded(); // Show the full list of records added this session
    }

    /**
     * Displays all employee records added during this session in a formatted table.
     * Called automatically after each successful addition.
     */
    public static void displayNewlyAdded() {

        // If no records have been added yet, show a message and return
        if (newlyAdded.isEmpty()) {
            System.out.println("\nNo new records added yet.");
            return;
        }

        // Print table header
        System.out.println("\n========= Newly Added Records =========");
        System.out.printf("%-4s %-22s %-20s %-15s%n",
                "#", "Full Name", "Manager Type", "Department");
        System.out.println("-".repeat(65));

        // Loop through all newly added records and print each one
        for (int i = 0; i < newlyAdded.size(); i++) {
            Employee e = newlyAdded.get(i); // Get employee at position i
            System.out.printf("%-4d %-22s %-20s %-15s%n",
                    (i + 1),              // Row number (starts at 1)
                    e.getFullName(),       // Employee full name
                    e.getPosition(),       // Manager Type
                    e.getDepartment());    // Department
        }

        System.out.println("-".repeat(65));
        System.out.println("Total new records added: " + newlyAdded.size());
    }

    // ----------------------------------------------------------------
    // HELPER METHODS — split full name into first and last name
    // ----------------------------------------------------------------

    /**
     * Extracts the first name from a full name string.
     * Example: "John Joe" returns "John"
     */
    private static String getFirstName(String fullName) {
        String[] parts = fullName.trim().split(" "); // Split by space
        return parts[0]; // Return the first word
    }

    /**
     * Extracts the last name from a full name string.
     * Example: "John Joe" returns "Joe"
     * If only one name is given, returns the same name.
     */
    private static String getLastName(String fullName) {
        String[] parts = fullName.trim().split(" "); // Split by space
        if (parts.length > 1) {
            return parts[parts.length - 1]; // Return the last word
        }
        return parts[0]; // Only one name given — return it as last name too
    }
    
    /**
 * Checks if the name is valid — must contain at least one letter.
 * Prevents numbers or special characters from being accepted as names.
 * Example: "John Joe" → valid | "-1" → invalid | "123" → invalid
 */
    private static boolean isValidName(String name) {
        for (char c : name.toCharArray()) {
            if (Character.isLetter(c)) {
            return true; // Found at least one letter — valid name
            }
        }
        return false; // No letters found — invalid name
    }
}