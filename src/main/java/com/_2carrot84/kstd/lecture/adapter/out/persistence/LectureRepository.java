package com._2carrot84.kstd.lecture.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.LectureEntity;

public interface LectureRepository extends JpaRepository<LectureEntity, Long>, LectureRepositoryCustom {
}
