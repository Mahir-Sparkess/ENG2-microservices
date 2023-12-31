package uk.ac.york.eng2.thm.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Serdeable
public class HashTag {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Min(0)
    @Column(nullable = false)
    private Integer likes;

    @Min(0)
    @Column(nullable = false)
    private Integer dislikes;

    @Min(0)
    @Column(nullable = false)
    private Long trendingActivity;

    @Column(nullable = false)
    private Long latestActivity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @Min(0) Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public @Min(0) Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Long getTrendingActivity() {
        return trendingActivity;
    }

    public void setTrendingActivity(Long trendingViews) {
        this.trendingActivity = trendingViews;
    }

    public Long getLatestActivity() {
        return latestActivity;
    }

    public void setLatestActivity(Long latestActivity) {
        this.latestActivity = latestActivity;
    }
}
