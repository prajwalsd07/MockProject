package com.demo.spring.entity;



import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;



public class CredentialsTest {
    
    @Test
    void testCredentials() {
        Credentials credentials=new Credentials();
        credentials.setUserName("receptionist");
        credentials.setPassword("receptionist");
        
        assertEquals("receptionist",credentials.getUserName());
        assertEquals("receptionist",credentials.getPassword());
        
        
        
        
    }



}