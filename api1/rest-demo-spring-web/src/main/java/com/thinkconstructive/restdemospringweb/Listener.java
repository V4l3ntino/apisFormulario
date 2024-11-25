package com.thinkconstructive.restdemospringweb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@EnableScheduling
@SpringBootApplication
public class Listener {

    public static void main(String[] args){
        SpringApplication.run(Listener.class, args);
    }

}
