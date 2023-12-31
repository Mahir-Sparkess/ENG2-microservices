package uk.ac.york.eng2.cli.domain;

import io.micronaut.serde.annotation.Serdeable;

import java.util.Set;

@Serdeable
public class User {

    private Long id;

    private String username;

    private Set<Video> viewed;

    private Set<Video> uploads;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Video> getViewed() {
        return viewed;
    }

    public void setViewed(Set<Video> viewed) {
        this.viewed = viewed;
    }

    public Set<Video> getUploads() {
        return uploads;
    }

    public void setUploads(Set<Video> uploads) {
        this.uploads = uploads;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
