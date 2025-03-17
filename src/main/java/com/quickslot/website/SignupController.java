package com.quickslot.website;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quickslot.website.DTO.user;

@Controller
public class SignupController {

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

        user testUser = user.builder()
            .username(username)
            .email(email)
            .password(passwordEncoder.encode((CharSequence)password))
            .build();
        
        System.out.println(testUser);


        model.addAttribute("message", "User registered successfully!");
        return "signup";

        

    }

}
