package com._2carrot84.kstd.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyConfig {
	@Autowired
	private Environment env;

	@Value("${spring.profiles.active}")
	private String profiles;

	private String getActiveProfiles() {
		return env.getActiveProfiles().length == 0 ? profiles : env.getActiveProfiles()[0];
	}

	public String generate(String key) {
		return getActiveProfiles() + ":" + key;
	}
}
