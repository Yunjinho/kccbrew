package kr.co.kccbrew.batch;

import org.springframework.batch.core.Job;  
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

//import kr.co.kccbrew.batch.config.BatchConfig;
import kr.co.kccbrew.batch.config.TrMigrationConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Date;

/**
 * @ClassNmae : JobDemo 
 * @Decription : 스프링배치 실행클래스
 * 
 * @   수정일           			    수정자            		 수정내용
 * ============      ==============     ==============
 * 2023-10-25							이세은					   	최초생성
 * @author LEESEEUN
 * @version 1.0
 */

@Component
public class JobDemo {
	
	public void main() throws Exception {
		GenericApplicationContext ctx =
				new AnnotationConfigApplicationContext(TrMigrationConfig.class);

		Job job = ctx.getBean(Job.class);
		JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
		JobParameters jobParameters = new JobParametersBuilder()
				.addDate("date", new Date())
				.toJobParameters();
		jobLauncher.run(job, jobParameters);

		ctx.close();
	}
}
