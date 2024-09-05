package com.xblog.front.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RestTemplateConfig implements WebMvcConfigurer { // thymeleafConfig 추가

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
