package employee.service;


import employee.dao.RoleDAO;
import employee.model.Role;

import java.sql.SQLException;
import java.util.List;

public class RoleService {
    private RoleDAO roleDAO = new RoleDAO();

    public void addRole(Role role) throws SQLException {
        if (role.getName() == null || role.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be null or empty.");
        }
        roleDAO.addRole(role);
    }

    public List<Role> getAllRoles() throws SQLException {
        return roleDAO.getAllRoles();
    }

    public Role getRoleById(int roleId) throws SQLException {
        return roleDAO.getAllRoles().stream()
                .filter(role -> role.getId() == roleId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
    }
}
