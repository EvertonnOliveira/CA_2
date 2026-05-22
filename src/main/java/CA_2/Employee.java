/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author evertonoliveira
 */
public class Employee {
    
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private double salary;
    private String department;
    private String position;   // intern, junior, middle, senior, etc.
    private String jobTitle;
    private String company;

    // Constructor
    public Employee(String firstName, String lastName, String gender,
                    String email, double salary, String department,
                    String position, String jobTitle, String company) {
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.gender     = gender;
        this.email      = email;
        this.salary     = salary;
        this.department = department;
        this.position   = position;
        this.jobTitle   = jobTitle;
        this.company    = company;
    }

    // ----------------------------------------------------------------
    // Getters
    // ----------------------------------------------------------------
    public String getFirstName()  { return firstName; }
    public String getLastName()   { return lastName; }
    public String getGender()     { return gender; }
    public String getEmail()      { return email; }
    public double getSalary()     { return salary; }
    public String getDepartment() { return department; }
    public String getPosition()   { return position; }
    public String getJobTitle()   { return jobTitle; }
    public String getCompany()    { return company; }

    /**
     * Returns the employee's full name (First + Last).
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Human-readable summary shown in the terminal.
     */
    @Override
    public String toString() {
        return String.format("%-20s | %-25s | %-20s | %s",
                getFullName(), jobTitle, department, company);
    }
}

    
    

