package com.smartcart.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer // Requires Annotation of Port 8888 to become capable of serving configurations
public class ConfigServerApplication {
// SpringBootApplication basically tells the program to start everything from here
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
