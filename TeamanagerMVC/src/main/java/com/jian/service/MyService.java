package com.jian.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    public MyService() {
        System.out.println("bean MyService");
    }

    public String process(String name) {
        return name.toLowerCase();
    }
}
