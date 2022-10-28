package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.dto.CredentialsDTO;
import com.demo.spring.entity.Credentials;

class CredentialsDTOTest {
@Test
void testCredentials()
{
	CredentialsDTO credentialsDTO = new CredentialsDTO();
	Credentials credentials=new Credentials("admin","admin");
	credentialsDTO.setPassword("admin");
	credentialsDTO.setUserName("admin");
	
	assertEquals("admin", credentialsDTO.getPassword());
	assertEquals("admin", credentialsDTO.getPassword());
	
}
}
