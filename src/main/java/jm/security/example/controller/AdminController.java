package jm.security.example.controller;

import jm.security.example.model.Role;
import jm.security.example.model.User;
import jm.security.example.service.RoleService;
import jm.security.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//1. добавить отображение ролей на фронте
//        2. добавить возможность выбора роли при добавлении/изменении пользователя
//        3. добавить шифрование паролей
//        4. при добавлении нового пользователя юзер должен связываться с ролью
//        5. переименовать AppConfig чтобы оно отображало суть класса
//        6. перенести UserDetailsService в пакет security
//        7. поправить @GetMapping(value = {"/users", "/"})
//8. исправить удаление пользовател через post запрос
//        9. реализовать FetchType.LAZY


@Controller
@RequestMapping("/admin/")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value ="users")
    public String allUsers(Model model) {
        List<User> users = userService.listUser();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "users/add")
    public String addUserPage(ModelMap model, User user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.listRole());
        return "add";
    }

    @PostMapping(value = "users/add")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(value = "roles") String[] roles) {
        Set<Role> rolesSet = new HashSet<>();
        for (String role : roles) {
            rolesSet.add(roleService.getRoleByName(role));
        }
        user.setRoles(rolesSet);
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/users/{id}/edit")
    public String editUserPage(Model model, @PathVariable("id") Long id ) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.listRole());
        return "edit";
    }

    @PostMapping(value = "/users/{id}/edit")
    public String editUser(@ModelAttribute("user") User user, @PathVariable("id") Long id,
                           @RequestParam(value = "roles") String[] roles) {
        Set<Role> rolesSet = new HashSet<>();
        for (String role : roles) {
            rolesSet.add(roleService.getRoleByName(role));
        }
        user.setRoles(rolesSet);
        userService.editUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = {"/users/{id}/delete"})
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }

}
