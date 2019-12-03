package com.forur2bhappy.rainbowfart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@MapperScan("com.gateway.dao")
@EnableCaching
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.forur2bhappy.rainbowfart")
@EnableScheduling
public class RainbowfartApplication {

    public static void main(String[] args) {
        SpringApplication.run(RainbowfartApplication.class, args);
    }

}
