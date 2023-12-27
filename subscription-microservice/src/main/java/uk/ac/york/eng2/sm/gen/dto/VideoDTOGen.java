package uk.ac.york.eng2.sm.gen.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class VideoDTOGen {

	private String title;
	
	private Set<Hashtag> tags;
	
	public String getTitle() { return title; }
	
	public void setTitle(String title) { this.title = title;}
	
	public Set<Hashtag> getTags() { return tags; }
	
	public void setTags(Set<Hashtag> tags) { this.tags = tags;}
	

}