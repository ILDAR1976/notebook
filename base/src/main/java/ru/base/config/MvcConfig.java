package ru.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

     @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/**")
          .addResourceLocations("/resources/","classpath:/static/")
          .setCachePeriod(3600)
          .resourceChain(true)
          .addResolver(new PathResourceResolver());	
    } 
 
}