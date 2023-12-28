package uk.ac.york.eng2.vm.repositories;

/* protected region imports on begin */
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import uk.ac.york.eng2.vm.domain.*;
import uk.ac.york.eng2.vm.gen.domain.Hashtag;
import uk.ac.york.eng2.vm.gen.dto.HashtagDTO;

import javax.validation.constraints.NotNull;
import java.util.Optional;
/* protected region imports end */

@Repository
public interface HashtagRepository extends CrudRepository</* protected region Generic on begin */HashtagExt, Long/* protected region Generic end */> {

	/* protected region Implementation on begin */
    @Override
    Optional<HashtagExt> findById(@NotNull Long id);

    Optional<HashtagExt> findByName(@NotNull String name);

    Optional<HashtagDTO> findOne(long id);
	/* protected region Implementation end */
}