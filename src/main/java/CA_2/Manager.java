/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 * Manager.java
 * Represents the different types of Managers in the Bank Organisation.
 *
 * The ManagerType enum defines the 3 valid manager types.
 * Each Manager object stores a type and the department they manage.
 */
public class Manager {

    // ================================================================
    //  ENUM — defines the 3 valid Manager types
    //  Used for validation in Add Record feature
    // ================================================================
    public enum ManagerType {
        SENIOR_MANAGER(1, "Senior Manager"),
        MANAGER(2, "Manager"),
        JUNIOR_MANAGER(3, "Junior Manager");

        private final int option;
        private final String label;

        ManagerType(int option, String label) {
            this.option = option;
            this.label = label;
        }

        public int getOption()  { return option; }
        public String getLabel() { return label; }

        /**
         * Converts a menu number to a ManagerType.
         * Returns null if the number is invalid.
         * Used for input validation.
         */
        public static ManagerType fromInt(int value) {
            for (ManagerType type : values()) {
                if (type.getOption() == value) return type;
            }
            return null; // invalid choice
        }

        /**
         * Converts a String label to a ManagerType.
         * Case-insensitive comparison.
         * Used to match data from the CSV file.
         */
        public static ManagerType fromString(String label) {
            for (ManagerType type : values()) {
                if (type.getLabel().equalsIgnoreCase(label.trim())) return type;
            }
            return null; // not found
        }
    }

    // ================================================================
    //  FIELDS
    // ================================================================
    private String name;           // Manager's full name
    private ManagerType type;      // Senior Manager, Manager, or Junior Manager
    private String department;     // Department this manager belongs to

    // ================================================================
    //  CONSTRUCTOR
    // ================================================================
    public Manager(String name, ManagerType type, String department) {
        this.name       = name;
        this.type       = type;
        this.department = department;
    }

    // ================================================================
    //  GETTERS
    // ================================================================
    public String getName()          { return name; }
    public ManagerType getType()     { return type; }
    public String getTypeLabel()     { return type.getLabel(); }
    public String getDepartment()    { return department; }

    /**
     * Displays all valid Manager types in the terminal.
     * Used in Add Record to show the user what options are available.
     */
    public static void displayManagerTypes() {
        System.out.println("\nPlease select from the following Manager Types:");
        for (ManagerType type : ManagerType.values()) {
            System.out.println(type.getOption() + ". " + type.getLabel());
        }
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-18s | %s", name, type.getLabel(), department);
    }
}

