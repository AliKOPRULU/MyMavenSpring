package com.ali.controller;

/**
 * Created by Ali on 26.10.2016.
 */

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {

    @PreAuthorize("isAnonymous()")//@PreAuthorize anotasyonuyla, getLoginPage methodunun sadece giriş yapmamış kullanıcılar için invoke edileceğini belirttik.
    @RequestMapping(value = "/login")
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {//@RequestParam olarak doğrudan String kullansaydık, ‘error is not present’ hatası alacaktık.
        return new ModelAndView("login", "error", error);
    }

}
