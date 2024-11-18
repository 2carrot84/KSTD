package com._2carrot84.kstd.lecture.adapter.out.persistence;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import com._2carrot84.kstd.common.config.RedisKeyConfig;
import com._2carrot84.kstd.lecture.domain.PopularLecture;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PopularLectureRepositoryRedisImpl implements PopularLectureRepository {

	private static final String POPULAR_LECTURE = "popular:lecture";
	private final RedisTemplate<String, Long> redisTemplate;
	private final RedisKeyConfig redisKey;

	private String getRedisKey() {
		return redisKey.generate(POPULAR_LECTURE);
	}

	@Override
	public void save(Long lectureId, Long applicantCount) {
		ZSetOperations<String, Long> opsForZSet = redisTemplate.opsForZSet();
		opsForZSet.add(getRedisKey(), lectureId, applicantCount);
	}

	@Override
	public void increaseApplicantCount(Long lectureId) {
		ZSetOperations<String, Long> opsForZSet = redisTemplate.opsForZSet();
		opsForZSet.incrementScore(getRedisKey(), lectureId, 1);
	}

	@Override
	public void decreaseApplicantCount(Long lectureId) {
		ZSetOperations<String, Long> opsForZSet = redisTemplate.opsForZSet();
		opsForZSet.incrementScore(getRedisKey(), lectureId, -1);
	}

	@Override
	public void deleteAll() {
		redisTemplate.delete(getRedisKey());
	}

	@Override
	public List<PopularLecture> findAll() {
		ZSetOperations<String, Long> opsForZSet = redisTemplate.opsForZSet();

		Set<ZSetOperations.TypedTuple<Long>> lectures = opsForZSet.reverseRangeWithScores(getRedisKey(), 0, -1);

		return Objects.requireNonNull(lectures).stream()
			.map(lecture -> PopularLecture.builder()
				.lectureId(lecture.getValue())
				.count(Objects.requireNonNull(lecture.getScore()).longValue())
				.build()
			).toList();
	}

	@Override
	public Double findById(Long lectureId) {
		ZSetOperations<String, Long> opsForZSet = redisTemplate.opsForZSet();
		return opsForZSet.score(getRedisKey(), lectureId);
	}
}
