package org.acme;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CoolService {
    public CoolService(){
        System.out.println("CoolService instantiated..");
    }
    String ping() {
        return "cool";
    }
}
