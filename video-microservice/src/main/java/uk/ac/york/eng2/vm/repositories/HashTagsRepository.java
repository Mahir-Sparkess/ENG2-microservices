package uk.ac.york.eng2.vm.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.vm.domain.HashTag;
import uk.ac.york.eng2.vm.domain.Video;
import uk.ac.york.eng2.vm.dto.HashTagDTO;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface HashTagsRepository extends CrudRepository<HashTag, Long> {
    @Override
    Optional<HashTag> findById(@NotNull Long id);
    Optional<HashTagDTO> findOne (Long id);
}
