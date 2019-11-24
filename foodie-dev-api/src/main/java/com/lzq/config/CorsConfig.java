package com.lzq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    public CorsConfig() {

    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        //设置请求的URL
        corsConfig.addAllowedOrigin("http://localhost:8080");
        //设置是否发送cookie信息 如果不设置这个无法实现跳转
        corsConfig.setAllowCredentials(true);
        //设置允许请求的方式
        corsConfig.addAllowedMethod("*");
        //设置允许的header
        corsConfig.addAllowedHeader("*");
        //为URL添加映射路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //映射所有路径
        source.registerCorsConfiguration("/**",corsConfig);
        return new CorsFilter(source);

    }
}
