package com.ty.fuping;

import com.ty.fuping.properties.BackUpProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

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
}
