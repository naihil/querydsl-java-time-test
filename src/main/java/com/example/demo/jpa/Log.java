package com.example.demo.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "log")
@Getter
@Setter
@ToString
public class Log {
	@Id
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "created_at")
	private OffsetDateTime createdAt;

	@Column(name = "title")
	private String title;
}
