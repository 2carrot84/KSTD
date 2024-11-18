package com._2carrot84.kstd.lecture.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LectureTest {

	@ParameterizedTest
	@MethodSource("provideArgumentsForApplicant")
	void canApply_applicant(int applyCount, int applicantCount, boolean expected) {
		List<ApplyLecture> applicants = new ArrayList<>();

		for (int i = 0; i < applyCount; i++) {
			applicants.add(ApplyLecture.builder()
				.lectureId(1L)
				.build());
		}

		Lecture lecture = Lecture.builder()
			.maxApplicantCount(applicantCount)
			.startDateTime(LocalDateTime.now().plusDays(1))
			.applicants(applicants)
			.build();

		assertThat(lecture.canApply()).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForApplicant() {
		return Stream.of(
			Arguments.of(5, 10, true),
			Arguments.of(5, 5, false),
			Arguments.of(10, 5, false)
		);
	}

	@ParameterizedTest
	@MethodSource("provideArgumentsForStartDateTime")
	void canApply_startDateTime(LocalDateTime startDateTime, boolean expected) {
		Lecture lecture = Lecture.builder()
			.maxApplicantCount(10)
			.startDateTime(startDateTime)
			.build();

		assertThat(lecture.canApply()).isEqualTo(expected);
	}

	private static Stream<Arguments> provideArgumentsForStartDateTime() {
		return Stream.of(
			Arguments.of(LocalDateTime.now().minusDays(1), false),
			Arguments.of(LocalDateTime.now().plusHours(1), true)
		);
	}
}