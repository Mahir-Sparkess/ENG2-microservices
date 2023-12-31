package uk.ac.york.eng2.cli.dto;

import io.micronaut.serde.annotation.Serdeable;
import uk.ac.york.eng2.cli.domain.HashTag;

import java.util.Set;

@Serdeable
public class VideoDTO {

    private String title;

    private Set<String> tags;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
