package com.dvdrental.batch.framework.context.listener;


import org.springframework.batch.core.Entity;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;

/**
 * date : 2020/11/26
 * file_name : CommonListener
 * package_name : com.dvdrental.batch.framework.context.listener
 * project_name : dvdrental-partitioning-batch
 * user : gangmin-u
 * Outline :
 * Desction :
 */

public class CommonListener {

    /**
     * Gets the entity information.
     *
     * @param entity the entity
     * @return Entity Information
     */
    protected String getEntityInformation(Entity entity) {
        String result = null;
        if(entity instanceof JobExecution){
            result = getJobInformation((JobExecution)entity);
        }else if(entity instanceof StepExecution){
            result = getStepInformation((StepExecution)entity);
        }
        return result;
    }

    /**
     * Gets the job information.
     *
     * @param jobExecution the job execution
     * @return Job Information
     */
    protected static String getJobInformation(JobExecution jobExecution) {
        StringBuffer buf = new StringBuffer();
        buf.append("[- * ").append(jobExecution.getStatus()).append(" Job / Job Name: ").append(jobExecution.getJobInstance().getJobName())
                .append(" / Job Instance ID: ").append(jobExecution.getJobId())
                .append(" / Job Execution ID: ").append(jobExecution.getId())
                .append(" ]");

        jobExecution.getJobInstance().getId();


        return buf.toString();
    }

    /**
     * Gets the step information.
     *
     * @param stepExecution the step execution
     * @return Step Information
     */
    private String getStepInformation(StepExecution stepExecution) {
        StringBuffer buf = new StringBuffer();
        buf.append("[- * ").append(stepExecution.getStatus()).append(" Step")
                .append(" / Step Name: ").append(stepExecution.getStepName())
                //.append(" / Step Summary: " + stepExecution.getSummary())
                .append(" ]");
        return buf.toString();
    }

}
