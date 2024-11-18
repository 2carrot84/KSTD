package com._2carrot84.kstd.lecture;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com._2carrot84.kstd.lecture.adapter.out.persistence.LectureRepository;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.LectureEntity;
import com._2carrot84.kstd.lecture.application.port.in.ApplyLectureCommand;
import com._2carrot84.kstd.lecture.application.service.ApplyLectureService;

@SpringBootTest
public class ApplyLectureLockTest {

	@Autowired
	private ApplyLectureService applyLectureService;

	@Autowired
	private LectureRepository lectureRepository;

	private Long LECTURE_ID = null;

	@BeforeEach
	void before() {
		LectureEntity lecture1 = LectureEntity.builder()
			.name("강연1")
			.speaker("강연자")
			.room("강연장")
			.content("강연내용")
			.maxApplicantCount(10)
			.startDateTime(LocalDateTime.now().plusDays(1))
			.build();

		LectureEntity saved = lectureRepository.save(lecture1);

		LECTURE_ID = saved.getId();
	}

	@Test
	void applyLectureLockTest() throws InterruptedException {
		int numberOfThreads = 100;
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);

		for (int i = 0; i < numberOfThreads; i++) {
			executorService.submit(() -> {
				try {
					applyLectureService.apply(ApplyLectureCommand.builder()
						.lectureId(LECTURE_ID)
						.employeeId(UUID.randomUUID().toString().substring(0, 5))
						.build());
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();

		List<LectureEntity> lectures = lectureRepository.findByIdWithApplicants(LECTURE_ID);
		LectureEntity lecture = lectures.get(0);

		assertThat(lecture.getApplicants().size()).isEqualTo(10);
	}
}
