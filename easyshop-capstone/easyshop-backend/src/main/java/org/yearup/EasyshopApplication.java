package org.yearup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yearup.configurations.DatabaseConfig;

@SpringBootApplication
public class EasyshopApplication implements ApplicationRunner {

    @Autowired
    DatabaseConfig dbConfig;

    public static void main(String[] args) {
        SpringApplication.run(EasyshopApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dbConfig.dataSource();
    }
}
