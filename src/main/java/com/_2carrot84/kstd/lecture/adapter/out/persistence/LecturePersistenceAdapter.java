package com._2carrot84.kstd.lecture.adapter.out.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com._2carrot84.kstd.lecture.application.port.out.CreateLecturesPort;
import com._2carrot84.kstd.lecture.application.port.out.FindLecturesPort;
import com._2carrot84.kstd.lecture.domain.Lecture;
import com._2carrot84.kstd.lecture.domain.mapper.LectureMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements FindLecturesPort, CreateLecturesPort {

	private final LectureRepository lectureRepository;

	@Override
	public List<Lecture> findAll() {
		return lectureRepository.findAll()
			.stream()
			.map(LectureMapper::mapToDomain)
			.toList();
	}

	@Override
	public List<Lecture> findAllById(List<Long> ids) {
		return lectureRepository.findAllById(ids)
			.stream()
			.map(LectureMapper::mapToDomain)
			.toList();
	}

	@Override
	public Lecture findById(Long id) {
		return LectureMapper.mapToDomain(lectureRepository.findById(id)
			.orElseThrow(EntityNotFoundException::new));
	}

	@Override
	public List<Lecture> findByIdWithApplicants(Long id) {
		return lectureRepository.findByIdWithApplicants(id)
			.stream()
			.map(LectureMapper::mapToDomainWithApplicants)
			.toList();
	}

	@Override
	public List<Lecture> findAllBy(LocalDateTime fromStartDateTime, LocalDateTime toStartDateTime) {
		return lectureRepository.findDynamicQuery(fromStartDateTime, toStartDateTime)
			.stream()
			.map(LectureMapper::mapToDomain)
			.toList();
	}

	@Override
	public Lecture create(Lecture lecture) {
		return LectureMapper.mapToDomain(lectureRepository.save(LectureMapper.mapToEntity(lecture)));
	}
}
