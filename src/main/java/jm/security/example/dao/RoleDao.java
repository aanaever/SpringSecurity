package jm.security.example.dao;

import jm.security.example.model.Role;

import java.util.List;

public interface RoleDao {

    public List<Role> listRole();

    public void addRole(Role role);

    public void editRole(Role role);

    public void removeRole(Role role);

    public Role getRoleById(Long id);

    public Role getRoleByName(String name);
}
