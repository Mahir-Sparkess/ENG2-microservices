package uk.ac.york.eng2.hrm.domains;

import io.micronaut.serde.annotation.Serdeable;
import uk.ac.york.eng2.hrm.gen.domain.Subscriber;

import javax.persistence.*;
import java.util.Set;

@Serdeable
@Entity
public class SubscriberExt extends Subscriber {
    private Long userId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "subscribed_tags")
    private Set<HashtagExt> subscriptions;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<HashtagExt> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<HashtagExt> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
