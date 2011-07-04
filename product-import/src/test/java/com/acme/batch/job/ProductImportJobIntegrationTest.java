package com.acme.batch.job;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.springframework.batch.core.ExitStatus.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductImportJobIntegrationTest {

    @Autowired
    private JobLauncher jobLauncher;
    @Resource(name = "import-products")
    private Job job;

    @Test
    public void importProducts() throws Exception {
        String targetDirectory = "target/" + getClass().getSimpleName();
        String targetFile = "products.txt";
        ClassPathResource expectedFile = new ClassPathResource("input/products.txt");
        FileSystemResource outputFile = new FileSystemResource(targetDirectory + "/" + targetFile);
        JobParameters parms = new JobParametersBuilder() //
            .addString("inputResource", "classpath:input/products.zip")//
            .addString("targetDirectory", targetDirectory)//
            .addString("targetFile", targetFile)//
            .toJobParameters();
        JobExecution jobExec = jobLauncher.run(job, parms);
        assertEquals(COMPLETED, jobExec.getExitStatus());
    }
}
