package CA_2;

import java.util.List;



public class Search {

  
    public static void linearSearch(List<Employee> employees, String name) {

        // Search is case-insensitive
        String target = name.toLowerCase().trim();

        // Call the search and get the result index
        int result = doLinearSearch(employees, target);

        if (result == -1) {
            System.out.println("\n[NOT FOUND] No employee named \"" + name + "\" was found.");
        } else {
            Employee found = employees.get(result);
            System.out.println("\n========= Employee Found (Linear Search) =========");
            System.out.printf("%-15s %s%n", "Full Name:",  found.getFullName());
            System.out.printf("%-15s %s%n", "Job Title:",  found.getJobTitle());
            System.out.printf("%-15s %s%n", "Department:", found.getDepartment());
            System.out.println("==================================================");
        }
    }

  
    private static int doLinearSearch(List<Employee> employees, String target) {

        // professor's: for (int i = 0; i < arr.length; i++)
        for (int i = 0; i < employees.size(); i++) {

            // professor's: if (arr[i] == key) return i
            // here we compare full names (lowercase) instead of integers
            if (employees.get(i).getFullName().toLowerCase().equals(target)) {
                return i; // found - return index
            }
        }

        return -1; // professor's: return -1 (not found)
    }

  
    
    public static void binarySearch(List<Employee> employees, String name) {

        int result = doBinarySearch(employees, 0, employees.size() - 1, name.trim());

        if (result == -1) {
            System.out.println("\n[NOT FOUND] No employee named \"" + name + "\" was found.");
        } else {
            Employee found = employees.get(result);
            System.out.println("\n========= Employee Found (Binary Search) =========");
            System.out.printf("%-15s %s%n", "Full Name:",  found.getFullName());
            System.out.printf("%-15s %s%n", "Job Title:",  found.getJobTitle());
            System.out.printf("%-15s %s%n", "Department:", found.getDepartment());
            System.out.println("==================================================");
        }
    }

   
    private static int doBinarySearch(List<Employee> employees, int left, int right, String target) {

        // Base case: range is empty - not found
        // Replaces professor's: while (left <= right)
        if (left > right) {
            return -1;
        }

        // Find middle - same formula as professor
        int mid = (left + right) / 2;

        // Compare middle element with target
        // professor's: int cmp = arr[mid].compareTo(target)
        int cmp = employees.get(mid).getFullName().compareToIgnoreCase(target);

        // professor's: if (cmp == 0) return mid
        if (cmp == 0) {
            return mid; // found!
        }

        // professor's: else if (cmp < 0) left = mid + 1
        // mid comes BEFORE target → target is in RIGHT half
        if (cmp < 0) {
            return doBinarySearch(employees, mid + 1, right, target);
        }

        // professor's: else right = mid - 1
        // mid comes AFTER target → target is in LEFT half
        return doBinarySearch(employees, left, mid - 1, target);
    }
}
