package bootRest.web;

import bootRest.models.AccountRepo;
import bootRest.Application;
import bootRest.models.GameRecord;
import bootRest.models.GameRecordRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

/**
 * Created by aaron on 3/20/17.
 */
@RestController
@Api("gameRecords")
@RequestMapping("/api/{username}/gameRecords")
public class GameRecordRestController {
  private static final Logger log = LoggerFactory.getLogger(Application.class);

  private final GameRecordRepo gameRecordRepo;

  private final AccountRepo accountRepo;

  @Autowired
  GameRecordRestController(GameRecordRepo gameRecordRepo,
                           AccountRepo accountRepo) {
    this.gameRecordRepo = gameRecordRepo;
    this.accountRepo = accountRepo;
  }

  /**
   * Get All by UserID
   * @param username
   * @return Collection<GameRecord>
   */
  @RequestMapping(method = RequestMethod.GET)
  @ApiOperation(value = "Gets all gameRecords for a username",
          notes = "Nothing special to note",
          response = GameRecord.class,
          responseContainer = "List")
  Collection<GameRecord> getAllGameRecords(@PathVariable String username) {
    log.info("Totally hit that.");
    this.validateUser(username);
    return this.gameRecordRepo.findByAccountUsername(username);
  }


  /**
   * Post GameRecord
   * @param username
   * @param input
   * @return Status code
   */
  @RequestMapping(method = RequestMethod.POST)
  @ApiOperation(value = "Create a GameRecord for a specific User, by ID",
          notes = "Nothing special to note")
  ResponseEntity<?> add(@PathVariable String username, @RequestBody GameRecord input) {
    this.validateUser(username);

    return this.accountRepo
            .findByUsername(username)
            .map(account -> {
              GameRecord result = gameRecordRepo.save(new GameRecord(account,
                      input.getGameName(), input.getScore(), input.getGameTime()));

              URI location = ServletUriComponentsBuilder
                      .fromCurrentRequest().path("/{id}")
                      .buildAndExpand(result.getId()).toUri();

              return ResponseEntity.created(location).build();
            })
            .orElse(ResponseEntity.noContent().build());

  }

  /**
   * Get Single by username and GameRecordID
   * @param username
   * @param gameRecordId
   * @return GameRecord
   */
  @RequestMapping(method = RequestMethod.GET, value = "/{gameRecordId}")
  @ApiOperation(value = "Gets a specific gameRecord by username and recordID",
          notes = "Nothing special to note",
          response = GameRecord.class)
  GameRecord getSingleGameRecord(@PathVariable String username, @PathVariable Long gameRecordId) {
    this.validateUser(username);
    return this.gameRecordRepo.findOne(gameRecordId);
  }

  /**
   * Delete a single gameRecord by username and gameRecordID
   * @param username
   * @param gameRecordId
   */
  @RequestMapping(method = RequestMethod.DELETE, value = "/{gameRecordId}")
  @ApiOperation(value = "Delete a specific game record by username and recordID",
          notes = "Nothing special to note")
  void delete(@PathVariable String username, @PathVariable Long gameRecordId) {
    this.validateUser(username);
    this.gameRecordRepo.delete(gameRecordId);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{gameRecordId}")
  @ApiOperation(value = "Puts a specific game record by username and recordID",
          notes = "This is not a PATCH, needs full record and overwrites existing")
  ResponseEntity<?> put(@PathVariable String username, @PathVariable Long gameRecordId,
                        @RequestBody GameRecord input) {
    this.validateUser(username);
    GameRecord toUpdate = this.gameRecordRepo.findOne(gameRecordId);
    toUpdate.setGameName(input.getGameName());
    toUpdate.setGameTime(input.getGameTime());
    toUpdate.setScore(input.getScore());
    GameRecord result = this.gameRecordRepo.save(toUpdate);

    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(result.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  private void validateUser(String username) {
    this.accountRepo.findByUsername(username).orElseThrow(
            () -> new UserNotFoundException(username));
  }
}