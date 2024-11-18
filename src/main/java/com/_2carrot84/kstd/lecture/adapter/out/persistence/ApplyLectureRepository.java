package com._2carrot84.kstd.lecture.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.ApplyLectureEntity;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.ApplyLectureId;

public interface ApplyLectureRepository
	extends JpaRepository<ApplyLectureEntity, ApplyLectureId>, ApplyLectureRepositoryCustom {
}
