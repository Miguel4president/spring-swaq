package bootRest.models;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRecordRepo extends JpaRepository<GameRecord, Long> {

  Collection<GameRecord> findByAccountUsername(String username);
}
