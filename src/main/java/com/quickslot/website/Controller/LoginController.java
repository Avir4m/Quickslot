package com.quickslot.website.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quickslot.website.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
    
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        Model model) {
        // Check if email exists
        if (!userRepository.existsByEmail(email)) {
            model.addAttribute("error", "No email found");
            return "login";
        }

        // Check if password is correct
        if (!passwordEncoder.matches(password, userRepository.findByEmail(email).getPassword())) { 
            model.addAttribute("error", "Wrong password");
            return "login";
        }


        // Redirect to home
        model.addAttribute("success", "Login successful");
        return "redirect:/home"; 
    }
    
}
