package edu.cnm.deepdive.dicewareservice.configuration;

import java.security.SecureRandom;
import java.util.random.RandomGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

  @Bean
  public RandomGenerator provideSecureRandom() {
    return new SecureRandom();
  }

}
