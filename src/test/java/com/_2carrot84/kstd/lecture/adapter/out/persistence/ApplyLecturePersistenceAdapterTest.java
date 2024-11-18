package com._2carrot84.kstd.lecture.adapter.out.persistence;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;

import com._2carrot84.kstd.common.config.QueryDslConfig;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.ApplyLectureEntity;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.ApplyLectureId;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.LectureEntity;
import com._2carrot84.kstd.lecture.domain.ApplyLecture;
import com._2carrot84.kstd.lecture.domain.PopularLecture;

@DataJpaTest
@ActiveProfiles("test")
@Import({ApplyLecturePersistenceAdapter.class, QueryDslConfig.class})
class ApplyLecturePersistenceAdapterTest {

	@Autowired
	private LectureRepository lectureRepository;

	@Autowired
	private ApplyLectureRepository applyLectureRepository;

	@Autowired
	private ApplyLecturePersistenceAdapter adapter;

	@BeforeEach
	void beforeEach() {

	}

	@Test
	void create() {
		LectureEntity lecture1 = LectureEntity.builder()
			.name("강연1")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(LocalDateTime.now())
			.build();

		LectureEntity saved = lectureRepository.save(lecture1);

		ApplyLecture applyLecture = ApplyLecture.builder()
			.lectureId(saved.getId())
			.employeeId("12345")
			.build();

		ApplyLecture applied = adapter.create(applyLecture);

		assertThat(applied).isNotNull();
	}

	@Test
	void create_fail_when_duplicate() {
		LectureEntity lecture1 = LectureEntity.builder()
			.name("강연1")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(LocalDateTime.now())
			.build();

		LectureEntity saved = lectureRepository.save(lecture1);

		ApplyLecture applyLecture = ApplyLecture.builder()
			.lectureId(saved.getId())
			.employeeId("12345")
			.build();

		adapter.create(applyLecture);

		assertThatThrownBy(() -> adapter.create(applyLecture)).isInstanceOf(DuplicateKeyException.class);
	}

	@Test
	void delete() {
		LectureEntity lecture1 = LectureEntity.builder()
			.name("강연1")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(LocalDateTime.now())
			.build();

		LectureEntity saved = lectureRepository.save(lecture1);

		ApplyLecture applyLecture = ApplyLecture.builder()
			.lectureId(saved.getId())
			.employeeId("12345")
			.build();

		ApplyLecture applied = adapter.create(applyLecture);

		adapter.delete(applied);

		Optional<ApplyLectureEntity> deleted = applyLectureRepository.findById(ApplyLectureId.builder()
			.lectureId(applied.getLectureId())
			.employeeId(applied.getEmployeeId())
			.build()
		);

		assertThat(deleted.isEmpty()).isTrue();
	}

	@Test
	void findByEmployeeId() {

		LectureEntity lecture1 = LectureEntity.builder()
			.name("강연1")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(LocalDateTime.now())
			.build();

		LectureEntity lecture2 = LectureEntity.builder()
			.name("강연2")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(LocalDateTime.now())
			.build();

		LectureEntity saved1 = lectureRepository.save(lecture1);
		LectureEntity saved2 = lectureRepository.save(lecture2);

		String employeeId = "12345";
		adapter.create(ApplyLecture.builder()
			.lectureId(saved1.getId())
			.employeeId(employeeId)
			.build());

		adapter.create(ApplyLecture.builder()
			.lectureId(saved2.getId())
			.employeeId(employeeId)
			.build());

		List<ApplyLecture> applyLectures = adapter.findByEmployeeId(employeeId);

		assertThat(applyLectures.size()).isGreaterThanOrEqualTo(2);
	}

	@Test
	void findPopularLectures() {
		LectureEntity lecture1 = LectureEntity.builder()
			.name("강연1")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(LocalDateTime.now())
			.build();

		LectureEntity created = lectureRepository.save(lecture1);

		applyLectureRepository.save(ApplyLectureEntity.builder()
			.lecture(created)
			.applyLectureId(ApplyLectureId.builder()
				.lectureId(created.getId())
				.employeeId("12345")
				.build())
			.build());

		applyLectureRepository.save(ApplyLectureEntity.builder()
			.lecture(created)
			.applyLectureId(ApplyLectureId.builder()
				.lectureId(created.getId())
				.employeeId("56789")
				.build())
			.build());

		List<PopularLecture> popularLectures = adapter.findPopularLectures();

		assertThat(popularLectures.size()).isGreaterThanOrEqualTo(1);
	}
}