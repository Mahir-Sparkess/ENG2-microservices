package uk.ac.york.eng2.sm.repositories;

/* protected region imports on begin */
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.sm.domains.UserExt;
import uk.ac.york.eng2.sm.gen.domain.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;
/* protected region imports end */

@Repository
public interface UserRepository extends CrudRepository</* protected region Generic on begin */UserExt, Long/* protected region Generic end */> {

	/* protected region Implementation on begin */
    Optional<UserExt> findByUserId(@NotNull Long id);
	/* protected region Implementation end */
}