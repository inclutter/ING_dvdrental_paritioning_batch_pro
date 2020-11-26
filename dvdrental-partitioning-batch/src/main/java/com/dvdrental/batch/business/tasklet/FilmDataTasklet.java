package com.dvdrental.batch.business.tasklet;

import com.dvdrental.batch.business.service.FilmService;
import com.dvdrental.batch.framework.context.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * date : 2020/11/26
 * file_name : FilmDataTasklet
 * package_name : com.dvdrental.batch.business.tasklet
 * project_name : dvdrental-partitioning-batch
 * user : gangmin-u
 * Outline :
 * Desction :
 */

@Slf4j
@Component
public class FilmDataTasklet implements Tasklet {

    @Autowired
    private FilmService filmService;


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        try {
            filmService.deleteFilmInfo();
            chunkContext.getStepContext().getStepExecution().setExitStatus(ExitStatus.COMPLETED);
        } catch (Exception e) {
            chunkContext.getStepContext().getStepExecution().setExitStatus(ExitStatus.FAILED);
            chunkContext.getStepContext().getStepExecution().addFailureException(e);	// Exception
            if (log.isErrorEnabled()){
                log.error("Name : {}, : {}", this.getClass().getName(), e.getMessage());
            }
            throw new BizException("Tasklet Error", e.getMessage());
        }
        return RepeatStatus.FINISHED;
    }
}
