package com.acme.batch.commons.config;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

public interface BatchConfig {

    PlatformTransactionManager transactionManager();

    MapJobRepositoryFactoryBean jobRepository(PlatformTransactionManager transactionManager);

    JobLauncher jobLauncher(JobRepository jobRepository);

}