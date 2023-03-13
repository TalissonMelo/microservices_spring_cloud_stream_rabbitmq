package com.algaworks.example.spring.cloud.stream.scheduler.listener.infra;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import com.algaworks.example.spring.cloud.stream.scheduler.api.model.HealthCheckTaskRequest;
import com.algaworks.example.spring.cloud.stream.scheduler.core.Mapper;
import com.algaworks.example.spring.cloud.stream.scheduler.domain.model.HealthCheckTask;
import com.algaworks.example.spring.cloud.stream.scheduler.listener.config.AppProperties;
import com.algaworks.example.spring.cloud.stream.scheduler.listener.service.ScheduleEventGateway;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduleEventGatewayWithStreamBridge implements ScheduleEventGateway {

    private final StreamBridge streamBridge;
    private final AppProperties appProperties;
    private final Mapper mapper;

    @Override
    public void sendExecuteTask(HealthCheckTask task) {
        HealthCheckTaskRequest request = mapper.map(task, HealthCheckTaskRequest.class);
        streamBridge.send(appProperties.getHealthCheckTaskChannel(), request);
    }
}