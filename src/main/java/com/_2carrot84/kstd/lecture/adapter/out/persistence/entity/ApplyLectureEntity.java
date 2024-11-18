package com._2carrot84.kstd.lecture.adapter.out.persistence.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "apply_lecture")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyLectureEntity {
	@EmbeddedId
	private ApplyLectureId applyLectureId;
	@Column
	@CreationTimestamp
	private LocalDateTime applyDateTime;
	@MapsId("lectureId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lecture_id")
	private LectureEntity lecture;
}
