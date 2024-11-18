package com._2carrot84.kstd.lecture.application.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com._2carrot84.kstd.common.util.DateTimeUtil;
import com._2carrot84.kstd.lecture.application.port.in.FindLectureQuery;
import com._2carrot84.kstd.lecture.application.port.out.FindLecturesPort;
import com._2carrot84.kstd.lecture.application.port.out.FindPopularLecturesPort;
import com._2carrot84.kstd.lecture.domain.Lecture;
import com._2carrot84.kstd.lecture.domain.PopularLecture;

@ExtendWith(MockitoExtension.class)
class FindLectureServiceTest {

	@Mock
	private FindLecturesPort findLecturesPort;

	@Mock
	private FindPopularLecturesPort findPopularLecturesPort;

	@InjectMocks
	private FindLectureService findLectureService;

	@Test
	public void findAllBy() {
		FindLectureQuery query = FindLectureQuery.builder()
			.fromStartDateTime("2024-11-18 10:00")
			.toStartDateTime("2024-11-20 10:00")
			.build();

		LocalDateTime fromStartDateTime = DateTimeUtil.parse(query.getFromStartDateTime());
		LocalDateTime toStartDateTime = DateTimeUtil.parse(query.getToStartDateTime());

		List<Lecture> expectedLectures = new ArrayList<>();
		when(findLecturesPort.findAllBy(fromStartDateTime, toStartDateTime)).thenReturn(expectedLectures);

		List<Lecture> actualLectures = findLectureService.findAllBy(query);

		assertThat(actualLectures).isSameAs(expectedLectures);
		verify(findLecturesPort).findAllBy(fromStartDateTime, toStartDateTime);
	}

	@Test
	void findPopularLectures() {
		List<PopularLecture> popularLectures = new ArrayList<>();
		PopularLecture popularLecture1 = PopularLecture.builder()
			.lectureId(1L)
			.build();
		PopularLecture popularLecture2 = PopularLecture.builder()
			.lectureId(2L)
			.build();
		popularLectures.add(popularLecture1);
		popularLectures.add(popularLecture2);

		List<Lecture> allLectures = new ArrayList<>();
		Lecture lecture1 = Lecture.builder()
			.id(2L)
			.build();
		Lecture lecture2 = Lecture.builder()
			.id(1L)
			.build();

		allLectures.add(lecture1);
		allLectures.add(lecture2);

		when(findPopularLecturesPort.findPopularLectures()).thenReturn(popularLectures);
		when(findLecturesPort.findAllById(anyList())).thenReturn(allLectures);

		List<Lecture> actualLectures = findLectureService.findPopularLectures();

		assertThat(actualLectures.get(0).getId()).isEqualTo(1L);
		assertThat(actualLectures.get(1).getId()).isEqualTo(2L);
	}
}