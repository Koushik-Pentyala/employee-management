package employee.model;


public class Employee {
    private int id;
    private String name;
    private String email;
    private double salary;
    private Department department;
    private Role role;

    // Constructors
    public Employee() {}

    public Employee(String name, String email, double salary, Department department, Role role) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.role = role;
    }

    public Employee(int id, String name, String email, double salary, Department department, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.role = role;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", salary=" + salary + ", department="
				+ department + ", role=" + role + "]";
	}

    // Getters and Setters
    // ...

    // toString method
    // ...
}