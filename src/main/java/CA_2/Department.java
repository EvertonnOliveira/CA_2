/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

public class Department {

    // ================================================================
    //  ENUM — defines the 5 valid Department types
    //  Used for validation in Add Record feature
    // ================================================================
    public enum DepartmentType {
        FINANCE(1, "Finance"),
        HR(2, "HR"),
        IT(3, "IT"),
        MARKETING(4, "Marketing"),
        SALES(5, "Sales");

        private final int option;
        private final String label;

        DepartmentType(int option, String label) {
            this.option = option;
            this.label = label;
        }

        public int getOption()   { return option; }
        public String getLabel() { return label; }

        /**
         * Converts a menu number to a DepartmentType.
         * Returns null if the number is invalid.
         * Used for input validation.
         */
        public static DepartmentType fromInt(int value) {
            for (DepartmentType dept : values()) {
                if (dept.getOption() == value) return dept;
            }
            return null; // invalid choice
        }

        /**
         * Converts a String label to a DepartmentType.
         * Case-insensitive comparison.
         * Used to match data from the CSV file.
         */
        public static DepartmentType fromString(String label) {
            for (DepartmentType dept : values()) {
                if (dept.getLabel().equalsIgnoreCase(label.trim())) return dept;
            }
            return null; // not found
        }
    }

    // ================================================================
    //  FIELDS
    // ================================================================
    private DepartmentType type;    // Finance, HR, IT, Marketing, or Sales
    private String description;     // Short description of the department

    // ================================================================
    //  CONSTRUCTOR
    // ================================================================
    public Department(DepartmentType type, String description) {
        this.type        = type;
        this.description = description;
    }

    // ================================================================
    //  GETTERS
    // ================================================================
    public DepartmentType getType()  { return type; }
    public String getLabel()         { return type.getLabel(); }
    public String getDescription()   { return description; }

    /**
     * Displays all valid Department types in the terminal.
     * Used in Add Record to show the user what options are available.
     */
    public static void displayDepartmentTypes() {
        System.out.println("\nPlease select from the following Departments:");
        for (DepartmentType dept : DepartmentType.values()) {
            System.out.println(dept.getOption() + ". " + dept.getLabel());
        }
    }

    @Override
    public String toString() {
        return String.format("%-12s | %s", type.getLabel(), description);
    }
}
