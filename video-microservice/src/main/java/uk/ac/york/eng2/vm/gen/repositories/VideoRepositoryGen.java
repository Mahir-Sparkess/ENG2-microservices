package uk.ac.york.eng2.vm.gen.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import uk.ac.york.eng2.vm.gen.domain.VideoGen;

@Repository
public interface VideoRepositoryGen extends CrudRepository<VideoGen, Long> {

}