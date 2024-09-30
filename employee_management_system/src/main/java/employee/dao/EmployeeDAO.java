package employee.dao;


import employee.model.Employee;
import employee.model.Department;
import employee.model.Role;
import employee.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void addEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO Employee (name, email, salary, department_id, role_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setDouble(3, employee.getSalary());
            pstmt.setInt(4, employee.getDepartment().getId());
            pstmt.setInt(5, employee.getRole().getId());
            pstmt.executeUpdate();
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        
        String query = "SELECT Employee.id, Employee.name, Employee.email, Employee.salary, "
                     + "Department.id AS dept_id, Department.name AS dept_name, "
                     + "Role.id AS role_id, Role.name AS role_name "
                     + "FROM Employee "
                     + "JOIN Department ON Employee.department_id = Department.id "
                     + "JOIN Role ON Employee.role_id = Role.id";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Retrieve employee information
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                double salary = rs.getDouble("salary");
                
                // Retrieve department information
                int deptId = rs.getInt("dept_id");
                String deptName = rs.getString("dept_name");
                Department department = new Department(deptId, deptName);
                
                // Retrieve role information
                int roleId = rs.getInt("role_id");
                String roleName = rs.getString("role_name");
                Role role = new Role(roleId, roleName);
                
                // Create Employee object and add it to the list
                Employee employee = new Employee(id, name, email, salary, department, role);
                employees.add(employee);
            }
        }

        return employees;
    }

    public void deleteEmployee(int employeeId) throws SQLException {
        String query = "DELETE FROM Employee WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();
        }
    }

    public void updateEmployee(Employee employee) throws SQLException {
        String query = "UPDATE Employee SET name = ?, email = ?, salary = ?, department_id = ?, role_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setDouble(3, employee.getSalary());
            pstmt.setInt(4, employee.getDepartment().getId());
            pstmt.setInt(5, employee.getRole().getId());
            pstmt.setInt(6, employee.getId());
            pstmt.executeUpdate();
        }
    }
}

