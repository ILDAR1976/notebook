package ru.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan({"ru.base.service"})
public class WebConfig implements WebMvcConfigurer {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        messageConverters.add(stringHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
      Charset UTF8 = Charset.forName("UTF-8");
      StringHttpMessageConverter bean = new StringHttpMessageConverter(UTF8);
      List<MediaType> supportedMediaTypes = new ArrayList<>();
      supportedMediaTypes.add(new MediaType("text", "plain", UTF8));
      supportedMediaTypes.add(new MediaType("*", "*", UTF8));
      supportedMediaTypes.add(MediaType.TEXT_PLAIN);
      supportedMediaTypes.add(MediaType.TEXT_HTML);
      supportedMediaTypes.add(MediaType.ALL);
      bean.setSupportedMediaTypes(supportedMediaTypes);
      return bean;
    }
    
}