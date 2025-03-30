package org.ohara.mcs;

import org.ohara.mcs.server.annotation.EnableOHaraMcsServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author SpringCat
 */
@SpringBootApplication
@EnableOHaraMcsServer(enable = true)
public class Demo3Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Demo3Application.class);
        application.run(args);
    }
}
