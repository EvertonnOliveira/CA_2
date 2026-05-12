package CA_2;

import java.util.List;

/**
 * SearchAlgorithm.java
 * Contains TWO searching algorithms for the Bank Organisation System:
 *   1. Linear Search  - adapted from professor's LinearBinary example
 *   2. Binary Search  - recursive, adapted from professor's BinarySearchRecursive example
 *
 * Both search the employee list by full name.
 */
public class Search {

    // ================================================================
    //  SECTION 1 — LINEAR SEARCH
    //  Adapted from professor's LinearBinary.linearSearch()
    //
    //  HOW IT WORKS:
    //  - Checks every element one by one from start to end
    //  - Returns as soon as it finds a match
    //  - Does NOT require the list to be sorted
    //
    //  Professor's original (int[] array):
    //    for (int i = 0; i < arr.length; i++)
    //        if (arr[i] == key) return i;
    //    return -1;
    //
    //  This version adapts the same logic to List<Employee> with Strings
    // ================================================================

    /**
     * PUBLIC entry point for Linear Search.
     * Called from Main when user picks "Linear Search".
     * Does NOT require the list to be sorted first.
     *
     * @param employees the list to search (sorted or unsorted)
     * @param name      the full name to search for
     */
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

    /**
     * PRIVATE Linear Search method.
     * Same logic as professor's linearSearch() but for List<Employee>.
     *
     * Professor's:  if (arr[i] == key)
     * This version: if (fullName.equals(target))
     *
     * @param employees the list to search
     * @param target    the name to find (lowercase)
     * @return          index if found, -1 if not found
     */
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


    // ================================================================
    //  SECTION 2 — RECURSIVE BINARY SEARCH
    //  Adapted from professor's MixedBinarySearch + BinarySearchRecursive
    //
    //  HOW IT WORKS:
    //  - Looks at the MIDDLE element of the current range
    //  - If match → found!
    //  - If target < middle → search LEFT half (recursive)
    //  - If target > middle → search RIGHT half (recursive)
    //  - If left > right → not found (base case)
    //
    //  IMPORTANT: List MUST be sorted before using Binary Search!
    //
    //  Professor's comparison logic (kept identical):
    //    int cmp = arr[mid].compareTo(target)
    //    if (cmp == 0)  → found
    //    if (cmp < 0)   → go right
    //    else           → go left
    //  Only change: while loop replaced with recursion
    // ================================================================

  
    
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

    /**
     * PRIVATE recursive Binary Search method.
     * Same comparison logic as professor's MixedBinarySearch.binarySearch()
     * but recursive instead of iterative (required by CA2).
     *
     * Base case 1: left > right  → not found → return -1
     * Base case 2: cmp == 0      → found     → return index
     * Recursive:   search left or right half based on comparison
     *
     * @param employees the sorted list
     * @param left      start of current search range
     * @param right     end of current search range
     * @param target    the name to find
     * @return          index if found, -1 if not found
     */
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
