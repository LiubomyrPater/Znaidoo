package com.finalproject.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter

@Component
public class ApplicationProperties {

    @Value("${demo.config.token.time-to-live}")
    private int tokenTimeToLiveInHours;

    @Value("${demo.config.ApplicationProperties.passwordLength}")
    private int passwordLength;
}
