package org.ohara.mcs;

import org.ohara.mcs.annotation.EnableOHaraMcsClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author SpringCat
 */
@SpringBootApplication
@EnableOHaraMcsClient(enable = true)
public class Client1Application {
    public static void main(String[] args) {
        SpringApplication.run(Client1Application.class, args);
    }
}
