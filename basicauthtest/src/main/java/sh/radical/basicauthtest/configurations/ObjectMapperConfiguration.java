package sh.radical.basicauthtest.configurations;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public ObjectMapper configureObjectMapper() {
       var mapper = new Jackson2ObjectMapperBuilder().build();
       mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
       return  mapper;
    }
}
