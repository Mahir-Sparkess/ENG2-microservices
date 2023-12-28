package uk.ac.york.eng2.vm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Serdeable
public class HashtagExt extends uk.ac.york.eng2.vm.gen.domain.Hashtag {

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<VideoExt> tagged;

    public Set<VideoExt> getTagged() {
        return tagged;
    }

    public void setTagged(Set<VideoExt> tagged) {
        this.tagged = tagged;
    }
}
