package uk.ac.york.eng2.hrm.repositories;

/* protected region imports on begin */
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.hrm.domains.SubscriberExt;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/* protected region imports end */

@Repository
public interface SubscriberRepository extends CrudRepository</* protected region Generic on begin */SubscriberExt, Long/* protected region Generic end */> {

	/* protected region Implementation on begin */
    Optional<SubscriberExt> findByUserId(@NotNull Long userId);
	/* protected region Implementation end */
}