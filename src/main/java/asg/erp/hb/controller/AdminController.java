package asg.erp.hb.controller;

import asg.erp.hb.entity.Role;
import asg.erp.hb.entity.User;
import asg.erp.hb.repository.RoleRepository;
import asg.erp.hb.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public AdminController(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    // List users
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "admin/users";
    }

    // show create user form
    @GetMapping("/users/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepo.findAll());
        return "admin/user_form";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute User user) {
        // encode password before saving — do this in service ideally
        userRepo.save(user);
        return "redirect:/admin/users";
    }

    // Roles
    @GetMapping("/roles")
    public String listRoles(Model model) {
        model.addAttribute("roles", roleRepo.findAll());
        return "admin/roles";
    }

    @GetMapping("/assign")
    public String assignRoleForm(Model model) {
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("roles", roleRepo.findAll());
        return "admin/assign";
    }

    @PostMapping("/assign")
    public String assignRole(@RequestParam Long userId, @RequestParam Long roleId) {
        User user = userRepo.findById(userId).orElseThrow();
        Role role = roleRepo.findById(roleId).orElseThrow();
        user.getRoles().add(role);
        userRepo.save(user);
        return "redirect:/admin/users";
    }
}

