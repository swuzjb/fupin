package com.ty.fuping;

import com.ty.fuping.properties.BackUpProperties;
import com.ty.fuping.startup.StartUpRun;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({BackUpProperties.class})
@ServletComponentScan
public class FupingApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FupingApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FupingApplication.class, args);
    }

    @Bean
    public StartUpRun startUpRun() {
        return new StartUpRun();
    }
}
