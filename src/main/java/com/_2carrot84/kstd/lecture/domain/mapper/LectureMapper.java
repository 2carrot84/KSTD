package com._2carrot84.kstd.lecture.domain.mapper;

import java.util.Collections;

import com._2carrot84.kstd.common.util.DateTimeUtil;
import com._2carrot84.kstd.lecture.adapter.in.web.dto.LectureResponse;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.LectureEntity;
import com._2carrot84.kstd.lecture.application.port.in.CreateLectureCommand;
import com._2carrot84.kstd.lecture.domain.Lecture;

public class LectureMapper {

	public static LectureResponse mapToDtoWithApplicants(Lecture lecture) {
		return LectureResponse.builder()
			.id(lecture.getId())
			.name(lecture.getName())
			.speaker(lecture.getSpeaker())
			.room(lecture.getRoom())
			.content(lecture.getContent())
			.maxApplicantCount(lecture.getMaxApplicantCount())
			.startDateTime(lecture.getStartDateTime())
			.applicants(lecture.getApplicants().stream()
				.map(ApplyLectureMapper::toDto)
				.toList())
			.build();
	}

	public static LectureResponse mapToDto(Lecture lecture) {
		return LectureResponse.builder()
			.id(lecture.getId())
			.name(lecture.getName())
			.speaker(lecture.getSpeaker())
			.room(lecture.getRoom())
			.content(lecture.getContent())
			.maxApplicantCount(lecture.getMaxApplicantCount())
			.startDateTime(lecture.getStartDateTime())
			.build();
	}

	public static Lecture mapToDomainWithApplicants(LectureEntity lecture) {
		return Lecture.builder()
			.id(lecture.getId())
			.name(lecture.getName())
			.speaker(lecture.getSpeaker())
			.room(lecture.getRoom())
			.content(lecture.getContent())
			.maxApplicantCount(lecture.getMaxApplicantCount())
			.startDateTime(lecture.getStartDateTime())
			.applicants(lecture.getApplicants().isEmpty() ? Collections.emptyList() : lecture.getApplicants()
				.stream()
				.map(ApplyLectureMapper::toDomainWithoutLecture)
				.toList())
			.build();
	}

	public static Lecture mapToDomain(LectureEntity lecture) {
		return Lecture.builder()
			.id(lecture.getId())
			.name(lecture.getName())
			.speaker(lecture.getSpeaker())
			.room(lecture.getRoom())
			.content(lecture.getContent())
			.maxApplicantCount(lecture.getMaxApplicantCount())
			.startDateTime(lecture.getStartDateTime())
			.build();
	}

	public static LectureEntity mapToEntity(Lecture lecture) {
		return LectureEntity.builder()
			.id(lecture.getId() == null ? null : lecture.getId())
			.name(lecture.getName())
			.speaker(lecture.getSpeaker())
			.room(lecture.getRoom())
			.content(lecture.getContent())
			.maxApplicantCount(lecture.getMaxApplicantCount())
			.startDateTime(lecture.getStartDateTime())
			.build();
	}

	public static Lecture mapToDomain(CreateLectureCommand lecture) {
		return Lecture.builder()
			.name(lecture.getName())
			.speaker(lecture.getSpeaker())
			.room(lecture.getRoom())
			.content(lecture.getContent())
			.maxApplicantCount(lecture.getApplicantCount())
			.startDateTime(DateTimeUtil.parse(lecture.getStartDateTime()))
			.build();
	}
}
