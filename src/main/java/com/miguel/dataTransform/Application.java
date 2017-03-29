package com.miguel.dataTransform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

//  @Bean
//  public CommandLineRunner demo(GameRecordRepo repository) {
//    return (args) -> {
//      // save a couple of customers
//      repository.save(new GameRecord("Slayer0X"));
//      repository.save(new GameRecord("Kirito"));
//      repository.save(new GameRecord("Toshiro"));
//      repository.save(new GameRecord("Shamshirz"));
//      repository.save(new GameRecord("BattleDuck"));
//
//      // fetch all customers
//      log.info("GameRecords found with findAll():");
//      log.info("-------------------------------");
//      for (GameRecord gameRecord : repository.findAll()) {
//        log.info(gameRecord.toString());
//      }
//      log.info("");
//
//      // fetch an individual customer by ID
//      GameRecord gameRecord = repository.findOne(1L);
//      log.info("GameRecord found with findOne(1L):");
//      log.info("--------------------------------");
//      log.info(gameRecord.toString());
//      log.info("");
//
//      // fetch customers by last name
//      log.info("Customer found with findByLastName('Bauer'):");
//      log.info("--------------------------------------------");
//      for (GameRecord sham : repository.findByUsername("Shamshirz")) {
//        log.info(sham.toString());
//      }
//      log.info("");
//    };
//  }

}