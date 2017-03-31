package bootRest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class GameRecord {

  @JsonIgnore
  @ManyToOne
  private Account account;

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;
  private String gameName;
  private int score;
  private int gameTime;

  public GameRecord() {} // Empty constructor for JPA

  public GameRecord(Account account, String gameName, int score, int gameTime) {
    this.account = account;
    this.gameName = gameName;
    this.score = score;
    this.gameTime = gameTime;
  }

  public GameRecord(Account account) {
    this.account = account;
    this.gameName = "tetris";
    this.score = 0;
    this.gameTime = 0;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGameName() {
    return gameName;
  }

  public void setGameName(String gameName) {
    this.gameName = gameName;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getGameTime() {
    return gameTime;
  }

  public void setGameTime(int gameTime) {
    this.gameTime = gameTime;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("GameRecord{");
    sb.append("account=").append(account);
    sb.append(", id=").append(id);
    sb.append(", gameName='").append(gameName).append('\'');
    sb.append(", score=").append(score);
    sb.append(", gameTime=").append(gameTime);
    sb.append('}');
    return sb.toString();
  }
}
