package jm.security.example.service;

import jm.security.example.model.Role;
import jm.security.example.model.User;

import java.util.List;

public interface UserService {
    public List<User> listUser();

    public void addUser(User user);

    public void editUser(User user);

    public void removeUser(Long id);

    public User getUserById(Long id);

    public User getUserByName(String name);

    public Role getRoleByName(String role);

}
