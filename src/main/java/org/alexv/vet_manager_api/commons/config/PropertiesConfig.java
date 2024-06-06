package org.alexv.vet_manager_api.commons.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "properties")
@Data
public class PropertiesConfig {

    String currency;
    String jwtSecret;
    Long jwtExpiration;
}
