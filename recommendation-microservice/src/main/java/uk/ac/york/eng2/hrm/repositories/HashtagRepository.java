package uk.ac.york.eng2.hrm.repositories;

/* protected region imports on begin */
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.hrm.domains.HashtagExt;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
/* protected region imports end */

@Repository
public interface HashtagRepository extends CrudRepository</* protected region Generic on begin */HashtagExt, Long/* protected region Generic end */> {

	/* protected region Implementation on begin */
    Optional<HashtagExt> findByName(@NotNull String name);

    @Transactional
    @Query(
            "SELECT t FROM HashtagExt t " +
            "LEFT JOIN t.subscribers u " +
            "WHERE u.userId IN (:userIds) AND t.name != :excludeHashtagName " +
            "GROUP BY t.id ORDER BY COUNT(u.id) DESC ")
    Iterable<HashtagExt> findTop10ByUserIdsAndExcludeHashtagName(List<Long> userIds, String excludeHashtagName);
	/* protected region Implementation end */
}