package by.malanka.academy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class EntityAuditConfiguration {

    @Bean
    public AuditorAware<UUID> auditorProvider() {
        return () -> Optional.of(UUID.randomUUID());
    }

}
