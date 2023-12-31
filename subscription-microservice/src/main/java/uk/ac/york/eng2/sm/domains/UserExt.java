package uk.ac.york.eng2.sm.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import uk.ac.york.eng2.sm.gen.domain.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Serdeable
public class UserExt extends User {
    @ManyToMany(mappedBy = "viewers", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<VideoExt> viewed;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_subscribed_tags")
    @JsonIgnore
    private Set<HashtagExt> subscriptions;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<VideoExt> getViewed() {
        return viewed;
    }

    public void setViewed(Set<VideoExt> viewed) {
        this.viewed = viewed;
    }

    public Set<HashtagExt> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<HashtagExt> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
