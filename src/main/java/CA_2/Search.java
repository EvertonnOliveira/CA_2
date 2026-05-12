/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;
import java.util.List;

public class Search {
   
    public static void search(List<Employee> employees, String name) {

        // Search is case-insensitive
        int index = binarySearch(employees, name.toLowerCase().trim(), 0, employees.size() - 1);

        if (index == -1) {
            System.out.println("\n[NOT FOUND] No employee named \"" + name + "\" was found.");
        } else {
            Employee found = employees.get(index);
            System.out.println("\n========= Employee Found =========");
            System.out.printf("%-15s %s%n", "Full Name:",   found.getFullName());
            System.out.printf("%-15s %s%n", "Job Title:",   found.getJobTitle());
            System.out.printf("%-15s %s%n", "Department:",  found.getDepartment());
            System.out.println("==================================");
        }
    }

   
    private static int binarySearch(List<Employee> employees, String target, int left, int right) {

        // BASE CASE: search range is empty → name not found
        if (left > right) {
            return -1;
        }

        // Find the middle index of the current range
        int mid = (left + right) / 2;
        String midName = employees.get(mid).getFullName().toLowerCase();

        // BASE CASE: found the name at middle position
        if (midName.equals(target)) {
            return mid;
        }

        // target comes BEFORE middle alphabetically → search LEFT half
        if (target.compareTo(midName) < 0) {
            return binarySearch(employees, target, left, mid - 1);
        }

        // target comes AFTER middle alphabetically → search RIGHT half
        return binarySearch(employees, target, mid + 1, right);
    }
}
