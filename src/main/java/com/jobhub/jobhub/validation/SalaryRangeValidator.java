
package com.jobhub.jobhub.validation;

import com.jobhub.jobhub.dto.JobRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SalaryRangeValidator
        implements ConstraintValidator<ValidSalaryRange, JobRequestDto> {

    @Override
    public boolean isValid(
            JobRequestDto dto,
            ConstraintValidatorContext context) {

        if (dto == null) {
            return true;
        }

        Double min = dto.getSalaryMin();
        Double max = dto.getSalaryMax();

        // Allow either salary field to be omitted.
        if (min == null || max == null) {
            return true;
        }

        return min <= max;
    }
}
