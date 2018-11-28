# specifi_method_resolver
Call the most specific method available in the specified object. 

Say you have a class like this one.

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
    
    // ...
       AuthenticationHandler h = new AuthenticationHandler();
       
       Object k = new SpecificAuthentication();
       
       h.print(k); //Prints Abstract Auth
    // ...
    
Java would call the method that matchs the type of the reference rather than the actuall object. 

This project was a personal challenge to implement an algorithm that always resolves the method with the closes signeture to the actual object. 
