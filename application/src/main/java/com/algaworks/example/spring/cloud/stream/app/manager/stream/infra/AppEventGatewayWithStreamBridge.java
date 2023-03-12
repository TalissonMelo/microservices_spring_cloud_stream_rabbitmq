package com.algaworks.example.spring.cloud.stream.app.manager.stream.infra;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import com.algaworks.example.spring.cloud.stream.app.manager.api.model.AppModel;
import com.algaworks.example.spring.cloud.stream.app.manager.core.Mapper;
import com.algaworks.example.spring.cloud.stream.app.manager.domain.model.App;
import com.algaworks.example.spring.cloud.stream.app.manager.stream.AppProperties;
import com.algaworks.example.spring.cloud.stream.app.manager.stream.service.AppEventGateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppEventGatewayWithStreamBridge implements AppEventGateway {

    private final StreamBridge streamBridge;
    private final AppProperties appProperties;
    private final Mapper mapper;

    @Override
    public void sendAppCreatedEvent(App app) {
        log.info("App criado " + app.getId());
        AppModel model = mapper.map(app, AppModel.class);
        streamBridge.send(appProperties.getAppCreatedChannel(), model);
    }

    @Override
    public void sendAppUpdatedEvent(App app) {
        log.info("App atualizado " + app.getId());
        AppModel model = mapper.map(app, AppModel.class);
        streamBridge.send(appProperties.getAppUpdatedChannel(), model);
    }
}