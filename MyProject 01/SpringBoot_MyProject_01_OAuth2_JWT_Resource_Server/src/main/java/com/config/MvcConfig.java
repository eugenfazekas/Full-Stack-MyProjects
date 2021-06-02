package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/user/**").addResourceLocations("classpath:/static/user/").setCacheControl(CacheControl.noCache());
        registry.addResourceHandler("/user/**").addResourceLocations("file:src/main/resources/static/user/").setCacheControl(CacheControl.noCache());
    }
}
