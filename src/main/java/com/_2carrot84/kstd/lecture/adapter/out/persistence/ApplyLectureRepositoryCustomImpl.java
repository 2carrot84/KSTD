package com._2carrot84.kstd.lecture.adapter.out.persistence;

import static com._2carrot84.kstd.lecture.adapter.out.persistence.entity.QApplyLectureEntity.*;
import static com._2carrot84.kstd.lecture.adapter.out.persistence.entity.QLectureEntity.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.util.StringUtils;

import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.ApplyLectureEntity;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.PopularLectureEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApplyLectureRepositoryCustomImpl implements ApplyLectureRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<ApplyLectureEntity> findByEmployeeId(String employeeId) {
		return queryFactory.selectFrom(applyLectureEntity)
			.join(applyLectureEntity.lecture, lectureEntity)
			.fetchJoin()
			.where(eqEmployeeId(employeeId))
			.fetch();
	}

	private BooleanExpression eqEmployeeId(String employeeId) {
		return StringUtils.hasText(employeeId) ? applyLectureEntity.applyLectureId.employeeId.eq(employeeId) : null;
	}

	@Override
	public List<PopularLectureEntity> findPopularLectures() {
		return queryFactory
			.select(
				Projections.fields(PopularLectureEntity.class,
					applyLectureEntity.applyLectureId.lectureId,
					applyLectureEntity.count().as("count")
				)
			)
			.from(applyLectureEntity)
			.where(goeApplyDateTime())
			.groupBy(applyLectureEntity.applyLectureId.lectureId)
			.orderBy(applyLectureEntity.count().desc())
			.fetch();
	}

	private BooleanExpression goeApplyDateTime() {
		return applyLectureEntity.applyDateTime.goe(LocalDateTime.now().minusDays(3));
	}
}
