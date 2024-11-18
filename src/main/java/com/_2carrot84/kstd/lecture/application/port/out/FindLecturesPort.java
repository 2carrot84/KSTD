package com._2carrot84.kstd.lecture.application.port.out;

import java.time.LocalDateTime;
import java.util.List;

import com._2carrot84.kstd.lecture.domain.Lecture;

public interface FindLecturesPort {
	List<Lecture> findAll();
	List<Lecture> findAllById(List<Long> ids);
	Lecture findById(Long id);
	List<Lecture> findByIdWithApplicants(Long id);
	List<Lecture> findAllBy(LocalDateTime fromStartDateTime, LocalDateTime toStartDateTime);
}
