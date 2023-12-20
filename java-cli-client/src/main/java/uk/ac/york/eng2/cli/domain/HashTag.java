package uk.ac.york.eng2.cli.domain;

import io.micronaut.serde.annotation.Serdeable;

import java.util.Set;

@Serdeable
public class HashTag {

    private Long id;

    private String name;

    private Set<Video> taggedBy;

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

    public Set<Video> getTaggedBy() {
        return taggedBy;
    }

    public void setTaggedBy(Set<Video> taggedBy) {
        this.taggedBy = taggedBy;
    }

    @Override
    public String toString() {
        return "HashTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
