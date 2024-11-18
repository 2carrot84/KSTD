package com._2carrot84.kstd.lecture.adapter.out.persistence;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com._2carrot84.kstd.lecture.domain.PopularLecture;

@SpringBootTest
@ActiveProfiles("test")
class PopularLecturePersistenceAdapterTest {

	@Autowired
	PopularLecturePersistenceAdapter adapter;

	@Autowired
	PopularLectureRepository popularLectureRepository;

	@Test
	void save() {
		PopularLecture lecture = PopularLecture.builder()
			.lectureId(1L)
			.count(10L)
			.build();

		adapter.save(lecture);

		List<PopularLecture> popularLectures = adapter.findPopularLectures();

		assertThat(popularLectures.size()).isGreaterThanOrEqualTo(1);
	}

	@Test
	void decreasePopularLecture() {
		long lectureId = 1L;
		PopularLecture lecture = PopularLecture.builder()
			.lectureId(lectureId)
			.count(10L)
			.build();

		adapter.save(lecture);
		adapter.decreaseApplicantCount(lectureId);

		Double applicantCount = popularLectureRepository.findById(lectureId);

		assertThat(applicantCount).isEqualTo(9);
	}

	@Test
	void deleteAll() {
		PopularLecture lecture = PopularLecture.builder()
			.lectureId(1L)
			.count(10L)
			.build();

		adapter.save(lecture);

		List<PopularLecture> popularLectures = adapter.findPopularLectures();

		assertThat(popularLectures.size()).isGreaterThanOrEqualTo(1);

		adapter.deleteAll();

		List<PopularLecture> empty = adapter.findPopularLectures();

		assertThat(empty.size()).isZero();
		assertThat(empty.isEmpty()).isTrue();
	}

	@Test
	void increasePopularLecture() {
		long lectureId = 1L;
		PopularLecture lecture = PopularLecture.builder()
			.lectureId(lectureId)
			.count(10L)
			.build();

		adapter.save(lecture);
		adapter.increaseApplicantCount(lectureId);

		Double applicantCount = popularLectureRepository.findById(lectureId);

		assertThat(applicantCount).isEqualTo(11);
	}
}