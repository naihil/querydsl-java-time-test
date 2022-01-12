package com.example.demo;

import com.example.demo.api.LogProjection;
import com.example.demo.service.LogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private LogService logService;

	@Test
	void contextLoads() {
		Page<LogProjection> logProjections = logService.selectLogs(Pageable.ofSize(20));
	}

}
