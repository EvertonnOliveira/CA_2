/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// This code belongs to the package (folder) called CA_2
package CA_2;

// Import tools we need to use
import java.io.IOException;   // Used to handle file reading errors
import java.util.List;        // Used to store a list of employees
import java.util.Scanner;     // Used to read input from the user

// This is the main class of the program
public class Main {

    // ================================================================
    //  MAIN MENU ENUM
    // ================================================================

    // An enum is a fixed list of options the user can choose from
    // This enum holds the options for the main menu
    public enum MenuOptionEnum {
        SORT(1, "Sort Employee List"),          // Option 1: sort the list
        SEARCH(2, "Search Employee"),           // Option 2: search for an employee
        ADD_RECORD(3, "Add New Employee"),      // Option 3: add a new employee
        BINARY_TREE(4, "Create Employee Hierarchy (Binary Tree)"), // Option 4: build a binary tree
        EXIT(5, "Exit");                        // Option 5: close the program

        private final int option;    // Stores the number of this option
        private final String label;  // Stores the text description of this option

        // Constructor: sets the number and text when the enum is created
        MenuOptionEnum(int option, String label) {
            this.option = option;
            this.label = label;
        }

        // Returns the number of this option
        public int getOption() { return option; }

        // Returns the text of this option
        public String getLabel() { return label; }

        // Finds and returns the enum that matches a given number
        // Returns null if no match is found
        public static MenuOptionEnum fromInt(int value) {
            for (MenuOptionEnum opt : values()) {        // Loop through all options
                if (opt.getOption() == value) return opt; // If the number matches, return it
            }
            return null; // No match found
        }
    }

    // ================================================================
    //  SORT SUB-MENU ENUM
    // ================================================================

    // This enum holds the options for the sort sub-menu
    public enum SortOptionEnum {
        SELECTION_SORT(1, "Selection Sort"), // Option 1: use selection sort
        QUICK_SORT(2, "Quick Sort"),         // Option 2: use quick sort
        BACK(3, "Back to Main Menu");        // Option 3: go back to main menu

        private final int option;    // Stores the number of this option
        private final String label;  // Stores the text description of this option

        // Constructor: sets the number and text when the enum is created
        SortOptionEnum(int option, String label) {
            this.option = option;
            this.label = label;
        }

        // Returns the number of this option
        public int getOption() { return option; }

        // Returns the text of this option
        public String getLabel() { return label; }

        // Finds and returns the enum that matches a given number
        // Returns null if no match is found
        public static SortOptionEnum fromInt(int value) {
            for (SortOptionEnum opt : values()) {         // Loop through all options
                if (opt.getOption() == value) return opt; // If the number matches, return it
            }
            return null; // No match found
        }
    }

    // ================================================================
    //  SEARCH SUB-MENU ENUM
    // ================================================================

    // This enum holds the options for the search sub-menu
    public enum SearchOptionEnum {
        BINARY_SEARCH(1, "Binary Search"), // Option 1: use binary search
        LINEAR_SEARCH(2, "Linear Search"), // Option 2: use linear search
        BACK(3, "Back to Main Menu");      // Option 3: go back to main menu

        private final int option;    // Stores the number of this option
        private final String label;  // Stores the text description of this option

        // Constructor: sets the number and text when the enum is created
        SearchOptionEnum(int option, String label) {
            this.option = option;
            this.label = label;
        }

        // Returns the number of this option
        public int getOption() { return option; }

        // Returns the text of this option
        public String getLabel() { return label; }

        // Finds and returns the enum that matches a given number
        // Returns null if no match is found
        public static SearchOptionEnum fromInt(int value) {
            for (SearchOptionEnum opt : values()) {       // Loop through all options
                if (opt.getOption() == value) return opt; // If the number matches, return it
            }
            return null; // No match found
        }
    }

    // ================================================================
    //  MAIN METHOD
    // ================================================================

    // This is where the program starts running
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Create a scanner to read what the user types
        List<Employee> employees = null;     // Create an empty list to hold employee data

        // --- Load file once at startup ---

        // Print a welcome header to the screen
        System.out.println("\n=============================");
        System.out.println("  Bank Organisation System");
        System.out.println("=============================");

        // Set the name of the file we want to read
        String filePath = "Applicants_Form - Sample data file for read.txt";

        // Tell the user which file is being loaded
        System.out.println("Loading data from: " + filePath);

        try {
            // Try to read the file and store the employee records in the list
            employees = ApplicantsFileReader.readFile(filePath);

            // Tell the user how many records were loaded successfully
            System.out.println("File read successfully! " + employees.size() + " records loaded.");

        } catch (IOException e) {
            // If the file could not be read, print the error message
            System.out.println("[ERROR] Could not read file: " + e.getMessage());

            sc.close(); // Close the scanner before stopping
            return;     // Stop the program
        }

        // --- Main menu loop ---
        // Keep showing the menu until the user chooses to exit
        while (true) {
            MenuOptionEnum option = null; // Start with no option selected

            // Keep asking until the user gives a valid choice
            while (option == null) {

                // Print the main menu header
                System.out.println("\n=============================");
                System.out.println("   Bank Organisation System  ");
                System.out.println("=============================");
                System.out.println("========= Main Menu =========");

                // Print each option from the enum
                for (MenuOptionEnum opt : MenuOptionEnum.values()) {
                    System.out.println(opt.getOption() + ". " + opt.getLabel());
                }

                // Ask the user to type their choice
                System.out.print("\nEnter your choice: ");

                if (sc.hasNextInt()) {         // Check if the user typed a number
                    int choice = sc.nextInt(); // Read the number
                    sc.nextLine();             // Clear the leftover newline from input
                    option = MenuOptionEnum.fromInt(choice); // Find the matching enum option
                } else {
                    sc.nextLine(); // Clear the invalid input so we can try again
                }

                // If the input did not match any option, show an error
                if (option == null) {
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                }
            }

            // Run the correct action based on the user's choice
            switch (option) {
                case SORT        -> handleSortMenu(sc, employees);             // Go to sort menu
                case SEARCH      -> handleSearchMenu(sc, employees);           // Go to search menu
                case ADD_RECORD  -> ADDRecords.addNewEmployee(employees, sc);  // Add a new employee
                case BINARY_TREE -> BinaryTree.displayHierarchy(employees);    // Show binary tree
                case EXIT        -> {
                    System.out.println("Exiting program. Goodbye!"); // Say goodbye
                    sc.close(); // Close the scanner
                    return;     // Stop the program
                }
            }
        }
    }

    // ================================================================
    //  SORT SUB-MENU
    // ================================================================

    // This method shows the sort menu and runs the chosen sort algorithm
    private static void handleSortMenu(Scanner sc, List<Employee> employees) {

        SortOptionEnum option = null; // Start with no option selected

        // Keep asking until the user gives a valid choice
        while (option == null) {

            // Print the sort menu header and all options
            System.out.println("\n========= Sort Menu =========");
            for (SortOptionEnum opt : SortOptionEnum.values()) {
                System.out.println(opt.getOption() + ". " + opt.getLabel());
            }
            System.out.print("\nEnter your choice: ");

            if (sc.hasNextInt()) {         // Check if the user typed a number
                int choice = sc.nextInt(); // Read the number
                sc.nextLine();             // Clear the leftover newline from input
                option = SortOptionEnum.fromInt(choice); // Find the matching enum option
            } else {
                sc.nextLine(); // Clear the invalid input
            }

            // If the input did not match any option, show an error
            if (option == null) {
                System.out.println("Invalid choice! Please enter 1, 2 or 3.");
            }
        }

        // Run the correct sort based on the user's choice
        switch (option) {
            case SELECTION_SORT -> {
                System.out.println("\nRunning Selection Sort..."); // Tell the user what is running
                Sort.recursiveSelectionSort(employees);            // Sort the list using selection sort
                Sort.displayFirst20(employees);                    // Show the first 20 results
            }
            case QUICK_SORT -> {
                System.out.println("\nRunning Quick Sort..."); // Tell the user what is running
                Sort.recursiveQuickSort(employees);            // Sort the list using quick sort
                Sort.displayFirst20(employees);                // Show the first 20 results
            }
            case BACK -> System.out.println("Returning to main menu..."); // Go back to the main menu
        }
    }

    // ================================================================
    //  SEARCH SUB-MENU
    // ================================================================

    // This method shows the search menu and runs the chosen search algorithm
    private static void handleSearchMenu(Scanner sc, List<Employee> employees) {

        SearchOptionEnum option = null; // Start with no option selected

        // Keep asking until the user gives a valid choice
        while (option == null) {

            // Print the search menu header and all options
            System.out.println("\n========= Search Menu =========");
            for (SearchOptionEnum opt : SearchOptionEnum.values()) {
                System.out.println(opt.getOption() + ". " + opt.getLabel());
            }
            System.out.print("\nEnter your choice: ");

            if (sc.hasNextInt()) {         // Check if the user typed a number
                int choice = sc.nextInt(); // Read the number
                sc.nextLine();             // Clear the leftover newline from input
                option = SearchOptionEnum.fromInt(choice); // Find the matching enum option
            } else {
                sc.nextLine(); // Clear the invalid input
            }

            // If the input did not match any option, show an error
            if (option == null) {
                System.out.println("Invalid choice! Please enter 1, 2 or 3.");
            }
        }

        // Run the correct search based on the user's choice
        switch (option) {
            case BINARY_SEARCH -> {
                // Warn the user that binary search only works on a sorted list
                System.out.println("\n[NOTE] Binary Search requires a sorted list.");

                System.out.print("Enter the employee name to search: "); // Ask for a name
                String name = sc.nextLine();               // Read the name the user typed
                Search.binarySearch(employees, name);      // Run binary search with that name
            }
            case LINEAR_SEARCH -> {
                System.out.print("\nEnter the employee name to search: "); // Ask for a name
                String name = sc.nextLine();               // Read the name the user typed
                Search.linearSearch(employees, name);      // Run linear search with that name
            }
            case BACK -> System.out.println("Returning to main menu..."); // Go back to the main menu
        }
    }
}