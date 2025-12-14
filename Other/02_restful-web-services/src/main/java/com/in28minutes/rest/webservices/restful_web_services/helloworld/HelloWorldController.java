package com.in28minutes.rest.webservices.restful_web_services.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello-world")
//    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String heloWrold() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean heloWroldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world-path-variable/{name}")
    public HelloWorldBean heloWroldPathVariable(@PathVariable String name) {
        return new HelloWorldBean("Hello World " + name);
    }
}
