package com.acme.batch.commons.config;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.acme.batch.commons.annotations.TestOnly;

@Configuration
@TestOnly
public class BatchConfigInMemory implements BatchConfig {

    @Override
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Override
    @Bean
    public MapJobRepositoryFactoryBean jobRepository(PlatformTransactionManager transactionManager) {
        MapJobRepositoryFactoryBean fb = new MapJobRepositoryFactoryBean(transactionManager);
        return fb;
    }
    
    @Override
    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }
}
