package com.miguel.dataTransform;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameRecord {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;
  private String username;
  private int score;
  private int gameTime;
  private String comment;

  protected GameRecord() {} // Empty constructor for JPA

  public GameRecord(String username, int score, int gameTime, String comment) {
    this.username = username;
    this.score = score;
    this.gameTime = gameTime;
    this.comment = comment;
  }

  public GameRecord(String username) {
    this.username = username;
    this.score = 0;
    this.gameTime = 0;
    this.comment = "";
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setGameTime(int gameTime) {
    this.gameTime = gameTime;
  }

  public void setComment(String comment) {
    this.comment = comment;
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

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("GameRecord{");
    sb.append("id=").append(id);
    sb.append(", username='").append(username).append('\'');
    sb.append(", score=").append(score);
    sb.append(", gameTime=").append(gameTime);
    sb.append(", comment='").append(comment).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
