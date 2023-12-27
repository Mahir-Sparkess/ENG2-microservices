package uk.ac.york.eng2.sm.gen.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;

@Entity
@Serdeable
public class VideoGen {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	
	private Set<Hashtag> tags;
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id;}
	
	public String getTitle() { return title; }
	
	public void setTitle(String title) { this.title = title;}
	
	public Set<Hashtag> getTags() { return tags; }
	
	public void setTags(Set<Hashtag> tags) { this.tags = tags;}
	

}