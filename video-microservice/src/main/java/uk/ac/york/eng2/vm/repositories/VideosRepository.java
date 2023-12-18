package uk.ac.york.eng2.vm.repositories;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.vm.domain.Video;
import uk.ac.york.eng2.vm.dto.VideoDTO;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface VideosRepository extends CrudRepository<Video, Long> {
    @Join(value = "viewers", type = Join.Type.LEFT_FETCH)
    @Join(value = "tags", type = Join.Type.LEFT_FETCH)
    @Override
    Optional<Video> findById(@NotNull Long id);

    Optional<VideoDTO> findOne (long id);
}
