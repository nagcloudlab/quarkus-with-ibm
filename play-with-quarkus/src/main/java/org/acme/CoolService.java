package org.acme;

import javax.inject.Singleton;

@Singleton
public class AmazineService {
    public AmazineService(){
        System.out.println("AmazineService instantiated..");
    }
    String ping() {
        return "amazing";
    }
}
