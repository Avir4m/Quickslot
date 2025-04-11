package com.quickslot.website.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quickslot.website.Entities.User;
import com.quickslot.website.Repositories.UserRepository;

@Controller
public class SignupController {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
    
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String handleSignUp(
        @RequestParam("username") String username,
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        Model model
    ) {
        if (username == "" || email == "" || password == "") {
            model.addAttribute("error", "Please fill all the required fields");
            return "signup";
        }

        if (userRepository.existsByEmail(email)) {
            model.addAttribute("error", "Email already exists");
            return "signup";
        }

        User newUser = new User(username, email, passwordEncoder.encode(password));
        userRepository.save(newUser);

        model.addAttribute("message", "User registered successfully!");
        return "signup";
    }

}
