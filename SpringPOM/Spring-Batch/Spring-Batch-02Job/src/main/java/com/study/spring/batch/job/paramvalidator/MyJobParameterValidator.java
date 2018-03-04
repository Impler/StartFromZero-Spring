package com.study.spring.batch.job.paramvalidator;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class MyJobParameterValidator implements JobParametersValidator {

    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        String sysCode = parameters.getString("sysCode");
        if(null == sysCode || sysCode.isEmpty()) {
            throw new JobParametersInvalidException("sysCode should not be null or empty");
        }
    }

}
