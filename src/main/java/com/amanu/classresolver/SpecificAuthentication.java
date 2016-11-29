package com.amanu.classresolver;

public class SpecificAuthentication extends BasicAuthentication{

    @Override
    public void authenticate(){
        System.out.println("Authenticating using specifc");
    }

}
