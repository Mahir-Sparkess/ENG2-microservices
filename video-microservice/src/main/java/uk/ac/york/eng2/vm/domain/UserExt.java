package uk.ac.york.eng2.vm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Serdeable
public class UserExt extends uk.ac.york.eng2.vm.gen.domain.User {

    @ManyToMany(mappedBy = "viewers")
    @JsonIgnore
    private Set<VideoExt> viewed;

    @OneToMany(mappedBy = "uploader")
    @JsonIgnore
    private Set<VideoExt> uploads;

    public Set<VideoExt> getViewed() {
        return viewed;
    }

    public void setViewed(Set<VideoExt> viewed) {
        this.viewed = viewed;
    }

    public Set<VideoExt> getUploads() {
        return uploads;
    }

    public void setUploads(Set<VideoExt> uploads) {
        this.uploads = uploads;
    }
}
