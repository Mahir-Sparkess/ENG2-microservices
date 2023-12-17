package uk.ac.york.eng2.vm.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.vm.domain.Video;

@Repository
public interface VideosRepository extends CrudRepository<Video, Long> {

}
