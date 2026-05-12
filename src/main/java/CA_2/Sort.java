package CA_2;

import java.util.List;


public class Sort {

    public static void recursiveSelectionSort(List<Employee> employees) {
        sort(employees, 0);
    }

    private static void sort(List<Employee> employees, int startIndex) {
        if (startIndex >= employees.size() - 1) {
            return;
        }

        int minIndex = startIndex;

        for (int j = startIndex + 1; j < employees.size(); j++) {
            String current = employees.get(j).getFullName().toLowerCase();
            String minimum = employees.get(minIndex).getFullName().toLowerCase();

            if (current.compareTo(minimum) < 0) {
                minIndex = j;
            }
        }

        if (minIndex != startIndex) {
            Employee temp = employees.get(startIndex);
            employees.set(startIndex, employees.get(minIndex));
            employees.set(minIndex, temp);
        }

        sort(employees, startIndex + 1);
    }

    public static void displayFirst20(List<Employee> employees) {
        System.out.println("\n========= Sorted Employee List (First 20) =========");
        System.out.printf("%-4s %-22s %-25s %-20s%n", "#", "Full Name", "Job Title", "Department");
        System.out.println("-".repeat(75));

        int limit = Math.min(20, employees.size());
        for (int i = 0; i < limit; i++) {
            Employee e = employees.get(i);
            System.out.printf("%-4d %-22s %-25s %-20s%n",
                    (i + 1),
                    e.getFullName(),
                    e.getJobTitle(),
                    e.getDepartment());
        }

        System.out.println("-".repeat(75));
        System.out.println("Showing " + limit + " of " + employees.size() + " employees.");
    }
}