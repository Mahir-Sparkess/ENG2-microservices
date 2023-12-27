package uk.ac.york.eng2.hrm.gen.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class HashtagDTOGen {

	private String name;
	
	private Long likes;
	
	private Long dislikes;
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name;}
	
	public Long getLikes() { return likes; }
	
	public void setLikes(Long likes) { this.likes = likes;}
	
	public Long getDislikes() { return dislikes; }
	
	public void setDislikes(Long dislikes) { this.dislikes = dislikes;}
	

}