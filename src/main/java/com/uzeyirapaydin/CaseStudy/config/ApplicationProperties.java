package com.uzeyirapaydin.CaseStudy.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ApplicationProperties {

    @Value("${jwt.secretKey}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${spring.data.rest.default-page-size}")
    private int defaultPageSize;

}
