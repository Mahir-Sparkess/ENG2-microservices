package uk.ac.york.eng2.sm.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.util.Collections;
import java.util.Set;

@Serdeable
public class VideoDTO {
    private String title;
    private Set<String> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getTags() {
        return tags != null ? tags : Collections.emptySet();
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
