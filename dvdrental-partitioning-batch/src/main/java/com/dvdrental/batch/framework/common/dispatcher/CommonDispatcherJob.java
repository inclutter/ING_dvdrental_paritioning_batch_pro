package com.dvdrental.batch.framework.common.dispatcher;

import com.dvdrental.batch.framework.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * date : 2020/11/26
 * file_name : CommonDispatcherJob
 * package_name : com.dvdrental.batch.framework.common
 * project_name : dvdrental-partitioning-batch
 * user : gangmin-u
 * Outline :
 * Desction :
 */

@Slf4j
public class CommonDispatcherJob {
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;


    public void setJob(Job job) {
        this.job = job;
    }

    public void process() {
        try {
            JobParameters jobParameters =
                    new JobParametersBuilder()
                            .addLong("time", System.currentTimeMillis()).toJobParameters();

            jobLauncher.run(job, jobParameters);

        } catch (JobExecutionAlreadyRunningException e) {
            log.error(e.getMessage());
        } catch (JobRestartException e) {
            log.error(e.getMessage());
        } catch (JobInstanceAlreadyCompleteException e) {
            log.error(e.getMessage());
        } catch (JobParametersInvalidException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void process(Map<String, String> param) {
        Map<String, JobParameter> map = new HashMap<String, JobParameter>();

        try {
            String jobStartTime = DateUtil.getTime("yyyyMMddHHmmssSSS");
            map.put("jobStartTime", new JobParameter(jobStartTime));

            for (String key:param.keySet()) {
                String value = param.get(key);
                map.put(key, new JobParameter(value));
            }

            jobLauncher.run(job, new JobParameters(map));

        } catch (JobExecutionAlreadyRunningException e) {
            log.error(e.getMessage());
        } catch (JobRestartException e) {
            log.error(e.getMessage());
        } catch (JobInstanceAlreadyCompleteException e) {
            log.error(e.getMessage());
        } catch (JobParametersInvalidException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
