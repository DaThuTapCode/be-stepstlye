package com.okconde.bestepstyle.core.config.cross;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Trong Phu on 25/09/2024 19:03
 *
 * @author Trong Phu
 */
@Configuration
public class CrossConfig implements WebMvcConfigurer {

    @Value("${server_fe}")
    private String angularServer;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(angularServer)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}

