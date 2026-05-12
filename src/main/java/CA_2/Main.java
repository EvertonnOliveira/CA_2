 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.util.Scanner;


public class Main {
    

    
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

    public int getOption() {
        return option;
    }

    public String getLabel() {
        return label;
        
    }

    public static MenuOptionEnum fromInt(int value) {
        for (MenuOptionEnum opt : values()) {
            if (opt.getOption() == value) {
                return opt;
            }
        }
        return null;
    }
}
    
    
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    while (true) {
        MenuOptionEnum option = null;
        while (option == null) {
            System.out.println("\n============================= "
                    + "\n  Bank Organisation System "
                    + "\n=============================");
            System.out.println("\n========= Main Menu =========");

            for (MenuOptionEnum opt : MenuOptionEnum.values()) {
                System.out.println(opt.getOption() + ". " + opt.getLabel());
            }

            int choice = sc.nextInt();
            option = MenuOptionEnum.fromInt(choice);
            if (option == null) {
                System.out.println("Invalid choice! Please try again.");
            }
        }

        switch (option) {
            case SORT -> {
                // Implementation SORT
            }
            case SEARCH -> {
                // Implementation SEARCH
            }
            case ADD_RECORD -> {
                // Implementation ADD_RECORD
            }
            case BINARY_TREE -> {
                // Implementation BINARY_TREE
            }
            case EXIT -> {
                System.out.println("Exiting program...");
                sc.close();
                return;
                
                }
            }
        }
    }

}



