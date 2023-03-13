package com.algaworks.example.spring.cloud.stream.scheduler.listener.config;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AppProperties {
    private String healthCheckTaskChannel = "healthCheckTaskCommand-out-0";
}