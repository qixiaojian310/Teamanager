package com.jian.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextBuilder {

    private static ApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    }

    public static ApplicationContext getContext(){
        return context;
    }
}
