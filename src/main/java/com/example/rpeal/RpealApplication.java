package com.example.rpeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RpealApplication {

    public static void main(String[] args){
        SpringApplication.run(RpealApplication.class, args);
    }

    

}


//@SpringBootApplication
//public class RpealApplication extends SpringBootServletInitializer {
//
//    public static void main(String[] args){
//        SpringApplication.run(RpealApplication.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(RpealApplication.class);
//    }
//
//}
