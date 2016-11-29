package com.amanu.classresolver;

public class BasicAuthentication extends Authentication{

    @Override
    public void authenticate(){
        System.out.println("Authenticating using basic!");
    }

}
