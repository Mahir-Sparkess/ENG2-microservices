package uk.ac.york.eng2.sm.repositories;

/* protected region imports on begin */
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.sm.domains.HashtagExt;
import uk.ac.york.eng2.sm.gen.domain.Hashtag;

import javax.validation.constraints.NotNull;
import java.util.Optional;
/* protected region imports end */

@Repository
public interface HashtagRepository extends CrudRepository</* protected region Generic on begin */HashtagExt, Long/* protected region Generic end */> {

	/* protected region Implementation on begin */
    Optional<HashtagExt> findByName(@NotNull String name);
	/* protected region Implementation end */
}