package com.demo.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.dto.CredentialsDTO;

class CredentialsDTOTest {
@Test
void testCredentials()
{
	CredentialsDTO credentialsDTO = new CredentialsDTO();
	credentialsDTO.setPassword("admin");
	credentialsDTO.setUserName("admin");
	
	assertEquals("admin", credentialsDTO.getPassword());
	assertEquals("admin", credentialsDTO.getPassword());
	
}
}
