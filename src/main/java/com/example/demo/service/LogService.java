package com.example.demo.service;

import com.example.demo.api.LogProjection;
import com.example.demo.jpa.QLog;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.PostgreSQLTemplates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
@Slf4j
public class LogService {
	@PersistenceContext
	private EntityManager em;

	public Page<LogProjection> selectLogs(Pageable pageable) {
		log.info("pageable[{}]", pageable);

		QLog qLog = QLog.log;

		JPASQLQuery<LogProjection> query = (new JPASQLQuery<>(em, new PostgreSQLTemplates()))
				.select(Projections.fields(LogProjection.class,
						qLog.id,
						qLog.createdAt,
						qLog.title))
				.from(qLog);

		final Page<LogProjection> result = createPageResult(query, pageable);

		log.info("{}", result);
		return result;
	}

	public static <T> Page<T> createPageResult(JPASQLQuery<T> query, Pageable pageable) {
		// select data
		query.offset(pageable.getOffset());
		query.limit(pageable.getPageSize());
		final List<T> data = query.fetch();

		// select count
		query.offset(0);
		query.limit(1L);
		query.getMetadata().clearOrderBy();
		final long total = query.fetchCount();

		return new PageImpl<>(data, pageable, total);
	}
}
