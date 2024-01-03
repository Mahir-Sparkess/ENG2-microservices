package uk.ac.york.eng2.sm.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import uk.ac.york.eng2.sm.gen.domain.Video;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Serdeable
public class VideoExt extends Video {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="video_viewers")
    @JsonIgnore
    private Set<UserExt> viewers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "video_tags")
    @JsonIgnore
    private Set<HashtagExt> tags;

    private Long videoId;

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
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
