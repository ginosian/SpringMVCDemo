package com.springmvc.demo.controller;

import com.springmvc.demo.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Martha on 6/20/2016.
 */
@Controller
@RequestMapping("")
public class AuthController {

    @Autowired
    UserManager userManager;


    @RequestMapping(value = "")
    public ModelAndView root(){
       userManager.initDefaults();
       return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(){
        return new ModelAndView("login_page");
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request){
        new SecurityContextLogoutHandler().logout(request, null, null);
        return new ModelAndView("redirect:/login");
    }

}
