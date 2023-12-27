package uk.ac.york.eng2.thm.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;

@Entity
@Serdeable
public class HashTag {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer likes;

    @Column(nullable = false)
    private Integer dislikes;

    @Column(nullable = false)
    private Integer trendingViews;

    @Column(nullable = false)
    private Long latestActivity;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Integer getTrendingViews() {
        return trendingViews;
    }

    public void setTrendingViews(Integer trendingViews) {
        this.trendingViews = trendingViews;
    }

    public Long getLatestActivity() {
        return latestActivity;
    }

    public void setLatestActivity(Long latestActivity) {
        this.latestActivity = latestActivity;
    }
}
