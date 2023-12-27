package uk.ac.york.eng2.hrm.gen.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;

@Entity
@Serdeable
public class SubscriberGen {
	
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id;}
	

}