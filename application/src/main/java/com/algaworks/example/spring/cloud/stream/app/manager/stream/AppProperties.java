package com.algaworks.example.spring.cloud.stream.app.manager.stream;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AppProperties {
	private String appCreatedChannel = "appCreatedSupplier-out-0";
	private String appUpdatedChannel = "appUpdatedSupplier-out-0";
}
