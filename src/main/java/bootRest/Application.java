package bootRest;

import bootRest.models.Account;
import bootRest.models.AccountRepo;
import bootRest.models.GameRecord;
import bootRest.models.GameRecordRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;


@SpringBootApplication
@ComponentScan
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner init(AccountRepo accountRepo,
                         GameRecordRepo bookmarkRepo) {
    return (evt) -> Arrays.asList(
            "jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
            .forEach(
                    a -> {
                      Account account = accountRepo.save(new Account(a));
                      bookmarkRepo.save(new GameRecord(account, "HoN", 100, 50));
                      bookmarkRepo.save(new GameRecord(account, "WoW", 120, 99999));
                    });
  }
}