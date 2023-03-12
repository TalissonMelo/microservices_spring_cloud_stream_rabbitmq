package com.algaworks.example.spring.cloud.stream.app.manager.domain.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.algaworks.example.spring.cloud.stream.app.manager.domain.model.App;
import com.algaworks.example.spring.cloud.stream.app.manager.domain.repository.AppRepository;
import com.algaworks.example.spring.cloud.stream.app.manager.domain.service.exception.AppNotFoundException;
import com.algaworks.example.spring.cloud.stream.app.manager.stream.service.AppEventGateway;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppManagementService {

    private final AppRepository apps;
    private final AppEventGateway eventGateway;

    @Transactional
    public App create(App app) {
        Validate.notNull(app);
        apps.saveAndFlush(app);
        eventGateway.sendAppCreatedEvent(app);
        return app;
    }

    @Transactional
    public App update(App updatedApp) {
        Validate.notNull(updatedApp);

        App existingApp = findAppById(updatedApp.getId());

        existingApp.update(updatedApp);
        apps.saveAndFlush(existingApp);
        eventGateway.sendAppUpdatedEvent(existingApp);
        return existingApp;
    }

    private App findAppById(UUID appId) {
        return apps.findById(appId).orElseThrow(AppNotFoundException::new);
    }

}
