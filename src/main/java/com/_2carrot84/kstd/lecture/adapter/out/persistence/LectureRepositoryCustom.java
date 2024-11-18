package com._2carrot84.kstd.lecture.adapter.out.persistence;

import java.time.LocalDateTime;
import java.util.List;

import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.LectureEntity;

public interface LectureRepositoryCustom {
	List<LectureEntity> findDynamicQuery(LocalDateTime fromStartDateTime, LocalDateTime toStartDateTime);
	List<LectureEntity> findByIdWithApplicants(Long id);
}
