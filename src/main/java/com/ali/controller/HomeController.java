package com.ali.controller;

import com.ali.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ali on 22.10.2016.
 */
@Controller
public class HomeController {

    //    @RequestMapping("/")
    @RequestMapping(value = {"/", "home"})
    public ModelAndView getHomePage(User user) {//@AuthenticationPrincipal
        return new ModelAndView("home", "user", user);
    }

}
