    package com.okconde.bestepstyle.core.config.cross;

    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.servlet.config.annotation.CorsRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    /**
     * Created by Trong Phu on 17/09/2024 23:04
     *
     * @author Trong Phu
     */
    @Configuration
    public class CrossConfig implements WebMvcConfigurer {

        @Value("${server_fe}")
        private String SERVER_FE;
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://192.168.0.103:4200") // Địa chỉ IP của Angular
                    .allowedOriginPatterns("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
        }
    }

