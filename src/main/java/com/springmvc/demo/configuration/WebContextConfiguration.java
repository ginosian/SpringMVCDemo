package com.springmvc.demo.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by Martha on 6/16/2016.
 */


/**
 * Configures Web application context and hibernate properties.
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.springmvc.demo")
@PropertySource(value = "classpath:config.properties")
public class WebContextConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    @Resource
    private Environment env; // Environment object is needed to get db properties


    @Bean
    public DataSource dataSource() {
        /**  Apache's DBCP's BasicDataSource connection pool bean */
//        BasicDataSource dataSource = new BasicDataSource();
////        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("database_driver"));
//        dataSource.setUrl(env.getProperty("database_url"));
//        dataSource.setUsername(env.getProperty("database_username"));
//        dataSource.setPassword(env.getProperty("database_password"));
//        return dataSource;
//
        /** C3P0's ComboPooledDataSource connection pool bean */
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(env.getProperty("database_driver")); //loads the jdbc driver
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(env.getProperty("database_url"));
        dataSource.setUser(env.getProperty("database_username"));
        dataSource.setPassword(env.getProperty("database_password"));
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
        //log settings
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");

//        //driver settings
//        properties.put("hibernate.connection.driver_class", env.getProperty("database_driver"));
//        properties.put("hibernate.connection.url", env.getProperty("database_url"));
//        properties.put("hibernate.connection.username", env.getProperty("database_username"));
//        properties.put("hibernate.connection.password", env.getProperty("database_password"));

        //c3p0 settings
        properties.put("hibernate.c3p0.min_size", 1);
        properties.put("hibernate.c3p0.max_size", 5);
        properties.put("hibernate.c3p0.acquire_increment", 3); // will try to acquire when the pool is exhausted

        //isolation level
        properties.setProperty("hibernate.connection.isolation", String.valueOf(Connection.TRANSACTION_SERIALIZABLE));
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
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("/resources/image/").setCachePeriod(0);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
