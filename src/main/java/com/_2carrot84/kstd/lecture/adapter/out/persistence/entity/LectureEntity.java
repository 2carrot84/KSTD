package com._2carrot84.kstd.lecture.adapter.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lecture")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private LocalDateTime startDateTime;
	@Column
	private String speaker;
	@Column
	private String room;
	@Column
	private int maxApplicantCount;
	@Column
	private String content;
	@OneToMany(mappedBy = "lecture", fetch = FetchType.LAZY)
	@Builder.Default
	private List<ApplyLectureEntity> applicants = new ArrayList<>();
}
