package employee;

import employee.model.Department;
import employee.model.Employee;
import employee.model.Role;
import employee.service.DepartmentService;
import employee.service.EmployeeService;
import employee.service.RoleService;
import employee.utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem {
    private static DepartmentService departmentService = new DepartmentService();
    private static RoleService roleService = new RoleService();
    private static EmployeeService employeeService = new EmployeeService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (!exit) {
            System.out.println("Employee Management System");
            System.out.println("1. Add Department");
            System.out.println("2. Add Role");
            System.out.println("3. Add Employee");
            System.out.println("4. View Employees");
            System.out.println("5. View Departments");
            System.out.println("6. View Roles");
            System.out.println("7. Update Employee");
            System.out.println("8. Delete Employee");
            System.out.println("9. Exit");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            try {
                switch (option) {
                    case 1:
                        addDepartment(scanner);
                        break;
                    case 2:
                        addRole(scanner);
                        break;
                    case 3:
                        addEmployee(scanner);
                        break;
                    case 4:
                        viewEmployees();
                        break;
                    case 5:
                        viewDepartments();
                        break;
                    case 6:
                        viewRoles();
                        break;
                    case 7:
                        updateEmployee(scanner);
                        break;
                    case 8:
                        deleteEmployee(scanner);
                        break;
                    case 9:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }

    private static void addDepartment(Scanner scanner) throws SQLException {
        System.out.print("Enter Department Name: ");
        String name = scanner.nextLine();
        Department department = new Department(0, name);
        departmentService.addDepartment(department);
        System.out.println("Department added successfully.");
    }

    private static void addRole(Scanner scanner) throws SQLException {
        System.out.print("Enter Role Name: ");
        String name = scanner.nextLine();
        Role role = new Role(0, name);
        roleService.addRole(role);
        System.out.println("Role added successfully.");
    }

    private static void addEmployee(Scanner scanner) throws SQLException {
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        List<Department> departments = departmentService.getAllDepartments();
        List<Role> roles = roleService.getAllRoles();

        System.out.println("Select Department:");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i).getName());
        }
        int departmentIndex = scanner.nextInt() - 1;
        Department department = departments.get(departmentIndex);

        System.out.println("Select Role:");
        for (int i = 0; i < roles.size(); i++) {
            System.out.println((i + 1) + ". " + roles.get(i).getName());
        }
        int roleIndex = scanner.nextInt() - 1;
        Role role = roles.get(roleIndex);

        Employee employee = new Employee(0, name, email, salary, department, role);
        employeeService.addEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    private static void viewEmployees() throws SQLException {
        List<Employee> employees = employeeService.getAllEmployees();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private static void viewDepartments() throws SQLException {
        List<Department> departments = departmentService.getAllDepartments();
        for (Department department : departments) {
            System.out.println(department);
        }
    }

    private static void viewRoles() throws SQLException {
        List<Role> roles = roleService.getAllRoles();
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    private static void updateEmployee(Scanner scanner) throws SQLException {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter New Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Employee Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter New Employee Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        List<Department> departments = departmentService.getAllDepartments();
        List<Role> roles = roleService.getAllRoles();

        System.out.println("Select New Department:");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i).getName());
        }
        int departmentIndex = scanner.nextInt() - 1;
        Department department = departments.get(departmentIndex);

        System.out.println("Select New Role:");
        for (int i = 0; i < roles.size(); i++) {
            System.out.println((i + 1) + ". " + roles.get(i).getName());
        }
        int roleIndex = scanner.nextInt() - 1;
        Role role = roles.get(roleIndex);

        Employee employee = new Employee(id, name, email, salary, department, role);
        employeeService.updateEmployee(employee);
        System.out.println("Employee updated successfully.");
    }

    private static void deleteEmployee(Scanner scanner) throws SQLException {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        employeeService.deleteEmployee(id);
        System.out.println("Employee deleted successfully.");
    }
}
