package uk.ac.york.eng2.sm.repositories;

/* protected region imports on begin */
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.sm.domains.HashtagExt;
import uk.ac.york.eng2.sm.domains.UserExt;
import uk.ac.york.eng2.sm.domains.VideoExt;
import uk.ac.york.eng2.sm.gen.domain.Video;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
/* protected region imports end */

@Repository
public interface VideoRepository extends CrudRepository</* protected region Generic on begin */VideoExt, Long/* protected region Generic end */> {

	/* protected region Implementation on begin */
    Optional<VideoExt> findByVideoId(@NotNull Long videoId);


    @Transactional
    @Query(
        "SELECT v FROM VideoExt v " +
        "LEFT JOIN v.viewers u LEFT JOIN v.tags t " +
        "WHERE (u.userId != :userId OR SIZE(v.viewers) = 0) AND t.name in :tagName " +
        "GROUP BY v.title ORDER BY COUNT(t.id) DESC ")
    Iterable<VideoExt> findTop10ByTagsNameAndViewerUserIdNot(@NotNull List<String> tagName, Long userId);
	/* protected region Implementation end */
}