package org.ohara.mcs;

import org.ohara.mcs.server.annotation.EnableOHaraMcsServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author SpringCat
 */
@SpringBootApplication
@EnableOHaraMcsServer(enable = true)
public class Demo2Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Demo2Application.class);
        application.run(args);
    }
}
