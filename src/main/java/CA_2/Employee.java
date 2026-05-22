// This code belongs to the package (folder) called CA_2
package CA_2;

// This class represents one employee and stores all their information
public class Employee {

    // These are the fields (data) that each employee has
    private String firstName;   // The employee's first name
    private String lastName;    // The employee's last name
    private String gender;      // The employee's gender
    private String email;       // The employee's email address
    private double salary;      // The employee's salary (can have decimal values)
    private String department;  // The department the employee works in
    private String position;    // The level of the employee (intern, junior, senior, etc.)
    private String jobTitle;    // The employee's job title
    private String company;     // The company the employee works for

    // Constructor: this method runs when we create a new Employee object
    // It receives all the employee's information and saves it to the fields above
    public Employee(String firstName, String lastName, String gender,
                    String email, double salary, String department,
                    String position, String jobTitle, String company) {

        this.firstName  = firstName;  // Save the first name to this object
        this.lastName   = lastName;   // Save the last name to this object
        this.gender     = gender;     // Save the gender to this object
        this.email      = email;      // Save the email to this object
        this.salary     = salary;     // Save the salary to this object
        this.department = department; // Save the department to this object
        this.position   = position;   // Save the position to this object
        this.jobTitle   = jobTitle;   // Save the job title to this object
        this.company    = company;    // Save the company to this object
    }

    // ----------------------------------------------------------------
    // Getters
    // ----------------------------------------------------------------
    // Getters are methods that let other classes read the private fields

    public String getFirstName()  { return firstName; }   // Returns the first name
    public String getLastName()   { return lastName; }    // Returns the last name
    public String getGender()     { return gender; }      // Returns the gender
    public String getEmail()      { return email; }       // Returns the email
    public double getSalary()     { return salary; }      // Returns the salary
    public String getDepartment() { return department; }  // Returns the department
    public String getPosition()   { return position; }    // Returns the position
    public String getJobTitle()   { return jobTitle; }    // Returns the job title
    public String getCompany()    { return company; }     // Returns the company

    // Returns the employee's full name by joining first name and last name
    public String getFullName() {
        return firstName + " " + lastName; // Combine first and last name with a space
    }

    // This method returns a formatted text summary of the employee
    // It is called automatically when we try to print an Employee object
    @Override
    public String toString() {
        // Format the output in fixed-width columns so it looks clean in the terminal
        // %-20s means: left-align text in a column 20 characters wide
        return String.format("%-20s | %-25s | %-20s | %s",
                getFullName(),   // Column 1: full name (20 chars wide)
                jobTitle,        // Column 2: job title (25 chars wide)
                department,      // Column 3: department (20 chars wide)
                company);        // Column 4: company name (no fixed width)
    }
}