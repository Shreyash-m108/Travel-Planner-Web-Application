package com.travelplanner.controller;

import com.travelplanner.dto.UserDTO;
import com.travelplanner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/registeruser")
    public String registerUser(@ModelAttribute UserDTO userDTO) {
        userService.registerUser(userDTO);
        return "redirect:/login";
    }
}