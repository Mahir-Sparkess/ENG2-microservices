package uk.ac.york.eng2.vm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Serdeable
public class VideoExt extends uk.ac.york.eng2.vm.gen.domain.Video {

    @ManyToOne
    private UserExt uploader;

    @ManyToMany
    @JoinTable(name="video_viewers")
    @JsonIgnore
    private Set<UserExt> viewers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "video_tags")
    @JsonIgnore
    private Set<HashtagExt> tags;

    public UserExt getUploader() {
        return uploader;
    }

    public void setUploader(UserExt uploader) {
        this.uploader = uploader;
    }

    public Set<UserExt> getViewers() {
        return viewers;
    }

    public void setViewers(Set<UserExt> viewers) {
        this.viewers = viewers;
    }

    public Set<HashtagExt> getTags() {
        return tags;
    }

    public void setTags(Set<HashtagExt> tags) {
        this.tags = tags;
    }
}
