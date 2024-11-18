package com._2carrot84.kstd.lecture.domain.mapper;

import com._2carrot84.kstd.lecture.adapter.in.web.dto.ApplyLectureResponse;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.ApplyLectureEntity;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.ApplyLectureId;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.LectureEntity;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.PopularLectureEntity;
import com._2carrot84.kstd.lecture.domain.ApplyLecture;
import com._2carrot84.kstd.lecture.domain.PopularLecture;

public class ApplyLectureMapper {

	public static ApplyLectureEntity toEntity(ApplyLecture applyLecture, LectureEntity lecture) {
		return ApplyLectureEntity.builder()
			.applyLectureId(ApplyLectureId.builder()
				.employeeId(applyLecture.getEmployeeId())
				.build())
			.lecture(lecture)
			.build();
	}

	public static ApplyLecture toDomain(ApplyLectureEntity applyLecture) {
		return ApplyLecture.builder()
			.lectureId(applyLecture.getApplyLectureId().getLectureId())
			.employeeId(applyLecture.getApplyLectureId().getEmployeeId())
			.applyDateTime(applyLecture.getApplyDateTime())
			.lecture(LectureMapper.mapToDomain(applyLecture.getLecture()))
			.build();
	}

	public static ApplyLecture toDomainWithoutLecture(ApplyLectureEntity applyLecture) {
		return ApplyLecture.builder()
			.lectureId(applyLecture.getApplyLectureId().getLectureId())
			.employeeId(applyLecture.getApplyLectureId().getEmployeeId())
			.applyDateTime(applyLecture.getApplyDateTime())
			.build();
	}

	public static ApplyLectureResponse toDto(ApplyLecture applyLecture) {
		return ApplyLectureResponse.builder()
			.lectureId(applyLecture.getLectureId())
			.employeeId(applyLecture.getEmployeeId())
			.applyDateTime(applyLecture.getApplyDateTime())
			.build();
	}

	public static PopularLecture mapToDomain(PopularLectureEntity lecture) {
		return PopularLecture.builder()
			.lectureId(lecture.getLectureId())
			.count(lecture.getCount())
			.build();
	}
}
