package com.algaworks.example.spring.cloud.stream.scheduler.listener;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.algaworks.example.spring.cloud.stream.scheduler.api.model.AppModel;
import com.algaworks.example.spring.cloud.stream.scheduler.domain.model.App;
import com.algaworks.example.spring.cloud.stream.scheduler.domain.repository.AppRepository;
import com.algaworks.example.spring.cloud.stream.scheduler.domain.service.exception.AppNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppUpdatedEventListener implements Consumer<AppModel> {

    private final AppRepository apps;

    @Override
    public void accept(AppModel appModel) {
        log.info("App atualizado recebido " + appModel.getId());
        App existingApp = apps.findById(appModel.getId()).orElseThrow(AppNotFoundException::new);
        App appUpdated = App.builder()
                .id(appModel.getId())
                .name(appModel.getName())
                .address(appModel.getAddress())
                .build();
        existingApp.update(appUpdated);
        apps.saveAndFlush(existingApp);
    }
}