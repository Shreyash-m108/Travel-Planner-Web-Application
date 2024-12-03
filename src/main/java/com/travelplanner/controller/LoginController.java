package com.travelplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import com.travelplanner.model.User;
import com.travelplanner.service.UserService;

@Controller
public class LoginController {
    
    private final UserService userService;
    
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    @PostMapping("/login")
    public String processLogin(@RequestParam String usernameOrEmail, 
                             @RequestParam String password,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        User user = userService.findByUsernameOrEmail(usernameOrEmail);
        
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            return "redirect:/trips";
        }
        
        redirectAttributes.addFlashAttribute("error", "Invalid username or password");
        return "redirect:/login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
} 