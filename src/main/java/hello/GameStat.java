package hello;

public class GameStat {

  private final long id;
  private final String username;
  private final int score;
  private final int gameTime;
  private final String comment;

  public GameStat(long id, String username, int score, int gameTime, String comment) {
    this.id = id;
    this.username = username;
    this.score = score;
    this.gameTime = gameTime;
    this.comment = comment;
  }

  public GameStat(long id, String username) {
    this.id = id;
    this.username = username;
    this.score = 0;
    this.gameTime = 0;
    this.comment = "";
  }

  public long getId() {
    return id;
  }

  public String getComment() {
    return comment;
  }

  public String getUsername() {
    return username;
  }

  public int getScore() {
    return score;
  }

  public int getGameTime() {
    return gameTime;
  }
}