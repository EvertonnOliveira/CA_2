package CA_2;

import java.util.List;

/**
 * SortingAlgorithm.java
 * Contains TWO recursive sorting algorithms for the Bank Organisation System:
 *   1. Selection Sort (recursive)
 *   2. Quick Sort (recursive)
 *
 * Both sort the employee list alphabetically by full name.
 */
public class Sort {

    // ================================================================
    //  SECTION 1 — RECURSIVE SELECTION SORT
    //  Adapted from professor's pseudocode (iterative → recursive)
    //
    //  HOW IT WORKS:
    //  - Find the smallest name from current position to end of list
    //  - Swap it to the current position
    //  - Recursively repeat for the next position
    //
    //  Example:
    //  [Kate, Alex, Zara, Ben]
    //   → finds Alex (smallest) → swaps to pos 0 → [Alex, Kate, Zara, Ben]
    //   → finds Ben (smallest of rest) → swaps to pos 1 → [Alex, Ben, Zara, Kate]
    //   → finds Kate → swaps to pos 2 → [Alex, Ben, Kate, Zara] ✅
    // ================================================================

    /**
     * PUBLIC entry point for Selection Sort.
     * Called from Main when user picks "Selection Sort".
     * Starts the recursion from index 0.
     *
     * @param employees the list to sort
     */
    public static void recursiveSelectionSort(List<Employee> employees) {
        // Start sorting from position 0
        selectionSort(employees, 0);
    }

    /**
     * PRIVATE recursive method — does the actual Selection Sort work.
     *
     * @param employees  the list being sorted
     * @param startIndex current position we are filling (replaces FOR i in pseudocode)
     */
    private static void selectionSort(List<Employee> employees, int startIndex) {

        // BASE CASE: if startIndex reached the last element, list is sorted → stop
        // employees.size()-1 because last element has nothing to compare with
        if (startIndex >= employees.size() - 1) {
            return; // void return — just stops this method call
        }

        // Assume the minimum element is at the current startIndex
        // (same as professor's pseudocode: minIndex = i)
        int minIndex = startIndex;

        // Inner loop: look at every element AFTER startIndex
        // (same as professor's pseudocode: FOR j = i+1 to n-1)
        for (int j = startIndex + 1; j < employees.size(); j++) {

            // Get the full name at position j (lowercase for fair comparison)
            String current = employees.get(j).getFullName().toLowerCase();

            // Get the full name at current minimum position (lowercase)
            String minimum = employees.get(minIndex).getFullName().toLowerCase();

            // compareTo() compares alphabetically:
            // negative = current comes before minimum → current is the new minimum
            if (current.compareTo(minimum) < 0) {
                minIndex = j; // update minimum position
            }
        }

        // SWAP: only swap if minimum is not already at startIndex
        // (avoids unnecessary swap when element is already in correct place)
        if (minIndex != startIndex) {

            // Store startIndex element temporarily so we don't lose it
            Employee temp = employees.get(startIndex);

            // Place the minimum element at startIndex
            employees.set(startIndex, employees.get(minIndex));

            // Place the old startIndex element where minimum was
            employees.set(minIndex, temp);
        }

        // RECURSIVE CALL: move to next position (replaces i++ in professor's FOR loop)
        // Now the element at startIndex is correct — sort the rest
        selectionSort(employees, startIndex + 1);
    }


    // ================================================================
    //  SECTION 2 — RECURSIVE QUICK SORT
    //
    //  HOW IT WORKS:
    //  - Pick a PIVOT element (we use the last element of each section)
    //  - PARTITION: move names smaller than pivot to LEFT
    //               move names greater than pivot to RIGHT
    //               place pivot in its correct final position
    //  - Recursively sort LEFT part and RIGHT part
    //
    //  Example:
    //  [Kate, Alex, Zara, Ben, Elena]   pivot = "Elena"
    //  After partition: [Alex, Ben] | Elena | [Kate, Zara]
    //  Recurse left:    [Alex, Ben]  → sorted ✅
    //  Recurse right:   [Kate, Zara] → sorted ✅
    //  Final: [Alex, Ben, Elena, Kate, Zara] ✅
    // ================================================================

    /**
     * PUBLIC entry point for Quick Sort.
     * Called from Main when user picks "Quick Sort".
     * Starts the recursion on the full list (index 0 to last).
     *
     * @param employees the list to sort
     */
    public static void recursiveQuickSort(List<Employee> employees) {
        // Start with the full range: from index 0 to last index
        quickSort(employees, 0, employees.size() - 1);
    }

    /**
     * PRIVATE recursive Quick Sort method.
     *
     * @param employees the list being sorted
     * @param low       start index of current section
     * @param high      end index of current section
     */
    private static void quickSort(List<Employee> employees, int low, int high) {

        // BASE CASE: section has 1 or 0 elements → already sorted, nothing to do
        if (low >= high) {
            return;
        }

        // PARTITION: rearrange elements around pivot
        // Returns the FINAL index where pivot was placed
        int pivotIndex = partition(employees, low, high);

        // RECURSIVE CALL LEFT: sort everything BEFORE the pivot
        quickSort(employees, low, pivotIndex - 1);

        // RECURSIVE CALL RIGHT: sort everything AFTER the pivot
        quickSort(employees, pivotIndex + 1, high);
    }

    /**
     * PRIVATE partition method — the core of Quick Sort.
     * Picks last element as pivot and rearranges the section so:
     *   - Everything smaller than pivot is on the LEFT
     *   - Everything greater than pivot is on the RIGHT
     *   - Pivot ends up in its correct final position
     *
     * @param employees the list
     * @param low       start of section
     * @param high      end of section (where pivot is)
     * @return          final index of pivot after partitioning
     */
    private static int partition(List<Employee> employees, int low, int high) {

        // Pick the LAST element of the section as pivot
        String pivot = employees.get(high).getFullName().toLowerCase();

        // i is the boundary between "smaller than pivot" and "greater than pivot"
        // starts just before the section (nothing is in the "smaller" side yet)
        int i = low - 1;

        // Walk through every element in the section EXCEPT the pivot (at high)
        for (int j = low; j < high; j++) {

            // Get name of current element
            String current = employees.get(j).getFullName().toLowerCase();

            // If current element is SMALLER than pivot → belongs on LEFT side
            // compareTo < 0 means current comes before pivot alphabetically
            if (current.compareTo(pivot) < 0) {

                // Expand the left side by one position
                i++;

                // Swap current element into the left side
                Employee temp = employees.get(i);  // save element at boundary
                employees.set(i, employees.get(j)); // move current to boundary
                employees.set(j, temp);              // move old boundary to j
            }
            // If current > pivot → do nothing, it stays on the right side
        }

        // Place pivot in its correct final position
        // pivot belongs at i+1 (right after all smaller elements)
        int pivotFinalIndex = i + 1;

        // Swap pivot (currently at high) to its correct position (i+1)
        Employee temp = employees.get(pivotFinalIndex);
        employees.set(pivotFinalIndex, employees.get(high)); // pivot goes to final pos
        employees.set(high, temp);                           // old element goes to high

        // Return pivot's final index so quickSort() knows where to split
        return pivotFinalIndex;
    }


    // ================================================================
    //  DISPLAY METHOD — shared by both sorting algorithms
    // ================================================================

    /**
     * Displays the first 20 employees from the sorted list in the terminal.
     * Used by both Selection Sort and Quick Sort after sorting.
     *
     * @param employees the already-sorted list
     */
    public static void displayFirst20(List<Employee> employees) {

        System.out.println("\n========= Sorted Employee List (First 20) =========");

        // Print table header with fixed column widths
        // %-4s  = left-aligned, 4 chars wide
        // %-22s = left-aligned, 22 chars wide
        System.out.printf("%-4s %-22s %-25s %-20s%n",
                "#", "Full Name", "Job Title", "Department");

        // Print a divider line of 75 dashes
        System.out.println("-".repeat(75));

        // Math.min() ensures we don't go over the list size if less than 20 employees
        int limit = Math.min(20, employees.size());

        // Loop through first 20 (or less) employees and print each row
        for (int i = 0; i < limit; i++) {
            Employee e = employees.get(i); // get employee at position i

            // Print row: number, full name, job title, department
            // (i+1) because display starts at 1, not 0
            System.out.printf("%-4d %-22s %-25s %-20s%n",
                    (i + 1),
                    e.getFullName(),
                    e.getJobTitle(),
                    e.getDepartment());
        }

        // Print closing divider and summary
        System.out.println("-".repeat(75));
        System.out.println("Showing " + limit + " of " + employees.size() + " employees.");
    }
}
