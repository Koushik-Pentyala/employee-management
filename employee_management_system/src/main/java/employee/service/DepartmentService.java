package employee.service;


import employee.dao.DepartmentDAO;
import employee.model.Department;

import java.sql.SQLException;
import java.util.List;

public class DepartmentService {
    private DepartmentDAO departmentDAO = new DepartmentDAO();

    public void addDepartment(Department department) throws SQLException {
        if (department.getName() == null || department.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be null or empty.");
        }
        departmentDAO.addDepartment(department);
    }

    public List<Department> getAllDepartments() throws SQLException {
        return departmentDAO.getAllDepartments();
    }

    public Department getDepartmentById(int departmentId) throws SQLException {
        return departmentDAO.getAllDepartments().stream()
                .filter(dept -> dept.getId() == departmentId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
    }
}

