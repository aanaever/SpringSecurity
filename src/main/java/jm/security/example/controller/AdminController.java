package jm.security.example.controller;

import jm.security.example.model.User;
import jm.security.example.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/users", "/"})
    public String allUsers(Model model) {
        List<User> users = userService.listUser();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/users/add")
    public String addUserPage(Model model, User user) {
        model.addAttribute("user", user);
        return "add";
    }

    @PostMapping(value = "/users/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/users/{id}/edit")
    public String editUserPage(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "/users/{id}/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/admin/";
    }

    @GetMapping(value = {"/users/{id}/delete"})
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }
}
