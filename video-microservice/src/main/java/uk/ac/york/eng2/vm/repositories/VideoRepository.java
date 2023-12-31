package uk.ac.york.eng2.vm.repositories;

/* protected region imports on begin */
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import uk.ac.york.eng2.vm.domain.*;
import uk.ac.york.eng2.vm.dto.VideoDTOExt;

import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.Optional;
/* protected region imports end */

@Repository
public interface VideoRepository extends CrudRepository</* protected region Generic on begin */VideoExt, Long/* protected region Generic end */> {

	/* protected region Implementation on begin */
    @Join(value = "tags", type = Join.Type.LEFT_FETCH)
    @Join(value = "viewers", type = Join.Type.LEFT_FETCH)
    @Join(value = "uploader", type = Join.Type.LEFT_FETCH)
    @Override
    Optional<VideoExt> findById(@NotNull Long id);

    Iterable<VideoExt> findByViewers(UserExt viewer);

    Iterable<VideoExt> findByTags(HashtagExt tag);

    Iterable<VideoExt> findByUploader(UserExt uploader);

    Optional<VideoExt> findOne (long id);
	/* protected region Implementation end */
}