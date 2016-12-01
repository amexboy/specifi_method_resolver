package com.amanu.classresolver;

/**
 * @author by Amanu on November 12, 2016.
 */
public class Main {
    public static void main(String[] args) {

        DefaultResolver<Authentication, Void> defaultResolver = new DefaultResolver<>(new AuthenticationHandler());

        defaultResolver.resolve(new SpecificAuthentication());
        
        
        defaultResolver.resolve(new BasicAuthentication());

    }
}
