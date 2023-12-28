package uk.ac.york.eng2.vm.gen.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;

@Entity
@Serdeable
public class Hashtag {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id;}
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name;}
	

}