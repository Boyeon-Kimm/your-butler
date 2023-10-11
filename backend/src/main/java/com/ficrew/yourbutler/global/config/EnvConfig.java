package com.ficrew.yourbutler.global.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Slf4j
@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:env.properties") 		// classpath는 src/main/resource/
public class EnvConfig {

    private final Environment environment;

    public String getProperty(String key){
        return environment.getProperty(key);
    }

}