package com.capg.CustomValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateOfBirthValidator implements ConstraintValidator<DOBValidation, LocalDate>
{
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext cxt) {
        LocalDate present = LocalDate.now();
        if(present.compareTo(dateOfBirth)<0)
            return false;
        else
            return true;
    }
}