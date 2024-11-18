package com._2carrot84.kstd.common.validation;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public abstract class SelfValidating<T> {

	private final Validator validator;

	public SelfValidating() {
		try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
			validator = factory.getValidator();
		}
	}

	protected void validateSelf() {
		Set<ConstraintViolation<T>> violations = validator.validate((T) this);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
	}
}
