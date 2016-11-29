package com.amanu.classresolver;

/**
 * @author by Amanu on November 12, 2016.
 */
public class AuthenticationHandler {

    @Handler
    public void authenticate(Object authentication) {
        System.out.println("Abstract Auth");
    }

    @Handler
    public void print(BasicAuthentication auth) {
        System.out.println("Called basic");
    }

    @Handler
    public void print(SpecificAuthentication auth) {
        System.out.println("Called specific");
    }

}
