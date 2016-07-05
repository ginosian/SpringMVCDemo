package com.springmvc.demo.controller;

import com.springmvc.demo.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Martha on 6/20/2016.
 */
@Controller
@RequestMapping("")
public class AuthController {

    @Autowired
    UserManager userManager;

    @Autowired
    Environment environment;

    @RequestMapping(value = "")
    public ModelAndView root(){
       userManager.initDefaults();
       return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(Authentication authentication){
        // Check if which module to apply
        if (authentication != null) {
            if (authentication.getAuthorities().iterator().next().toString().equals("ROLE_" + environment.getProperty("role_admin")))
                return new ModelAndView("redirect:/admin");
            else return new ModelAndView("redirect:/common");
        }
        return new ModelAndView("login_page");
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return new ModelAndView("redirect:/login");
    }

}
