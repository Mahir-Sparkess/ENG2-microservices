package uk.ac.york.eng2.cli.domain;

import io.micronaut.serde.annotation.Serdeable;

import java.util.Set;

@Serdeable
public class Video {

    private Long id;

    private String title;

    private User uploader;

    private Set<User> viewers;

    private Set<HashTag> tags;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public Set<User> getViewers() {
        return viewers;
    }

    public void setViewers(Set<User> viewers) {
        this.viewers = viewers;
    }

    public Set<HashTag> getTags() {
        return tags;
    }

    public void setTags(Set<HashTag> tags) {
        this.tags = tags;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
