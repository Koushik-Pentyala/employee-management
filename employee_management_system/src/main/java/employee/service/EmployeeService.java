package employee.service;


import employee.dao.EmployeeDAO;
import employee.model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private DepartmentService departmentService = new DepartmentService();
    private RoleService roleService = new RoleService();

    public void addEmployee(Employee employee) throws SQLException {
        validateEmployee(employee);
        employeeDAO.addEmployee(employee);
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDAO.getAllEmployees();
    }

    public void updateEmployee(Employee employee) throws SQLException {
        validateEmployee(employee);
        employeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(int employeeId) throws SQLException {
        employeeDAO.deleteEmployee(employeeId);
    }

    private void validateEmployee(Employee employee) throws SQLException {
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be null or empty.");
        }
        if (employee.getEmail() == null || employee.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee email cannot be null or empty.");
        }
        if (employee.getSalary() <= 0) {
            throw new IllegalArgumentException("Employee salary must be greater than 0.");
        }
        if (employee.getDepartment() == null || departmentService.getDepartmentById(employee.getDepartment().getId()) == null) {
            throw new IllegalArgumentException("Invalid department.");
        }
        if (employee.getRole() == null || roleService.getRoleById(employee.getRole().getId()) == null) {
            throw new IllegalArgumentException("Invalid role.");
        }
    }
}

