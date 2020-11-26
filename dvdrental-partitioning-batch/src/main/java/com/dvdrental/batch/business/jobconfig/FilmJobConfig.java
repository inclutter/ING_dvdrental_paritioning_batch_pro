package com.dvdrental.batch.business.jobconfig;

import com.dvdrental.batch.business.partition.FilmPartition;
import com.dvdrental.batch.business.tasklet.FilmDataTasklet;
import com.dvdrental.batch.business.vo.FilmVO;
import com.dvdrental.batch.business.writer.FilmWriter;
import com.dvdrental.batch.framework.common.dispatcher.CommonDispatcherJob;
import com.dvdrental.batch.framework.context.listener.CommonJobListener;
import com.dvdrental.batch.framework.context.listener.CommonStepListener;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * date : 2020/11/26
 * file_name : FilmJobConfig
 * package_name : com.dvdrental.batch.business.jobconfig
 * project_name : dvdrental-partitioning-batch
 * user : gangmin-u
 * Outline :
 * Desction :
 */

@Configuration
public class FilmJobConfig {
    private static final String JOB_NAME = "film-sync";
    private static final String STEP_NAME1 = "FilmDataInitStep";
    private static final String STEP_NAME2 = "FilmDataSyncStep";

    @Autowired
    ApplicationContext context;

    @Autowired
    private  JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CommonStepListener commonStepListener;

    @Autowired
    private CommonJobListener commonJobListener;

    @Autowired
    private FilmWriter filmWriter;

    @Autowired
    private FilmDataTasklet filmDataTasklet;


    @Bean
    public CommonDispatcherJob dispatcherFilmSyncJob() throws DuplicateJobException {
        CommonDispatcherJob dispatcherJob = new CommonDispatcherJob();
        dispatcherJob.setJob(FilmSyncJob());
        return dispatcherJob;
    }

    @Bean
    public Job FilmSyncJob() throws org.springframework.batch.core.configuration.DuplicateJobException {
        Job job = jobBuilderFactory.get(JOB_NAME)
                .listener(commonJobListener)
                .start(filmDataInitStep())
                .next(filmSynctStep())
                .build();
        return job;

    }

    @Bean
    public Step filmDataInitStep() {
        return stepBuilderFactory.get(STEP_NAME1)
                .tasklet(filmDataTasklet)
                .listener(commonStepListener)
                .build();
    }

    @Bean
    public Step filmSynctStep() {
        return stepBuilderFactory.get(STEP_NAME2)
                .partitioner(filmSlaveStep().getName(), filmPartitioner())
                .partitionHandler(filmMasterSlaveHandler())
                .build();
    }


    @Bean
    public Step filmSlaveStep() {
        return stepBuilderFactory.get(STEP_NAME2 + "SlaveStep")
                .<FilmVO, FilmVO>chunk(100)
                .reader(filmPagingReader())
                .writer(filmWriter)
                .listener(commonStepListener)
                .build();
    }

    @Bean
    @StepScope
    public MyBatisPagingItemReader<FilmVO> filmPagingReader(){
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.getBean(new StringBuffer().append("sqlSessionFactory").toString());
        Map<String, Object> parameterValues = new HashMap<String, Object>();

        MyBatisPagingItemReader<FilmVO> reader = new MyBatisPagingItemReader<>();
        reader.setQueryId("com.dvdrental.batch.business.mapper.FilmMapper.selectFilmList");
        reader.setSqlSessionFactory(sqlSessionFactory);
        reader.setPageSize(1000);
        reader.setParameterValues(parameterValues);
        return reader;
    }

    @Bean
    public FilmPartition filmPartitioner() {
        return new FilmPartition();
    }

    @Bean
    public PartitionHandler filmMasterSlaveHandler() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setGridSize(100);
        handler.setTaskExecutor(taskExecutor);
        handler.setStep(filmSlaveStep());

        try {
            handler.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handler;
    }
}
