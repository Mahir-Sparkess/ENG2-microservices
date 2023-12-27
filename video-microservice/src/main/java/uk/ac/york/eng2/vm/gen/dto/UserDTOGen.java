package uk.ac.york.eng2.vm.gen.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class UserDTOGen {

	private String username;
	
	public String getUsername() { return username; }
	
	public void setUsername(String username) { this.username = username;}
	

}