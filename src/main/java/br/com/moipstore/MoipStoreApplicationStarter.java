package br.com.moipstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Class for Starting Spring Boot application project
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "br.com.moipstore.repository")
public class MoipStoreApplicationStarter {

    public static void main(String[] args) throws Exception {
        ApplicationContext app = SpringApplication.run(MoipStoreApplicationStarter.class, args);
    }
}
