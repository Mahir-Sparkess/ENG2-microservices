package uk.ac.york.eng2.hrm.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import uk.ac.york.eng2.hrm.gen.domain.Hashtag;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Serdeable
public class HashtagExt extends Hashtag {

    @ManyToMany(mappedBy = "subscriptions", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<SubscriberExt> subscribers;

    public void setSubscribers(Set<SubscriberExt> subscribers) {
        this.subscribers = subscribers;
    }

    public Set<SubscriberExt> getSubscribers() {
        return subscribers;
    }
}
