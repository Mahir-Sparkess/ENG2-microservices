package uk.ac.york.eng2.vm.gen.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;

@Entity
@Serdeable
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String username;
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id;}
	
	public String getUsername() { return username; }
	
	public void setUsername(String username) { this.username = username;}
	

}