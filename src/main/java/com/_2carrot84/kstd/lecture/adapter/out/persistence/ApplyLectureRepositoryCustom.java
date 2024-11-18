package com._2carrot84.kstd.lecture.adapter.out.persistence;

import java.util.List;

import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.ApplyLectureEntity;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.PopularLectureEntity;

public interface ApplyLectureRepositoryCustom {
	List<ApplyLectureEntity> findByEmployeeId(String employeeId);
	List<PopularLectureEntity> findPopularLectures();
}
