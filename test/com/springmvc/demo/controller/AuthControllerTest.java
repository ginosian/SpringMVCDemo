package com.springmvc.demo.controller;

import com.springmvc.demo.services.UserManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by Martha on 6/26/2016.
 */
public class AuthControllerTest {

//    @Resource
//    private WebApplicationContext context;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup(){
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    public void testVle() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/vle").accept(MediaType.ALL))
//                .andExpect(MockMvcResultMatchers.view().name("admin_page"))
//                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/admin_page.jsp"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("aaa"));
//    }


    @Mock
    UserManager userManager;
    @InjectMocks
    AuthController authController;
    MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void root() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(""))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/login"));
    }

    @Test
    public void login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.view().name("login_page"));
    }

}
