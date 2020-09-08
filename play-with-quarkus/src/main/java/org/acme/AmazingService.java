package org.acme;

import javax.inject.Singleton;

@Singleton
public class AmazingService {
    public AmazingService(){
        System.out.println("AmazineService instantiated..");
    }
    String ping() {
        return "amazing";
    }
}
