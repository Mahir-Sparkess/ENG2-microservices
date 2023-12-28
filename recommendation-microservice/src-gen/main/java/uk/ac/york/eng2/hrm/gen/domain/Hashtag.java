package uk.ac.york.eng2.hrm.gen.domain;

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
	
	@Column(nullable = false)
	private Long likes;
	
	@Column(nullable = false)
	private Long dislikes;
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id;}
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name;}
	
	public Long getLikes() { return likes; }
	
	public void setLikes(Long likes) { this.likes = likes;}
	
	public Long getDislikes() { return dislikes; }
	
	public void setDislikes(Long dislikes) { this.dislikes = dislikes;}
	

}