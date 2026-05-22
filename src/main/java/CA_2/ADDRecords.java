// This code belongs to the package (folder) called CA_2
package CA_2;

// Import tools we need to use
import java.util.ArrayList; // Used to create a resizable list
import java.util.List;      // Used as the type for our list
import java.util.Scanner;   // Used to read input from the user
import CA_2.Manager.ManagerType;       // Import the ManagerType enum from the Manager class
import CA_2.Department.DepartmentType; // Import the DepartmentType enum from the Department class

// This class handles adding new employees to the system
public class ADDRecords {

    // ================================================================
    //  Keeps track of all records added during this session
    // ================================================================

    // A list that stores only the employees added in the current session
    private static List<Employee> newlyAdded = new ArrayList<>();

    // This method handles the full process of adding a new employee
    public static void addNewEmployee(List<Employee> employees, Scanner sc) {

        // Print a header to the screen
        System.out.println("\n========= Add New Employee =========");

        // ----------------------------------------------------------------
        // STEP 1: Get the employee name
        // ----------------------------------------------------------------

        // Ask the user to type the employee's name
        System.out.print("Please input the Employee Name: ");

        // Read the name and remove any extra spaces at the start or end
        String name = sc.nextLine().trim();

        // Keep asking until the user types something (name cannot be blank)
        while (name.isEmpty()) {
            System.out.println("[ERROR] Name cannot be empty. Please try again."); // Show error
            System.out.print("Please input the Employee Name: ");                  // Ask again
            name = sc.nextLine().trim();                                            // Read again
        }

        // ----------------------------------------------------------------
        // STEP 2: Choose Manager Type (with validation)
        // ----------------------------------------------------------------

        // Show the list of available manager types to the user
        Manager.displayManagerTypes();

        ManagerType managerType = null; // Start with no manager type selected

        // Keep asking until the user picks a valid manager type
        while (managerType == null) {
            System.out.print("\nEnter your choice: "); // Ask the user to type a number

            if (sc.hasNextInt()) {                         // Check if the input is a number
                int choice = sc.nextInt();                 // Read the number
                sc.nextLine();                             // Clear the leftover newline from input
                managerType = ManagerType.fromInt(choice); // Find the matching manager type
            } else {
                sc.nextLine(); // Clear the invalid input so we can try again
            }

            // If no match was found, show an error
            if (managerType == null) {
                System.out.println("[ERROR] Invalid choice! Please select a valid Manager Type.");
            }
        }

        // ----------------------------------------------------------------
        // STEP 3: Choose Department (with validation)
        // ----------------------------------------------------------------

        // Show the list of available departments to the user
        Department.displayDepartmentTypes();

        DepartmentType departmentType = null; // Start with no department selected

        // Keep asking until the user picks a valid department
        while (departmentType == null) {
            System.out.print("\nEnter your choice: "); // Ask the user to type a number

            if (sc.hasNextInt()) {                               // Check if the input is a number
                int choice = sc.nextInt();                       // Read the number
                sc.nextLine();                                   // Clear the leftover newline from input
                departmentType = DepartmentType.fromInt(choice); // Find the matching department
            } else {
                sc.nextLine(); // Clear the invalid input so we can try again
            }

            // If no match was found, show an error
            if (departmentType == null) {
                System.out.println("[ERROR] Invalid choice! Please select a valid Department.");
            }
        }

        // ----------------------------------------------------------------
        // STEP 4: Create new Employee and add to list
        // ----------------------------------------------------------------

        // Create a new Employee object using the information collected above
        // Fields like email, gender and salary are set to default values
        Employee newEmployee = new Employee(
            getFirstName(name),          // Extract the first name from the full name
            getLastName(name),           // Extract the last name from the full name
            "N/A",                       // Gender is not collected, so set to "N/A"
            "N/A",                       // Email is not collected, so set to "N/A"
            0.0,                         // Salary is not collected, so set to 0
            departmentType.getLabel(),   // Use the department the user chose
            managerType.getLabel(),      // Use the manager type as the position
            managerType.getLabel(),      // Use the manager type as the job title too
            "Bank"                       // All employees belong to "Bank"
        );

        // Add the new employee to the main employee list
        employees.add(newEmployee);

        // Also add the new employee to the session list (newly added)
        newlyAdded.add(newEmployee);

        // ----------------------------------------------------------------
        // STEP 5: Confirm and display all newly added records
        // ----------------------------------------------------------------

        // Print a success message showing what was added
        System.out.println("\n\"" + name + "\" has been added as \""
                + managerType.getLabel() + "\" to \""
                + departmentType.getLabel() + "\" successfully!");

        // Show all records that were added in this session
        displayNewlyAdded();
    }

    // This method prints all employees added during the current session
    public static void displayNewlyAdded() {

        // If no employees have been added yet, print a message and stop
        if (newlyAdded.isEmpty()) {
            System.out.println("\nNo new records added yet.");
            return; // Exit the method early
        }

        // Print a header for the table
        System.out.println("\n========= Newly Added Records =========");

        // Print the column titles with fixed widths so they line up neatly
        System.out.printf("%-4s %-22s %-20s %-15s%n",
                "#", "Full Name", "Manager Type", "Department");

        // Print a divider line made of 65 dashes
        System.out.println("-".repeat(65));

        // Loop through all newly added employees and print each one
        for (int i = 0; i < newlyAdded.size(); i++) {
            Employee e = newlyAdded.get(i); // Get the employee at position i

            // Print the row number, full name, position and department
            System.out.printf("%-4d %-22s %-20s %-15s%n",
                    (i + 1),           // Row number (starts at 1)
                    e.getFullName(),   // Employee's full name
                    e.getPosition(),   // Employee's position (manager type)
                    e.getDepartment()); // Employee's department
        }

        // Print a closing divider line
        System.out.println("-".repeat(65));

        // Print the total count of newly added employees
        System.out.println("Total new records added: " + newlyAdded.size());
    }

    // ================================================================
    //  HELPER METHODS
    //  Split full name into first and last name
    // ================================================================

    // Returns the first word of the full name as the first name
    private static String getFirstName(String fullName) {
        String[] parts = fullName.trim().split(" "); // Split the name by spaces into an array
        return parts[0]; // Return the first word
    }

    // Returns the last word of the full name as the last name
    private static String getLastName(String fullName) {
        String[] parts = fullName.trim().split(" "); // Split the name by spaces into an array

        if (parts.length > 1) {
            return parts[parts.length - 1]; // Return the last word if there is more than one word
        }
        return parts[0]; // If only one word was given, use it as both first and last name
    }
}