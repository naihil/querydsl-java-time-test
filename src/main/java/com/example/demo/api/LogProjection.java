package com.example.demo.api;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class LogProjection {
	private Long id;
	private OffsetDateTime createdAt;
	private String title;
}
