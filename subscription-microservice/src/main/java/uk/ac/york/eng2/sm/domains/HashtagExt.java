package uk.ac.york.eng2.sm.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import uk.ac.york.eng2.sm.gen.domain.Hashtag;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Serdeable
public class HashtagExt extends Hashtag {
    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<VideoExt> tagged;

    @ManyToMany(mappedBy = "subscriptions", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserExt> subscribers;

    public Set<VideoExt> getTagged() {
        return tagged;
    }

    public void setTagged(Set<VideoExt> tagged) {
        this.tagged = tagged;
    }

    public Set<UserExt> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<UserExt> subscribers) {
        this.subscribers = subscribers;
    }
}
