package com._2carrot84.kstd.lecture.adapter.out.persistence;

import java.util.List;

import com._2carrot84.kstd.lecture.domain.PopularLecture;

public interface PopularLectureRepository {
	void save(Long lectureId, Long applicantCount);
	void increaseApplicantCount(Long lectureId);
	void decreaseApplicantCount(Long lectureId);
	void deleteAll();
	List<PopularLecture> findAll();
	Double findById(Long lectureId);
}
