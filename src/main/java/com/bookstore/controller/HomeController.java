package com.bookstore.controller;

import com.bookstore.domain.User;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/newAccount")
    public String newAccount(Model model, Locale locale, @RequestParam("token") String token) {
        PasswordResetToken passwordResetToken = userService.getPasswordResetToken(token);
        if (passwordResetToken == null) {
            String message = "Invalid Token";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        }
        User user = passwordResetToken.getUser();
        String username = user.getUsername();
        
        model.addAttribute("classActiveNewAccount", true);
        return "myAccount";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword(Model model) {
        model.addAttribute("classActiveForgetPassword", true);
        return "myAccount";
    }
}
