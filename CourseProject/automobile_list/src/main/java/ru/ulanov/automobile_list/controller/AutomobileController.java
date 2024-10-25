package ru.ulanov.automobile_list.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.ulanov.automobile_list.entity.Automobile;
import ru.ulanov.automobile_list.entity.User;
import ru.ulanov.automobile_list.repository.AutomobileRepo;
import ru.ulanov.automobile_list.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Controller
public class AutomobileController {
    private final AutomobileRepo automobileRepo;
    private final UserService userService;

    @Autowired
    public AutomobileController(AutomobileRepo automobileRepo, UserService userService) {
        this.automobileRepo = automobileRepo;
        this.userService = userService;
    }
    @GetMapping("/addAutomobileForm")
    public ModelAndView addAutomobileForm() {
        ModelAndView mav = new ModelAndView("add-automobile-form");
        Automobile automobile = new Automobile();
        mav.addObject("automobile", automobile);

        return mav;
    }

    @PostMapping("/saveAutomobile")
    public String showUpdateForm(@ModelAttribute Automobile automobile) {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null)
            automobile.setUser(currentUser);
        automobileRepo.save(automobile);

        return "redirect:/list";
    }

    @PostMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long automobileId) {
        ModelAndView mav = new ModelAndView("add-automobile-form");
        Optional<Automobile> optionalAutomobile = automobileRepo.findById(automobileId);
        Automobile automobile = new Automobile();
        if (optionalAutomobile.isPresent())
            automobile = optionalAutomobile.get();
        mav.addObject("automobile", automobile);

        return mav;
    }

    @GetMapping("/deleteAutomobile")
    public String deleteAutomobile(@RequestParam Long automobileId) {
        automobileRepo.deleteById(automobileId);

        return "redirect:/list";
    }
}
