package com.dvdrental.batch.framework.context.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;

import java.util.Collection;
import java.util.Iterator;

/**
 * date : 2020/11/26
 * file_name : CommonJobListener
 * package_name : com.dvdrental.batch.framework.context.listener
 * project_name : dvdrental-partitioning-batch
 * user : gangmin-u
 * Outline :
 * Desction :
 */
@Slf4j
public class CommonJobListener extends CommonListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (log.isInfoEnabled()){
            log.info("[==========================================================================]");
            log.info(getJobInformation(jobExecution));
            log.info("[==========================================================================]");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (log.isInfoEnabled()){
            log.info("[==========================================================================]");
            log.info(getJobInformation(jobExecution));
            log.info("* -------------------------------------------------------------------------");
        }
        long duration = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime() ;
        Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
        Iterator<StepExecution> it = stepExecutions.iterator();

        StepExecution stepExe = null;
        while (it.hasNext()) {
            stepExe = it.next();
            if (log.isInfoEnabled()){
                log.info(String.format("* Step name         : %10s / Step Execution ID : %10d" , stepExe.getStepName() , stepExe.getId()));
                log.info("* Execution Context : " + stepExe.getExecutionContext());
                log.info("* -------------------------------------------------------------------------");
                log.info(String.format("          Read Count   : %10d / Read skip count    : %10d" , stepExe.getReadCount(), stepExe.getReadSkipCount()));
                log.info(String.format("                                      Process skip count : %10d ", stepExe.getProcessSkipCount()));
                log.info(String.format("          Write count  : %10d / Write skip count   : %10d ", stepExe.getWriteCount(), stepExe.getWriteSkipCount()));
                log.info(String.format("          Commit count : %10d / Rollback count     : %10d", stepExe.getCommitCount(), stepExe.getRollbackCount()));
                log.info(String.format("          Status       : %10s / Exit status        : %10s" , stepExe.getStatus(), stepExe.getExitStatus().getExitCode()));

                log.info("* -------------------------------------------------------------------------");
            }

        }

        long minute = (duration/1000)/60;
        long second = (duration/1000)%60;

        log.info("* Execution time : " + minute + " minute "+second+" second");
        log.info("* Job status     : " + jobExecution.getStatus());
        log.info("[==========================================================================]");
    }
}
