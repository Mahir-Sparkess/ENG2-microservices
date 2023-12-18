package uk.ac.york.eng2.vm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Serdeable
public class Video {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToMany
    @JsonIgnore
    private Set<User> viewers;

    @ManyToMany
    @JsonIgnore
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
}
