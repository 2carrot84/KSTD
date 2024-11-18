package com._2carrot84.kstd.lecture.application.port.in;

import java.util.List;

import com._2carrot84.kstd.lecture.domain.Lecture;

public interface FindLecturesUseCase {
	List<Lecture> findAll();
	List<Lecture> findAllById(List<Long> ids);
	List<Lecture> findByIdWithApplicants(Long id);
	List<Lecture> findAllBy(FindLectureQuery query);
	List<Lecture> findPopularLectures();
}
