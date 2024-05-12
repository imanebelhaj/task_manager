package ma.xproce.task_manager.dao.repositories;

import ma.xproce.task_manager.dao.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);
}
