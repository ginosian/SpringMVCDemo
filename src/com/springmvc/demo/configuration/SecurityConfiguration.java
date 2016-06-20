package com.springmvc.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Martha on 6/17/2016.
 */
@Configuration
@EnableWebMvc
@Order
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // region In-memory authentication

    /** @param http configures HttpSecurity which allows configuring web based security for specified http requests**/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/").permitAll() // not secured, anyone can access
                .antMatchers("/admin/**").hasRole("ADMIN") //can only be accessed by someone who have ADMIN role
                .antMatchers("/common/**").hasRole("USER")
                .antMatchers("/tuft/**").hasRole("DBA")//can only be accessed by someone who have both USER roles
                .and().formLogin().loginPage("/login").successHandler(new SecuritySuccessHandler())//  creates a custom login page with ‘/login’ url
                .usernameParameter("username").passwordParameter("password") // will accept username and password Http request parameters
                .and().exceptionHandling().accessDeniedPage("/gggg") // will catch all 403 [http access denied] exceptions and display our user defined page
                .and().csrf(); //  active by default, stands for Cross Site Request Forgery, a Synchronizer Token Pattern where each request requires, in addition to our session cookie, a randomly generated token as an HTTP parameter

//        http.authorizeRequests()
//                .antMatchers("/login", "/").permitAll() // not secured, anyone can access
//                .antMatchers("/admin/**").access("hasRole('ADMIN')") //can only be accessed by someone who have ADMIN role
//                .antMatchers("/common/**").hasRole("USER") //can only be accessed by someone who have both USER roles
//                .and().formLogin().loginPage("/login").loginProcessingUrl("/login").successHandler(new SecuritySuccessHandler())
//                .usernameParameter("username").passwordParameter("password") // will accept username and password Http request parameters
//                .and().csrf() //  active by default, stands for Cross Site Request Forgery, a Synchronizer Token Pattern where each request requires, in addition to our session cookie, a randomly generated token as an HTTP parameter
//                .and().exceptionHandling().accessDeniedPage("/tuft");
    }

    /* setup in XML configuration format
    <http auto-config="true" >
    <intercept-url pattern="/" access="permitAll" />
    <intercept-url pattern="/main_page" access="permitAll" />
    <intercept-url pattern="/admin**" access="hasRole('ADMIN')" />
    <intercept-url pattern="/common**" access="hasRole('USER')" />
    <form-login  authentication-failure-url="/login" />
     <form-login  login-page="/login" username-parameter="ssoId" password-parameter="password"/>
     <csrf/>
    </http> */

//    @Bean
//    public SavedRequestAwareAuthenticationSuccessHandler
//    savedRequestAwareAuthenticationSuccessHandler() {
//
//        SavedRequestAwareAuthenticationSuccessHandler auth
//                = new SavedRequestAwareAuthenticationSuccessHandler();
//
//        auth.setTargetUrlParameter("targetUrl");
//        return auth;
//    }

    /** @param auth  configures AuthenticationManagerBuilder with user credentials and allowed roles.
     *               This AuthenticationManagerBuilder creates AuthenticationManager which is responsible for processing any authentication request.
     *               In-memory authentication is applied.
     **/
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("a@a.com").password("aa").roles("USER");
        auth.inMemoryAuthentication().withUser("b@b.com").password("bb").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("c@c.com").password("cc").roles("DBA");
    }

    /*
    setup in XML configuration format
     <authentication-manager >
        <authentication-provider>
            <user-service>
                <user name="a@a.com"  password="aa"  authorities="ROLE_USER" />
                <user name="b@b.com" password="bb" authorities="ROLE_ADMIN" />
            </user-service>
        </authentication-provider>
    </authentication-manager>
    * */



    // endregion

//        public SecurityConfiguration() {
//        super(false);
//    }
//
//    @Autowired
//    @Qualifier("customUserDetailsService")
//    UserDetailsService userDetailsService;
//
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) {
////        try {
////            auth
////                    .inMemoryAuthentication()
////                    .withUser("user")  // #1
////                    .password("password")
////                    .roles("USER")
////                    .and()
////                    .withUser("admin") // #2
////                    .password("password")
////                    .roles("ADMIN");
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
////    @Override
////    public void configure(WebSecurity web) throws Exception {
////        web
////                .ignoring()
////                .antMatchers("/resources/**"); // #3
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasRole("USER")
//                .and().formLogin().loginPage("/login") // <security:form-login login-page="/login" default-target-url="/main"
//                .usernameParameter("login").passwordParameter("password")
//                .and().csrf()
//                .and().exceptionHandling().accessDeniedPage("/project_admin_page");
//    }
//
//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }


}
