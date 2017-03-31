package bootRest.models;

/**
 * Created by aaron on 3/30/17.
 */
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
  Optional<Account> findByUsername(String username);
}
