package com._2carrot84.kstd.lecture.application.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com._2carrot84.kstd.common.util.DateTimeUtil;
import com._2carrot84.kstd.lecture.application.port.in.FindLectureQuery;
import com._2carrot84.kstd.lecture.application.port.in.FindLecturesUseCase;
import com._2carrot84.kstd.lecture.application.port.out.FindLecturesPort;
import com._2carrot84.kstd.lecture.application.port.out.FindPopularLecturesPort;
import com._2carrot84.kstd.lecture.domain.Lecture;
import com._2carrot84.kstd.lecture.domain.PopularLecture;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindLectureService implements FindLecturesUseCase {
	private final FindLecturesPort findLecturesPort;
	private final FindPopularLecturesPort findPopularLecturesPort;

	@Override
	public List<Lecture> findAllBy(FindLectureQuery query) {
		LocalDateTime fromStartDateTime;
		LocalDateTime toStartDateTime;

		if (!StringUtils.hasText(query.getFromStartDateTime())) {
			fromStartDateTime = LocalDateTime.now().minusDays(1);
		} else {
			fromStartDateTime = DateTimeUtil.parse(query.getFromStartDateTime());
		}

		if (!StringUtils.hasText(query.getToStartDateTime())) {
			toStartDateTime = LocalDateTime.now().plusDays(7);
		} else {
			toStartDateTime = DateTimeUtil.parse(query.getToStartDateTime());
		}

		return findLecturesPort.findAllBy(fromStartDateTime, toStartDateTime);
	}

	@Override
	public List<Lecture> findPopularLectures() {
		List<PopularLecture> popularLectures = findPopularLecturesPort.findPopularLectures();
		List<Lecture> lectures = findLecturesPort.findAllById(popularLectures
			.stream()
			.map(PopularLecture::getLectureId)
			.toList());

		Map<Long, Integer> indexMap = IntStream.range(0, popularLectures.size())
			.boxed()
			.collect(Collectors.toMap(i -> popularLectures.get(i).getLectureId(), i -> i));

		return IntStream.range(0, lectures.size())
			.boxed()
			.sorted(Comparator.comparingInt(i -> indexMap.get(lectures.get(i).getId())))
			.map(lectures::get)
			.toList();
	}

	@Override
	public List<Lecture> findAll() {
		return findLecturesPort.findAll();
	}

	@Override
	public List<Lecture> findAllById(List<Long> ids) {
		return findLecturesPort.findAllById(ids);
	}

	@Override
	public List<Lecture> findByIdWithApplicants(Long id) {
		return findLecturesPort.findByIdWithApplicants(id);
	}
}
