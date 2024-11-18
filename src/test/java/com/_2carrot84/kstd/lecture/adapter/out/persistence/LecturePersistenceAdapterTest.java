package com._2carrot84.kstd.lecture.adapter.out.persistence;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com._2carrot84.kstd.common.config.QueryDslConfig;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.ApplyLectureEntity;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.ApplyLectureId;
import com._2carrot84.kstd.lecture.domain.ApplyLecture;
import com._2carrot84.kstd.lecture.domain.Lecture;
import com._2carrot84.kstd.lecture.domain.mapper.LectureMapper;

@DataJpaTest
@ActiveProfiles("test")
@Import({LecturePersistenceAdapter.class, QueryDslConfig.class})
class LecturePersistenceAdapterTest {

	@Autowired
	private LecturePersistenceAdapter adapter;

	@Autowired
	private ApplyLectureRepository applyLectureRepository;

	@Test
	void create() {
		Lecture lecture1 = Lecture.builder()
			.name("강연1")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(LocalDateTime.now())
			.build();

		Lecture created = adapter.create(lecture1);

		assertThat(created.getId()).isNotNull();
	}

	@Test
	void findAllById() {

		Lecture lecture1 = Lecture.builder()
			.name("강연1")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(LocalDateTime.now())
			.build();

		Lecture lecture2 = Lecture.builder()
			.name("강연2")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(LocalDateTime.now())
			.build();

		Lecture created1 = adapter.create(lecture1);
		Lecture created2 = adapter.create(lecture2);

		List<Lecture> lectures = adapter.findAllById(List.of(created1.getId(), created2.getId()));

		assertThat(lectures.size()).isEqualTo(2);
	}

	@Test
	void findAllBy() {

		LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
		LocalDateTime today = LocalDateTime.now();

		Lecture lecture1 = Lecture.builder()
			.name("어제강연")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(yesterday)
			.build();

		Lecture lecture2 = Lecture.builder()
			.name("내일강연")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(today)
			.build();

		adapter.create(lecture1);
		adapter.create(lecture2);

		List<Lecture> lectures = adapter.findAllBy(today, LocalDateTime.now().plusDays(1));

		assertThat(lectures.size()).isEqualTo(1);
	}

	@Test
	void findById() {
		Lecture lecture1 = Lecture.builder()
			.name("강연1")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(LocalDateTime.now())
			.build();

		Lecture created = adapter.create(lecture1);
		Lecture found = adapter.findById(created.getId());

		assertThat(created.getId()).isEqualTo(found.getId());

		List<ApplyLecture> applicants = found.getApplicants();
		applicants.size();
	}

	@Test
	void findByIdWithApplicants() {
		Lecture lecture1 = Lecture.builder()
			.name("강연1")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(LocalDateTime.now())
			.build();

		Lecture created = adapter.create(lecture1);

		applyLectureRepository.save(ApplyLectureEntity.builder()
			.lecture(LectureMapper.mapToEntity(created))
			.applyLectureId(ApplyLectureId.builder()
				.lectureId(created.getId())
				.employeeId("12345")
				.build())
			.build());

		applyLectureRepository.save(ApplyLectureEntity.builder()
			.lecture(LectureMapper.mapToEntity(created))
			.applyLectureId(ApplyLectureId.builder()
				.lectureId(created.getId())
				.employeeId("56789")
				.build())
			.build());

		applyLectureRepository.flush();

		List<Lecture> lectures = adapter.findByIdWithApplicants(created.getId());

		assertThat(lectures.size()).isNotZero();
		assertThat(lectures.get(0).getApplicants().size()).isEqualTo(2);
	}
}