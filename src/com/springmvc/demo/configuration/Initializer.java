package com.springmvc.demo.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created by Martha on 6/16/2016.
 */
/**If any servlet Filter mappings are added after AbstractSecurityWebApplicationInitializer is invoked,
 * they might be accidentally added before springSecurityFilterChain.
 * Unless an application contains Filter instances that do not need to be secured,
 * springSecurityFilterChain should be before any other Filter mappings.
 *  The @Order annotation can be used to help ensure that any WebApplicationInitializer is loaded in a deterministic order.
 */

public class Initializer implements WebApplicationInitializer { //AbstractAnnotationConfigDispatcherServletInitializer is implementer of this interface
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(DataConfiguration.class);

        servletContext.addListener(new ContextLoaderListener(ctx));

        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",
                new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

        FilterRegistration.Dynamic security = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
        security.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }


}
