package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aaron on 3/20/17.
 */
@RestController
public class GameStatController {
  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/greeting")
  public GameStat gameStat(@RequestParam(value="name", defaultValue="World") String name) {
    return new GameStat(counter.incrementAndGet(), name);
  }
}