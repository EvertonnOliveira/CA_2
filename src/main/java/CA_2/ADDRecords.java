/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import CA_2.Manager.ManagerType;
import CA_2.Department.DepartmentType;

public class ADDRecords {
    

    // ================================================================
    //  Keeps track of all records added during this session
    // ================================================================
    private static List<Employee> newlyAdded = new ArrayList<>();

    /**
     * PUBLIC entry point - called from Main when user picks ADD RECORD.
     *
     * @param employees the main employee list to add to
     * @param sc        the Scanner from Main (reused to avoid conflicts)
     */
    public static void addNewEmployee(List<Employee> employees, Scanner sc) {

        System.out.println("\n========= Add New Employee =========");

        // ----------------------------------------------------------------
        // STEP 1: Get the employee name
        // ----------------------------------------------------------------
        System.out.print("Please input the Employee Name: ");
        String name = sc.nextLine().trim();

        // Validate name is not empty
        while (name.isEmpty()) {
            System.out.println("[ERROR] Name cannot be empty. Please try again.");
            System.out.print("Please input the Employee Name: ");
            name = sc.nextLine().trim();
        }

        // ----------------------------------------------------------------
        // STEP 2: Choose Manager Type (with validation)
        // ----------------------------------------------------------------
        Manager.displayManagerTypes();

        ManagerType managerType = null;
        while (managerType == null) {
            System.out.print("\nEnter your choice: ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine(); // clear buffer
                managerType = ManagerType.fromInt(choice);
            } else {
                sc.nextLine(); // discard invalid input
            }

            if (managerType == null) {
                System.out.println("[ERROR] Invalid choice! Please select a valid Manager Type.");
            }
        }

        // ----------------------------------------------------------------
        // STEP 3: Choose Department (with validation)
        // ----------------------------------------------------------------
        Department.displayDepartmentTypes();

        DepartmentType departmentType = null;
        while (departmentType == null) {
            System.out.print("\nEnter your choice: ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine(); // clear buffer
                departmentType = DepartmentType.fromInt(choice);
            } else {
                sc.nextLine(); // discard invalid input
            }

            if (departmentType == null) {
                System.out.println("[ERROR] Invalid choice! Please select a valid Department.");
            }
        }

        // ----------------------------------------------------------------
        // STEP 4: Create new Employee and add to list
        // ----------------------------------------------------------------
        // Creates employee with the required fields
        // Other fields (email, salary, etc.) are set to defaults
        Employee newEmployee = new Employee(
            getFirstName(name),          // first name
            getLastName(name),           // last name
            "N/A",                       // gender
            "N/A",                       // email
            0.0,                         // salary
            departmentType.getLabel(),   // department
            managerType.getLabel(),      // position (manager type)
            managerType.getLabel(),      // job title
            "Bank"                       // company
        );

        // Add to main list
        employees.add(newEmployee);

        // Add to newly added list for display
        newlyAdded.add(newEmployee);

        // ----------------------------------------------------------------
        // STEP 5: Confirm and display all newly added records
        // ----------------------------------------------------------------
        System.out.println("\n\"" + name + "\" has been added as \""
                + managerType.getLabel() + "\" to \""
                + departmentType.getLabel() + "\" successfully!");

        displayNewlyAdded();
    }

    /**
     * Displays all records added during this session.
     */
    public static void displayNewlyAdded() {
        if (newlyAdded.isEmpty()) {
            System.out.println("\nNo new records added yet.");
            return;
        }

        System.out.println("\n========= Newly Added Records =========");
        System.out.printf("%-4s %-22s %-20s %-15s%n",
                "#", "Full Name", "Manager Type", "Department");
        System.out.println("-".repeat(65));

        for (int i = 0; i < newlyAdded.size(); i++) {
            Employee e = newlyAdded.get(i);
            System.out.printf("%-4d %-22s %-20s %-15s%n",
                    (i + 1),
                    e.getFullName(),
                    e.getPosition(),
                    e.getDepartment());
        }

        System.out.println("-".repeat(65));
        System.out.println("Total new records added: " + newlyAdded.size());
    }

    // ================================================================
    //  HELPER METHODS
    //  Split full name into first and last name
    // ================================================================

    /**
     * Extracts the first name from a full name string.
     * Example: "John Joe" → "John"
     */
    private static String getFirstName(String fullName) {
        String[] parts = fullName.trim().split(" ");
        return parts[0];
    }

    /**
     * Extracts the last name from a full name string.
     * Example: "John Joe" → "Joe"
     * If only one name given, returns same name.
     */
    private static String getLastName(String fullName) {
        String[] parts = fullName.trim().split(" ");
        if (parts.length > 1) {
            return parts[parts.length - 1];
        }
        return parts[0]; // only one name given
    }
}

    

