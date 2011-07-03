package com.acme.batch.commons.tasklets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;

@Configuration
public class DecompressTaskletConfig {

    @Bean(name = "decompressTasklet")
    @Scope("step")
    public DecompressTasklet decompressTasklet(@Value("#{jobParameters['inputResource']}") Resource inputResource,
            @Value("#{jobParameters['targetDirectory']}") String targetDirectory,
            @Value("#{jobParameters['targetFile']}") String targetFile) {
        return new DecompressTasklet(inputResource, targetDirectory, targetFile);
    }
}
