package com.dvdrental.batch.framework.context.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;

/**
 * date : 2020/11/26
 * file_name : CommonStepListener
 * package_name : com.dvdrental.batch.framework.context.listener
 * project_name : dvdrental-partitioning-batch
 * user : gangmin-u
 * Outline :
 * Desction :
 */
@Slf4j
public class CommonStepListener extends CommonListener {

    /**
     * Before step.
     *
     * @param stepExecution the step execution
     */
    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        if (log.isErrorEnabled()){
            log.info("=======================================================");
            log.info(getEntityInformation(stepExecution));
            log.info("=======================================================");
        }
    }

    /**
     * After step.
     *
     * @param stepExecution the step execution
     * @return Exit Status
     */
    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (log.isErrorEnabled()){
            log.info("=======================================================");
            log.info(getEntityInformation(stepExecution));
            log.info("=======================================================");
        }
        return stepExecution.getExitStatus();
    }

}
