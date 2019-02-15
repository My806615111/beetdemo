package com.allen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author allen
 * @createdate 2019-02-14-16:54
 */
@RestController
public class Demo {
    @RequestMapping("/demo1")
    public String demo1(){
        System.out.println("aaaa");
        System.out.println("bbb");
        return "hello,world";
    }
}
