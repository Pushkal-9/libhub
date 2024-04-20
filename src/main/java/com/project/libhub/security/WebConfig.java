package com.project.libhub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Or a more specific path
                        .allowedOrigins("http://localhost:3000") // The origin of your React app
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Whatever methods you want to allow
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
