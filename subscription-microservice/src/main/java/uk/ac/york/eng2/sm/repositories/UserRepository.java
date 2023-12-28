package uk.ac.york.eng2.sm.repositories;

/* protected region imports on begin */
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import uk.ac.york.eng2.sm.domain.*;
/* protected region imports end */

@Repository
public interface UserRepository extends CrudRepository</* protected region Generic on begin */User, Long/* protected region Generic end */> {

	/* protected region Implementation on begin */
	/* protected region Implementation end */
}