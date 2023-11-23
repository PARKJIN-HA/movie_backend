package com.parkjinha.movie_backend.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "app.api")
public class ApiKeyProperties {
    private final String kobisApiKey;
    private final String kmdbApiKey;
}

