package com.acme.batch.commons.tasklets;

import static org.springframework.batch.test.AssertFile.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class DecompressTaskletConfigTest {
    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired
    @Qualifier("unzip-job")
    private Job job;
    
    @Test
    public void unzip() throws Exception {
        String targetDirectory = "target/" + getClass().getSimpleName();
        String targetFile = "products.txt";
        Resource expectedFile = new ClassPathResource("input/products.txt");
        Resource outputFile = new FileSystemResource(targetDirectory + "/" + targetFile);
        JobParameters parms = new JobParametersBuilder() //
            .addString("inputResource", "classpath:input/products.zip")
            .addString("targetDirectory", targetDirectory)
            .addString("targetFile", targetFile)
            .toJobParameters();
        jobLauncher.run(job, parms);
        assertFileEquals(expectedFile, outputFile);

    }

}
