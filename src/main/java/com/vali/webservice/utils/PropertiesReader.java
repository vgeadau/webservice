package com.vali.webservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Properties reader class.
 */
@Component
@PropertySource("classpath:application.properties")
public class PropertiesReader {
    @Autowired
    public Environment environment;

    /**
     * method returns property from application.properties config file.
     * @param configKey String
     * @return value
     */
    public String getConfigValue(String configKey) {
        return environment.getProperty(configKey);
    }
}