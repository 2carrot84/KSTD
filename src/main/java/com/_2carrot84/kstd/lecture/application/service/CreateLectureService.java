package com._2carrot84.kstd.lecture.application.service;

import org.springframework.stereotype.Service;

import com._2carrot84.kstd.lecture.application.port.in.CreateLectureCommand;
import com._2carrot84.kstd.lecture.application.port.in.CreateLectureUseCase;
import com._2carrot84.kstd.lecture.application.port.out.CreateLecturesPort;
import com._2carrot84.kstd.lecture.domain.Lecture;
import com._2carrot84.kstd.lecture.domain.mapper.LectureMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateLectureService implements CreateLectureUseCase {

	private final CreateLecturesPort createLecturesPort;

	@Override
	public Lecture create(CreateLectureCommand command) {
		return createLecturesPort.create(LectureMapper.mapToDomain(command));
	}
}
