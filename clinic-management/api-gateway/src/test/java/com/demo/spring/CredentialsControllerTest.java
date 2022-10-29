package com.demo.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.demo.spring.entity.Credentials;
import com.demo.spring.util.Message;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CredentialsControllerTest {

	@LocalServerPort
	int port;
	
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
    void testFinduserFailure() throws Exception {
        Credentials credentials = new Credentials("hi", "hi");
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Credentials> req = new HttpEntity<>(credentials, headers);
        ResponseEntity<Message> resp2 = testRestTemplate.exchange("http://localhost:"+port+"/login/find",HttpMethod.POST,req,Message.class);
        Assertions.assertEquals("User Not Found",resp2.getBody().getStatus());
    }
	
	@Test
    void testFinduserSuccess() throws Exception {
        Credentials credentials = new Credentials("prajwal", "4321");
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Credentials> req = new HttpEntity<>(credentials, headers);
        ResponseEntity<Message> resp2 = testRestTemplate.exchange("http://localhost:"+port+"/login/find",HttpMethod.POST,req,Message.class);
        Assertions.assertEquals("User Found",resp2.getBody().getStatus());
    }
	
	@Test
    void testupdatePasswordSuccess() throws Exception {
        Credentials credentials = new Credentials("prajwal", "4321");
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Credentials> req = new HttpEntity<>(credentials, headers);
        ResponseEntity<Message> resp2 = testRestTemplate.exchange("http://localhost:"+port+"/login/update",HttpMethod.PATCH,req,Message.class);
        Assertions.assertEquals("User Updated",resp2.getBody().getStatus());
    }
	
	@Test
    void testupdatePasswordFailure() throws Exception {
        Credentials credentials = new Credentials("prajwa", "4321");
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Credentials> req = new HttpEntity<>(credentials, headers);
        ResponseEntity<Message> resp2 = testRestTemplate.exchange("http://localhost:"+port+"/login/update",HttpMethod.PATCH,req,Message.class);
        Assertions.assertEquals("User Not Found",resp2.getBody().getStatus());
    }
}