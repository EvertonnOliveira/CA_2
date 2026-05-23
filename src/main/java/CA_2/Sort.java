package CA_2; // All files in this project share this package name

import java.util.List; // Allows us to use List to store Employee objects

public class Sort { // This class contains both sorting algorithms

    // ================================================================
    //  SECTION 1 — RECURSIVE SELECTION SORT
    //
    //  HOW IT WORKS:
    //  - Find the smallest name from the current position to the end
    //  - Swap it into the current position
    //  - Recursively repeat for the next position
    //  - Stop when there is only one element left (base case)
    // ================================================================

    // PUBLIC method — called from Main.java when user selects Selection Sort
    public static void recursiveSelectionSort(List<Employee> employees) {
        selectionSort(employees, 0); // Start the recursion from index 0
    }

    // PRIVATE recursive method — does the actual sorting work
    // startIndex = the current position we are filling with the smallest name
    private static void selectionSort(List<Employee> employees, int startIndex) {

        // BASE CASE: if startIndex reached the last element, the list is sorted — stop
        // size()-1 because the last element has nothing left to compare with
        if (startIndex >= employees.size() - 1) {
            return; // void return — just exits this method call
        }

        // Assume the minimum element is at startIndex
        // (same as professor's pseudocode: minIndex = i)
        int minIndex = startIndex;

        // Inner loop: check every element AFTER startIndex
        // (same as professor's pseudocode: FOR j = i+1 to n-1)
        for (int j = startIndex + 1; j < employees.size(); j++) {

            // Get the full name at position j in lowercase for fair comparison
            String current = employees.get(j).getFullName().toLowerCase();

            // Get the full name at the current minimum position in lowercase
            String minimum = employees.get(minIndex).getFullName().toLowerCase();

            // compareTo() compares alphabetically:
            // negative result = current comes before minimum → current is the new minimum
            if (current.compareTo(minimum) < 0) {
                minIndex = j; // update the minimum position
            }
        }

        // SWAP: only swap if the minimum is not already in the correct position
        // (avoids unnecessary swap when the element is already in the right place)
        if (minIndex != startIndex) {

            // Save the element at startIndex temporarily so we don't lose it
            Employee temp = employees.get(startIndex);

            // Place the minimum element at startIndex
            employees.set(startIndex, employees.get(minIndex));

            // Place the old startIndex element where the minimum was
            employees.set(minIndex, temp);
        }

        // RECURSIVE CALL: move to the next position (replaces i++ from professor's FOR loop)
        // The element at startIndex is now correct — sort the rest of the list
        selectionSort(employees, startIndex + 1);
    }

    // ================================================================
    //  SECTION 2 — RECURSIVE QUICK SORT
    //
    //  HOW IT WORKS:
    //  - Pick a PIVOT element (the last element of the current section)
    //  - PARTITION: move names smaller than pivot to the LEFT
    //               move names greater than pivot to the RIGHT
    //               place pivot in its correct final position
    //  - Recursively sort the LEFT part and the RIGHT part
    //  - Stop when the section has 0 or 1 element (base case)
    // ================================================================

    // PUBLIC method — called from Main.java when user selects Quick Sort
    public static void recursiveQuickSort(List<Employee> employees) {
        // Start with the full list: from index 0 to the last index
        quickSort(employees, 0, employees.size() - 1);
    }

    // PRIVATE recursive method — divides and sorts around a pivot
    // low = start of the current section
    // high = end of the current section
    private static void quickSort(List<Employee> employees, int low, int high) {

        // BASE CASE: section has 1 or 0 elements — already sorted, nothing to do
        if (low >= high) {
            return;
        }

        // PARTITION: rearrange elements around the pivot
        // Returns the final index where the pivot was placed
        int pivotIndex = partition(employees, low, high);

        // RECURSIVE CALL LEFT: sort everything BEFORE the pivot
        quickSort(employees, low, pivotIndex - 1);

        // RECURSIVE CALL RIGHT: sort everything AFTER the pivot
        quickSort(employees, pivotIndex + 1, high);
    }

    // PRIVATE method — the core of Quick Sort (partition step)
    // Picks the last element as pivot and rearranges the section so:
    //   - Names smaller than pivot go to the LEFT side
    //   - Names greater than pivot go to the RIGHT side
    //   - Pivot ends up in its correct final position
    private static int partition(List<Employee> employees, int low, int high) {

        // Pick the LAST element of the section as pivot
        String pivot = employees.get(high).getFullName().toLowerCase();

        // i tracks the boundary between "smaller than pivot" and "greater than pivot"
        // starts before the section — nothing on the left side yet
        int i = low - 1;

        // Walk through every element in the section EXCEPT the pivot (which is at high)
        for (int j = low; j < high; j++) {

            // Get the name of the current element
            String current = employees.get(j).getFullName().toLowerCase();

            // If current is SMALLER than pivot → it belongs on the LEFT side
            // compareTo < 0 means current comes before pivot alphabetically
            if (current.compareTo(pivot) < 0) {

                // Expand the left side by one position
                i++;

                // Swap current element into the left side
                Employee temp = employees.get(i);    // save element at boundary
                employees.set(i, employees.get(j));  // move current to boundary
                employees.set(j, temp);               // move old boundary to j
            }
            // If current > pivot → do nothing, it stays on the right side
        }

        // Place pivot in its correct final position
        // pivot belongs at i+1 (right after all smaller elements)
        int pivotFinalIndex = i + 1;

        // Swap pivot (currently at high) into its correct position (i+1)
        Employee temp = employees.get(pivotFinalIndex);
        employees.set(pivotFinalIndex, employees.get(high)); // pivot goes to final position
        employees.set(high, temp);                           // old element goes to high

        // Return pivot's final index so quickSort() knows where to split
        return pivotFinalIndex;
    }

    // ================================================================
    //  DISPLAY METHOD — shared by both sorting algorithms
    // ================================================================

    // PUBLIC method — displays the first 20 names from the sorted list in the terminal
    public static void displayFirst20(List<Employee> employees) {

        // Print the table header
        System.out.println("\n========= Sorted Employee List (First 20) =========");

        // Printf formats columns with fixed widths:
        // %-4s  = left-aligned, 4 characters wide
        // %-22s = left-aligned, 22 characters wide
        System.out.printf("%-4s %-22s %-25s %-20s%n",
                "#", "Full Name", "Job Title", "Department");

        // Print a divider line of 75 dashes
        System.out.println("-".repeat(75));

        // Math.min() ensures we don't go past the list size if less than 20 employees
        int limit = Math.min(20, employees.size());

        // Loop through the first 20 (or fewer) employees and print each row
        for (int i = 0; i < limit; i++) {
            Employee e = employees.get(i); // get employee at position i

            // Print row: number, full name, job title, department
            // (i+1) because the display starts at 1, not 0
            System.out.printf("%-4d %-22s %-25s %-20s%n",
                    (i + 1),
                    e.getFullName(),
                    e.getJobTitle(),
                    e.getDepartment());
        }

        // Print closing divider and summary line
        System.out.println("-".repeat(75));
        System.out.println("Showing " + limit + " of " + employees.size() + " employees.");
    }
}