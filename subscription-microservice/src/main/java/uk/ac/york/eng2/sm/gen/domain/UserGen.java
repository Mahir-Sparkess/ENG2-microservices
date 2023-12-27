package uk.ac.york.eng2.sm.gen.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;

@Entity
@Serdeable
public class UserGen {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id;}
	
	public String getUsername() { return username; }
	
	public void setUsername(String username) { this.username = username;}
	

}