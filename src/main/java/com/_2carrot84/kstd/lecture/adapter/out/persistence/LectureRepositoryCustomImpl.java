package com._2carrot84.kstd.lecture.adapter.out.persistence;

import static com._2carrot84.kstd.lecture.adapter.out.persistence.entity.QLectureEntity.*;
import static com._2carrot84.kstd.lecture.adapter.out.persistence.entity.QApplyLectureEntity.*;

import java.time.LocalDateTime;
import java.util.List;

import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.LectureEntity;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LectureRepositoryCustomImpl implements LectureRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<LectureEntity> findDynamicQuery(LocalDateTime fromStartDateTime, LocalDateTime toStartDateTime) {
		return queryFactory.selectFrom(lectureEntity)
			.where(betweenStartDateTime(fromStartDateTime, toStartDateTime))
			.fetch();
	}

	@Override
	public List<LectureEntity> findByIdWithApplicants(Long id) {
		return queryFactory.selectFrom(lectureEntity)
			.leftJoin(lectureEntity.applicants, applyLectureEntity)
			.fetchJoin()
			.where(eqId(id))
			.fetch();
	}

	private BooleanExpression eqId(Long id) {
		return id == null ? null : lectureEntity.id.eq(id);
	}

	private BooleanExpression betweenStartDateTime(LocalDateTime from, LocalDateTime to) {
		if (from == null || to == null) {
			return null;
		}
		return lectureEntity.startDateTime.between(from, to);
	}
}
