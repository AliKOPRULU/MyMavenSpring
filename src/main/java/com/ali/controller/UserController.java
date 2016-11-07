package com.ali.controller;

import com.ali.domain.RegisterValidator;
import com.ali.domain.User;
import com.ali.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * Created by Ali on 25.10.2016.
 */
@Controller
public class UserController {
    private final UserService userService;
    private final RegisterValidator registerValidator;

    @Autowired
    public UserController(UserService userService, RegisterValidator registerValidator) {
        this.userService = userService;
        this.registerValidator = registerValidator;
    }

    @RequestMapping("/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("register", "user", new User());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleRegisterForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "register";
        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping("users")
    public ModelAndView getUserPage() {
        return new ModelAndView("users", "users", userService.getUser());
    }

    @RequestMapping("/users/{id}/items")
    public ModelAndView getUserPage(@PathVariable Long id) {
        if (null == userService.getUserById(id))
            throw new NoSuchElementException("User with id:" + id + "not found");
        else
            return new ModelAndView("userItems", "items", userService.numberOfItemsByType(id));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(new Validator[]{(Validator) registerValidator});

    }

}
