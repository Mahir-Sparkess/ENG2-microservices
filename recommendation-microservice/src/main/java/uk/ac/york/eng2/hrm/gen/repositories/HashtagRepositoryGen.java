package uk.ac.york.eng2.hrm.gen.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import uk.ac.york.eng2.hrm.gen.domain.HashtagGen;

@Repository
public interface HashtagRepositoryGen extends CrudRepository<HashtagGen, Long> {

}