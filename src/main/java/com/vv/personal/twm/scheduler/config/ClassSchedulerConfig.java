package com.vv.personal.twm.scheduler.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vivek
 * @since 22/07/22
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "scheduler")
public class ClassSchedulerConfig {
    private String location;
}