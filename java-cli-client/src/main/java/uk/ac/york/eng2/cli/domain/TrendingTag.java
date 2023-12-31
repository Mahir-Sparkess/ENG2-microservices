package uk.ac.york.eng2.cli.domain;

import io.micronaut.serde.annotation.Serdeable;

import javax.validation.constraints.Min;

@Serdeable
public class TrendingTag {

    private Long id;

    private String name;

    private Integer likes;

    private Integer dislikes;

    private Long trendingActivity;

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

    @Override
    public String toString() {
        return "TrendingTag{" +
                "id=" + id +
                ", name='#" + name + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", trendingActivity=" + trendingActivity +
                '}';
    }

    public void setLatestActivity(Long latestActivity) {
        this.latestActivity = latestActivity;
    }
}
