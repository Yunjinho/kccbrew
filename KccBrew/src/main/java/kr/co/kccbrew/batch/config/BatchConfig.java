package kr.co.kccbrew.batch.config;




import java.io.File;

import javax.naming.Context;
import javax.swing.text.Document;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;

import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

/**
 * @ClassNmae : BatchConfig 
 * @Decription : 스프링배치 설정클래스
 * 
 * @   수정일           			    수정자            		 				수정내용
 * ============      ==============     ============================
 * 2023-10-25							이세은					   					최초생성
 * 2023-10-27							이세은						누락데이터선별 기능구현
 * @author LEESEEUN
 * @version 1.0
 */

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
//@ImportResource({"webapp/WEB-INF/spring/database.xml"})
@ComponentScan("kr.co.kccbrew.batch")
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloWorldJob() {
        return jobBuilderFactory.get("helloWorldJob")
                .incrementer(new RunIdIncrementer())
                .start(helloWorldStep())
                .build();
    }

    @JobScope
    @Bean
    public Step helloWorldStep() {
        return stepBuilderFactory.get("helloWorldStep")
                .tasklet(helloWorldTasklet())
                .build();
    }

    @StepScope
    @Bean
    public Tasklet helloWorldTasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Hello World Spring Batch");
                return RepeatStatus.FINISHED;
            }
        };
    }
}