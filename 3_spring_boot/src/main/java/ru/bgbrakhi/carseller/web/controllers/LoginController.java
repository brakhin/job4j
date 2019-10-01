package ru.bgbrakhi.carseller.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

/*
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String username(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid username and password.");
        }
        if (logout != null) {
            model.addAttribute("msg", "You have been logged out succesfully.");
//            return "cars";
        }
        return "login";
    }

 */



    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Неверные имя пользователя или пароль.");

        if (logout != null)
            model.addAttribute("message", "Вы успешно вышли.");

        return "login";
    }

}
