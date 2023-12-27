package uk.ac.york.eng2.vm.gen.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class VideoDTOGen {

	private String title;
	
	private User uploader;
	
	private Set<Hashtag> tags;
	
	public String getTitle() { return title; }
	
	public void setTitle(String title) { this.title = title;}
	
	public User getUploader() { return uploader; }
	
	public void setUploader(User uploader) { this.uploader = uploader;}
	
	public Set<Hashtag> getTags() { return tags; }
	
	public void setTags(Set<Hashtag> tags) { this.tags = tags;}
	

}