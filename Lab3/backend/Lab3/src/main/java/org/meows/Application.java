package org.meows;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.meows")
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}