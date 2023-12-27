package uk.ac.york.eng2.hrm.gen.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import uk.ac.york.eng2.hrm.gen.domain.SubscriberGen;

@Repository
public interface SubscriberRepositoryGen extends CrudRepository<SubscriberGen, Long> {

}