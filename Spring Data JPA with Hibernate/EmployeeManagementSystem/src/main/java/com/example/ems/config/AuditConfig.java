package com.example.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfig {

    // supplies the value used for @CreatedBy and @LastModifiedBy
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("system");
    }

}
