package com.springmvc.demo.configuration;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Martha on 6/30/2016.
 */
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("=== Session is CREATED ===");
        httpSessionEvent.getSession().setMaxInactiveInterval(120);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("=== Session is DESTROYED ===");
    }
}
