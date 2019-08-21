package com.rynk.datascript;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableTransactionManagement(proxyTargetClass = true)
public class DatascriptApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatascriptApplication.class, args);
    }

}
