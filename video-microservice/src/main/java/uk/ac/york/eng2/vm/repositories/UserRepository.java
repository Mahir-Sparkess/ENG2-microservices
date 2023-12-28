package uk.ac.york.eng2.vm.repositories;

/* protected region imports on begin */
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import uk.ac.york.eng2.vm.domain.*;
import uk.ac.york.eng2.vm.gen.dto.UserDTO;

import javax.validation.constraints.NotNull;
import java.util.Optional;
/* protected region imports end */

@Repository
public interface UserRepository extends CrudRepository</* protected region Generic on begin */UserExt, Long/* protected region Generic end */> {

	/* protected region Implementation on begin */
    Optional<UserExt> findById(@NotNull Long id);
    Optional<UserDTO> findOne(long id);
	/* protected region Implementation end */
}