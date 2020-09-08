package org.acme;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
//@SessionScoped
//@RequestScoped
public class GreetingService {

    @PostConstruct
    public  void init(){
        System.out.println("GreetingService::init");
    }

    @PreDestroy
    public void cleanup(){
        System.out.println("GreetingService::cleanup");
    }

    public String getGreeting(){
        return "Hello";
    }

}
