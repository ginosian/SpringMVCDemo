package com.springmvc.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by Martha on 6/17/2016.
 */

@Configuration
@EnableWebMvc
@Order
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    @Qualifier("customUsrService")
    UserDetailsService userDetailsService;

    @Autowired
    DataSource dataSource;

    /** @param http configures HttpSecurity which allows configuring web based security for specified http requests**/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/").permitAll() // not secured, anyone can access
                .antMatchers("/admin/**").hasRole("ADMIN") //can only be accessed by someone who have ADMIN role
                .antMatchers("/common/**").hasRole("USER") //can only be accessed by someone who have USER role
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).invalidSessionUrl("/login")
                .and().rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(120)
                .and().formLogin().loginPage("/login").successHandler(new SecuritySuccessHandler())//  creates a custom login page with "/login" url
                .usernameParameter("username").passwordParameter("password") // will accept username and password Http request parameters
                .and().exceptionHandling() // will catch all 403 [http access denied] exceptions and display on user defined page
                .and().csrf(); //  active by default, stands for Cross Site Request Forgery, a Synchronizer Token Pattern where each request requires, in addition to our session cookie, a randomly generated token as an HTTP parameter
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
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


    /** @param auth  configures AuthenticationManagerBuilder with user credentials and allowed roles.
     *               This AuthenticationManagerBuilder creates AuthenticationManager which is responsible for processing any authentication request.
     **/
     @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
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
}
