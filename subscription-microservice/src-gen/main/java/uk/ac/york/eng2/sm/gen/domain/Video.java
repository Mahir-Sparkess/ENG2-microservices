package uk.ac.york.eng2.sm.gen.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;

@Entity
@Serdeable
public class Video {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id;}
	
	public String getTitle() { return title; }
	
	public void setTitle(String title) { this.title = title;}
	

}