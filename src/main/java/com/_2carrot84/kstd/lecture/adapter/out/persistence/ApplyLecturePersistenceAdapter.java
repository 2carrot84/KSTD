package com._2carrot84.kstd.lecture.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.ApplyLectureId;
import com._2carrot84.kstd.lecture.adapter.out.persistence.entity.LectureEntity;
import com._2carrot84.kstd.lecture.application.port.out.CreateApplyLecturePort;
import com._2carrot84.kstd.lecture.application.port.out.DeleteApplyLecturePort;
import com._2carrot84.kstd.lecture.application.port.out.FindApplyLecturePort;
import com._2carrot84.kstd.lecture.domain.ApplyLecture;
import com._2carrot84.kstd.lecture.domain.PopularLecture;
import com._2carrot84.kstd.lecture.domain.mapper.ApplyLectureMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplyLecturePersistenceAdapter implements CreateApplyLecturePort, DeleteApplyLecturePort,
	FindApplyLecturePort {

	private final ApplyLectureRepository applyLectureRepository;
	private final LectureRepository lectureRepository;

	@Override
	public ApplyLecture create(ApplyLecture applyLecture) {
		LectureEntity lecture = lectureRepository.findById(applyLecture.getLectureId())
			.orElseThrow(EntityNotFoundException::new);

		return ApplyLectureMapper.toDomain(
			applyLectureRepository.save(ApplyLectureMapper.toEntity(applyLecture, lecture)));
	}

	@Override
	public void delete(ApplyLecture applyLecture) {
		applyLectureRepository.deleteById(ApplyLectureId.builder()
			.lectureId(applyLecture.getLectureId())
			.employeeId(applyLecture.getEmployeeId())
			.build());
	}

	@Override
	public List<ApplyLecture> findByEmployeeId(String employeeId) {
		return applyLectureRepository.findByEmployeeId(employeeId)
			.stream()
			.map(ApplyLectureMapper::toDomain)
			.toList();
	}

	@Override
	public List<PopularLecture> findPopularLectures() {
		return applyLectureRepository.findPopularLectures()
			.stream()
			.map(ApplyLectureMapper::mapToDomain)
			.toList();
	}
}
