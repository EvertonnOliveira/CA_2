package CA_2;

import java.util.List;


public class Sort {

    
    public static void recursiveSelectionSort(List<Employee> employees) {
        // Start sorting from position 0
        selectionSort(employees, 0);
    }

 
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

    
    public static void recursiveQuickSort(List<Employee> employees) {
        // Start with the full range: from index 0 to last index
        quickSort(employees, 0, employees.size() - 1);
    }

    
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

    private static void quickSort(List<Employee> employees, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
