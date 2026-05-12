 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    // ================================================================
    //  MAIN MENU ENUM
    // ================================================================
    public enum MenuOptionEnum {
        SORT(1, "Sort Employee List"),
        SEARCH(2, "Search Employee"),
        ADD_RECORD(3, "Add New Employee"),
        BINARY_TREE(4, "Create Employee Hierarchy (Binary Tree)"),
        EXIT(5, "Exit");

        private final int option;
        private final String label;

        MenuOptionEnum(int option, String label) {
            this.option = option;
            this.label = label;
        }

        public int getOption() { return option; }
        public String getLabel() { return label; }

        public static MenuOptionEnum fromInt(int value) {
            for (MenuOptionEnum opt : values()) {
                if (opt.getOption() == value) return opt;
            }
            return null;
        }
    }

    // ================================================================
    //  SORT SUB-MENU ENUM
    // ================================================================
    public enum SortOptionEnum {
        SELECTION_SORT(1, "Selection Sort"),
        QUICK_SORT(2, "Quick Sort"),
        BACK(3, "Back to Main Menu");

        private final int option;
        private final String label;

        SortOptionEnum(int option, String label) {
            this.option = option;
            this.label = label;
        }

        public int getOption() { return option; }
        public String getLabel() { return label; }

        public static SortOptionEnum fromInt(int value) {
            for (SortOptionEnum opt : values()) {
                if (opt.getOption() == value) return opt;
            }
            return null;
        }
    }

    // ================================================================
    //  SEARCH SUB-MENU ENUM
    // ================================================================
    public enum SearchOptionEnum {
        BINARY_SEARCH(1, "Binary Search"),
        LINEAR_SEARCH(2, "Linear Search"),
        BACK(3, "Back to Main Menu");

        private final int option;
        private final String label;

        SearchOptionEnum(int option, String label) {
            this.option = option;
            this.label = label;
        }

        public int getOption() { return option; }
        public String getLabel() { return label; }

        public static SearchOptionEnum fromInt(int value) {
            for (SearchOptionEnum opt : values()) {
                if (opt.getOption() == value) return opt;
            }
            return null;
        }
    }

    // ================================================================
    //  MAIN METHOD
    // ================================================================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Employee> employees = null;

        // --- Load file once at startup ---
        System.out.println("\n=============================");
        System.out.println("  Bank Organisation System");
        System.out.println("=============================");
        System.out.print("\nPlease enter the filename to read: ");
        String filePath = sc.nextLine().trim();

        try {
            employees = ApplicantsFileReader.readFile(filePath);
            System.out.println("File read successfully! " + employees.size() + " records loaded.");
        } catch (IOException e) {
            System.out.println("[ERROR] Could not read file: " + e.getMessage());
            sc.close();
            return;
        }

        // --- Main menu loop ---
        while (true) {
            MenuOptionEnum option = null;

            while (option == null) {
                System.out.println("\n=============================");
                System.out.println("   Bank Organisation System  ");
                System.out.println("=============================");
                System.out.println("========= Main Menu =========");

                for (MenuOptionEnum opt : MenuOptionEnum.values()) {
                    System.out.println(opt.getOption() + ". " + opt.getLabel());
                }

                System.out.print("\nEnter your choice: ");

                if (sc.hasNextInt()) {
                    int choice = sc.nextInt();
                    sc.nextLine();
                    option = MenuOptionEnum.fromInt(choice);
                } else {
                    sc.nextLine();
                }

                if (option == null) {
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                }
            }

            switch (option) {
                case SORT -> handleSortMenu(sc, employees);
                case SEARCH -> handleSearchMenu(sc, employees);
                case ADD_RECORD -> System.out.println("[ADD RECORD - coming soon]");
                case BINARY_TREE -> System.out.println("[BINARY TREE - coming soon]");
                case EXIT -> {
                    System.out.println("Exiting program. Goodbye!");
                    sc.close();
                    return;
                }
            }
        }
    }

    // ================================================================
    //  SORT SUB-MENU
    // ================================================================
    private static void handleSortMenu(Scanner sc, List<Employee> employees) {

        SortOptionEnum option = null;

        while (option == null) {
            System.out.println("\n========= Sort Menu =========");
            for (SortOptionEnum opt : SortOptionEnum.values()) {
                System.out.println(opt.getOption() + ". " + opt.getLabel());
            }
            System.out.print("\nEnter your choice: ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                option = SortOptionEnum.fromInt(choice);
            } else {
                sc.nextLine();
            }

            if (option == null) {
                System.out.println("Invalid choice! Please enter 1, 2 or 3.");
            }
        }

        switch (option) {
            case SELECTION_SORT -> {
                System.out.println("\nRunning Selection Sort...");
                SortingAlgorithm.recursiveSelectionSort(employees);
                SortingAlgorithm.displayFirst20(employees);
            }
            case QUICK_SORT -> {
                System.out.println("\nRunning Quick Sort...");
                SortingAlgorithm.recursiveQuickSort(employees);
                SortingAlgorithm.displayFirst20(employees);
            }
            case BACK -> System.out.println("Returning to main menu...");
        }
    }

    // ================================================================
    //  SEARCH SUB-MENU
    // ================================================================
    private static void handleSearchMenu(Scanner sc, List<Employee> employees) {

        SearchOptionEnum option = null;

        while (option == null) {
            System.out.println("\n========= Search Menu =========");
            for (SearchOptionEnum opt : SearchOptionEnum.values()) {
                System.out.println(opt.getOption() + ". " + opt.getLabel());
            }
            System.out.print("\nEnter your choice: ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                option = SearchOptionEnum.fromInt(choice);
            } else {
                sc.nextLine();
            }

            if (option == null) {
                System.out.println("Invalid choice! Please enter 1, 2 or 3.");
            }
        }

        switch (option) {
            case BINARY_SEARCH -> {
                System.out.println("\n[NOTE] Binary Search requires a sorted list.");
                System.out.print("Enter the employee name to search: ");
                String name = sc.nextLine();
                SearchAlgorithm.search(employees, name);
            }
            case LINEAR_SEARCH -> {
                System.out.print("\nEnter the employee name to search: ");
                String name = sc.nextLine();
                // LinearSearch.search(employees, name); -- coming soon
                System.out.println("[LINEAR SEARCH - coming soon]");
            }
            case BACK -> System.out.println("Returning to main menu...");
        }
    }
}



