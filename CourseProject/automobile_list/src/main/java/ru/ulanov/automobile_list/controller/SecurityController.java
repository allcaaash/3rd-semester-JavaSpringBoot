package ru.ulanov.automobile_list.controller;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.ulanov.automobile_list.dto.UserDto;
import ru.ulanov.automobile_list.entity.Automobile;
import ru.ulanov.automobile_list.entity.Role;
import ru.ulanov.automobile_list.entity.User;
import ru.ulanov.automobile_list.repository.AutomobileRepo;
import ru.ulanov.automobile_list.repository.RoleRepo;
import ru.ulanov.automobile_list.repository.UserRepo;
import ru.ulanov.automobile_list.service.UserService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class SecurityController {
    private final UserService userService;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AutomobileRepo automobileRepo;

    @Autowired
    private RoleRepo roleRepo;

    public SecurityController(UserService userService) { this.userService = userService; }

    @GetMapping("/index")
    public String home() { return "index"; }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);

        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result. recordFieldValue("email", null,
                    "A user with this email address already exists.");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);

            return "/register";
        }

        userService.saveUser(userDto);

        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/list")
    public ModelAndView getAllAutomobiles(Principal principal) {
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("list-automobiles");

        User currentUser = userService.findUserByEmail(principal.getName());

        Role userRole = currentUser.getRoles().get(0);
        if (userRole.getName().equals("ROLE_ADMIN"))
            mav.addObject("automobiles", automobileRepo.findAll());
        else
            mav.addObject("automobiles", automobileRepo.findByUserId(currentUser.getId()));

        return mav;
    }

    @PostMapping("/editUserRole")
    public String editUserRole(@RequestParam("userEmail") String email,
                               @RequestParam("newRole") String newRole) {
        // Логика обновления роли пользователя
        if (userService.updateUserRole(email, newRole))
            return "redirect:/users";
        else
            return "redirect:/index";
    }

    @GetMapping("/about")
    public String about() { return "about"; }
}
