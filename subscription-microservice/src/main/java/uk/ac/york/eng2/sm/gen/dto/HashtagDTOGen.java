package uk.ac.york.eng2.sm.gen.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class HashtagDTOGen {

	private String name;
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name;}
	

}