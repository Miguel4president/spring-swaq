package bootRest.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aaron on 3/30/17.
 */
@Entity
public class Account {

  @OneToMany(mappedBy = "account")
  private Set<GameRecord> gameRecords = new HashSet<>();

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;
  private String username;

  public Account(String username) {
    this.username = username;
  }

  protected Account() {} // JPA needs this

  public Set<GameRecord> getGameRecords() {
    return gameRecords;
  }

  public void setGameRecords(Set<GameRecord> gameRecords) {
    this.gameRecords = gameRecords;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Account{");
    sb.append("gameRecords=").append(gameRecords);
    sb.append(", id=").append(id);
    sb.append(", username='").append(username).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
