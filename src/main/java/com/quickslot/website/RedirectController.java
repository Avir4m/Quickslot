package com.quickslot.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RedirectController {

    @RequestMapping("/")
    public String redirectToSignUp() {
        return "redirect:/signup";
    }
    
}
