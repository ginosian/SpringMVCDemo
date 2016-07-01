package com.springmvc.demo.manager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.support.AbstractGenericContextLoader;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Martha on 6/30/2016.
 */
public class ConnectionTest2 {
    public static class TXClass {
        private SessionFactory sessionFactory;

        @Transactional(propagation= Propagation.REQUIRES_NEW)
        public void doJob() {
            Session session = sessionFactory.getCurrentSession();
            // Just a stupid test to make sure everything is setup properly
            Query query = session.createQuery("select task from TaskDTO task join task.userDTO assignee where assignee.id = :id");
            query.setParameter("id", "1");
        }
    }


    @Before
    public void start() throws Exception {

        final BeanDefinitionBuilder bDBuilder = BeanDefinitionBuilder
                .rootBeanDefinition("org.springframework.jms.core.JmsTemplate");

        GenericApplicationContext ctx = new GenericApplicationContext();
        ctx.registerBeanDefinition("com.springmvc.demo",
                bDBuilder.getBeanDefinition());
        ctx.refresh();
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-wiring.xml");
        TXClass tx =(TXClass)ctx.getBean("bean");
        tx.doJob();
        System.out.println("Thread count : " + Thread.activeCount());


    }

    @Test public void test1() throws Exception {Thread.sleep(500);}
    @Test public void test2() throws Exception {Thread.sleep(500);}
    @Test public void test3() throws Exception {Thread.sleep(500);}
    @Test public void test4() throws Exception {Thread.sleep(500);}
    @Test public void test5() throws Exception {Thread.sleep(500);}
    @Test public void test6() throws Exception {Thread.sleep(500);}
    @Test public void test7() throws Exception {Thread.sleep(500);}
    @Test
    public void test8() throws Exception {Thread.sleep(500);}
}
