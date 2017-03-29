package com.miguel.dataTransform;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aaron on 3/20/17.
 */
@RestController
public class GameRecordController {
  private static final Logger log = LoggerFactory.getLogger(Application.class);

  @RequestMapping("/record")
  public GameRecord gameStat(@RequestParam(value="username", defaultValue="basher") String name) {
    log.info("Totally hit that.");
    return new GameRecord(name);
  }
}