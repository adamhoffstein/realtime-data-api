package org.adam.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class CheckMultipleStringChoiceValidator implements ConstraintValidator<CheckMultipleStringChoice, String> {

    private String[] validCountryCodes;

    @Override
    public void initialize(CheckMultipleStringChoice constraintAnnotation) {
        this.validCountryCodes = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if (object == null) {
            return true;
        }
        boolean isValid;

        isValid = Arrays.stream(validCountryCodes).toList().contains(object);


        if (!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate(
                            "{com.gogox.validators.CheckMultipleStringChoice.message}"
                    )
                    .addConstraintViolation();
        }

        return isValid;
    }
}