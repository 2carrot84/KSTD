package com._2carrot84.kstd.lecture.adapter.out.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class ApplyLectureId implements Serializable {
	@Column
	private Long lectureId;
	@Column(length = 5)
	private String employeeId;

	@Builder
	private ApplyLectureId(Long lectureId, String employeeId) {
		this.lectureId = lectureId;
		this.employeeId = employeeId;
	}
}