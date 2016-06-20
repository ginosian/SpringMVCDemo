package com.springmvc.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Martha on 6/16/2016.
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.springmvc.demo")
//@SpringBootApplication(exclude={HibernateJpaAuto})
//@PropertySource("classpath:application.properties")
//@ImportResource("classpath:spring-security.xml")
    public class DataConfiguration extends WebMvcConfigurerAdapter {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "";
    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:mysql://localhost:3306/spring_mvc_demo_db";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "root";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.SQLServer2005Dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "true";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.springmvc.demo.dto";

    @Autowired
    @Resource
    private Environment env;


    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
        dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
        dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
        dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.springmvc.demo");
        sessionFactoryBean.setHibernateProperties(hibProperties());
        return sessionFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login_page");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

//    @Order
//    @Bean
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/home").setViewName("login_page");
//        registry.addViewController("/").setViewName("login_page");
//        registry.addViewController("/hello").setViewName("login_page");
//        registry.addViewController("/login").setViewName("login_page");
//    }

    //     equivalents for <mvc:resources/> tags
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("/resources/image/").setCachePeriod(0);
    }

    // equivalent for <mvc:default-servlet-handler/> tag
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
