package uk.ac.york.eng2.vm.repositories;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.vm.domain.User;
import uk.ac.york.eng2.vm.domain.Video;
import uk.ac.york.eng2.vm.dto.UserDTO;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    @Join(value = "watchedVideos", type = Join.Type.RIGHT_FETCH)
    @Override
    Optional<User> findById(@NotNull Long id);
    Optional<UserDTO> findOne(long id);
}
