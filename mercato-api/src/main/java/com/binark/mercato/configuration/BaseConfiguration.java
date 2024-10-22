package com.binark.mercato.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.binark.annotation.EnableQueryPredicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The base application configuration
 */
@Configuration
@EnableQueryPredicate
public class BaseConfiguration {

    /**
     * Object mapper bean with Java 8 Time module extension
     *
     * @return {@link ObjectMapper} object mapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
