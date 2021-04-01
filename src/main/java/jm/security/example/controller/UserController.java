package jm.security.example.controller;

import jm.security.example.model.User;
import jm.security.example.service.RoleService;
import jm.security.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping()
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/user")
    public String getUserPage(Principal principal, Model model) {
        String name = principal.getName();
        User user = userService.getUserByName(name);
        model.addAttribute("user", user);
        model.addAttribute("role", user.getRoles());
        return "user";
    }
}
