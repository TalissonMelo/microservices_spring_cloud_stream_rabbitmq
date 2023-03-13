package com.algaworks.example.spring.cloud.stream.metrics.listener;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.algaworks.example.spring.cloud.stream.metrics.api.model.HealthCheckTaskResultModel;
import com.algaworks.example.spring.cloud.stream.metrics.domain.model.HealthCheckTaskResult;
import com.algaworks.example.spring.cloud.stream.metrics.domain.service.MetricService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HealthCheckResultEventListener implements Consumer<HealthCheckTaskResultModel> {

    private final MetricService metricService;

    @Override
    public void accept(HealthCheckTaskResultModel model) {
        HealthCheckTaskResult taskResult = model.toDomain();
        metricService.process(taskResult);
    }
}
