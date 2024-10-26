package com.xblog.front.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * RestTemplate 설정
 * @author jihyeon
 */
@Configuration
public class RestTemplateConfig implements WebMvcConfigurer {

    /**
     * RestTemplate 빈을 생성할 수 있도록 만든 method
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
