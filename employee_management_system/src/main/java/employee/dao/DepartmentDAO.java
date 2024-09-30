package employee.dao;


import employee.model.Department;
import employee.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    public void addDepartment(Department department) throws SQLException {
        String query = "INSERT INTO Department (name) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, department.getName());
            pstmt.executeUpdate();
        }
    }

    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM Department";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                departments.add(new Department(rs.getInt("id"), rs.getString("name")));
            }
        }
        return departments;
    }
}
