package com.svn.gsheetsserviceaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class GsheetsserviceaccountApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GsheetsserviceaccountApplication.class, args);
        System.out.println("--- run.");
    }

}
