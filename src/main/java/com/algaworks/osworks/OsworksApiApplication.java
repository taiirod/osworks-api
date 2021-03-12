package com.algaworks.osworks;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OsworksApiApplication {

    @Value("${application.name}")
    private String applicationName;

    @Value("${build.version}")
    private String applicationVersion;

    public static void main(String[] args) {
        SpringApplication.run(OsworksApiApplication.class, args);
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> {
            registry.config().commonTags("app_name", this.applicationName , "app_version", this.applicationVersion);
        };
    }

}
