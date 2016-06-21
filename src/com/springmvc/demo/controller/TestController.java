package com.springmvc.demo.controller;

import org.springframework.stereotype.Controller;

/**
 * Created by Martha on 6/14/2016.
 */
@Controller
public class TestController {

//    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
//    public ModelAndView login(
//            @RequestParam(value = "error", required = false) String error,
//            @RequestParam(value = "logout", required = false) String logout) {
//
//        ModelAndView model = new ModelAndView();
//        if (error != null) {
//            model.addObject("error", "Invalid username and password!");
//        }
//
//        if (logout != null) {
//            model.addObject("msg", "You've been logged out successfully.");
//        }
//        model.setViewName("login");
//
//        return model;
//    }

//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String adminPage(ModelMap model) {
//        return "admin_page";
//    }

//    @RequestMapping(value = "/common", method = RequestMethod.GET)
//    public String commonPage(ModelMap model) {
//        return "common_page";
//    }
//
//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login?logout";
//    }
//
//    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
//    public String accessDeniedPage(ModelMap model) {
//        model.addAttribute("user", getPrincipal());
//        return "project_admin_page";
//    }
//
//    private String getPrincipal(){
//        String userName = null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            userName = ((UserDetails)principal).getUsername();
//        } else {
//            userName = principal.toString();
//        }
//        return userName;
//    }
//
//
//
//
//
//

//
//    @Autowired
//    UserManager userManager;
//
//    //    @Autowired
////    ProjectManager projectManager;
////
////
////    @RequestMapping("/")
////    public String home() {
////        return "login_page";
////    }
////
//    @RequestMapping("/tuft")
//    public ModelAndView test(){
////        ProjectDTO project = new ProjectDTO();
////        project.set("bla", "mla");
////        projectManager.addProject(project);
////        return new ModelAndView("admin_page");
//
//        UserDTO userDTO = new UserDTO();
//        RoleDTO roleDTO = new RoleDTO();
//        roleDTO.set("ADMIN");
//        Set<RoleDTO> roles = new HashSet<>();
//        roles.add(roleDTO);
//        userManager.addRole(roleDTO);
//        userDTO.set("a@a.com", "aa", "Vle", true, roles);
//        userManager.addUser(userDTO);
//
//
////        UserDTO userDTO2 = new UserDTO();
////        RoleDTO roleDTO2 = new RoleDTO();
////        roleDTO.set("USER");
////        Set<RoleDTO> roles = new HashSet<>();
////        roles.add(roleDTO);
////        userManager.addRole(roleDTO);
////        userDTO.set("e@e.com", "ee", "Ahot", true, roles);
////        userManager.addUser(userDTO);
//
//        ModelAndView modelAndView = new ModelAndView("project_admin_page");
//        return modelAndView;
//    }
////
////    @RequestMapping("/read")
////    public ModelAndView readUser(@RequestParam("id") long userId) {
////        UserDTO userDTO = userManager.getUserById(userId);
////        ModelAndView modelAndView = new ModelAndView("index");
////        modelAndView.addObject("id", userDTO.getId());
////        modelAndView.addObject("name", userDTO.getName());
////        modelAndView.addObject("username", userDTO.getUsername());
////        modelAndView.addObject("password", userDTO.getPassword());
////        modelAndView.addObject("roles", userDTO.getUserRoles());
////
////        return modelAndView;
////    }
////
//////
//////    @RequestMapping("/muft")
//////    public ModelAndView testmuft(@RequestParam("id") long id){
//////        UserDTO user = userManager.getUserById(id);
//////        ModelAndView modelAndView = new ModelAndView("project_admin_page");
//////        modelAndView.addObject("id", id);
//////        modelAndView.addObject("name", user.getName());
//////        modelAndView.addObject("role", user.getRoleDTO().getRole());
//////        return modelAndView;
//////    }
//////
//////    @Secured({"ROLE_REGULAR_USER","ROLE_ADMIN"})
//////    @RequestMapping(value="/common", method = RequestMethod.GET)
//////    public String common(ModelMap model) {
//////
//////        return "common_page";
////
////    }
////    @Secured("ROLE_ADMIN")
////    @RequestMapping(value="/admin", method = RequestMethod.GET)
////    public String admin(ModelMap model) {
////
////        return "admin_page";
////
////    }
}

