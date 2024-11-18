package com._2carrot84.kstd.lecture.application.port.in;

import java.time.LocalDateTime;

import com._2carrot84.kstd.common.validation.SelfValidating;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateLectureCommand extends SelfValidating<CreateLectureCommand> {
	@NotNull
	private final String name;
	@NotNull
	private final String speaker;
	@NotNull
	private final String room;
	@NotNull
	private final String content;
	@NotNull
	private final int applicantCount;
	@NotNull
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$", message = "날짜 타입(yyyy-MM-dd HH:mm)에 맞춰야 합니다.")
	private final String startDateTime;

	@Builder
	private CreateLectureCommand(String name, String speaker, String room, String content, int applicantCount,
		String startDateTime) {
		this.name = name;
		this.speaker = speaker;
		this.room = room;
		this.content = content;
		this.applicantCount = applicantCount;
		this.startDateTime = startDateTime;
		this.validateSelf();
	}
}
