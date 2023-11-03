package kr.co.kccbrew.batch.config;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.batch.AsLogVo;
import kr.co.kccbrew.batch.MybatisItemProcessor;
import kr.co.kccbrew.batch.dao.IBatchRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

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

@Slf4j
@Data
@Configuration
@EnableBatchProcessing
@ComponentScan("kr.co.kccbrew.batch")
@Component
public class TrMigrationConfig extends DefaultBatchConfigurer{

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired MybatisItemProcessor mybatisItemProcessor;
	
	@Autowired DataSource dataSource;
	@Autowired ApplicationContext applicationContext;
	
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:config/modelConfig.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/**/*.xml"));

		return sqlSessionFactoryBean.getObject();
	}
	
/*job*/
	@Bean
	    public Job mybatisBatchJob() throws Exception {
	        return jobBuilderFactory.get("selectOmittedDataJob")
	            .start(mybatisStep())
	            .build();
	    }

	 /*step*/
	 @JobScope
	 public Step mybatisStep() throws Exception {
	     return stepBuilderFactory.get("mybatisStep")
	         .<AsLogVo, AsMngVo>chunk(1000)
	         .reader(mybatisItemReader(sqlSessionFactory()))
	         .processor(mybatisItemProcessor)
	         .writer(mybatisItemWriter(sqlSessionFactory()))
	         .build();
	 }
	    
	    /*reader*/
	    @StepScope
	    public MyBatisPagingItemReader<AsLogVo> mybatisItemReader(SqlSessionFactory sqlSessionFactory) {
	        MyBatisPagingItemReader<AsLogVo> reader = new MyBatisPagingItemReader<>();
	        reader.setSqlSessionFactory(sqlSessionFactory);
	        reader.setQueryId("mapper.batch.batchMapper.selectRecentAsLog"); 
	        reader.setPageSize(1000); 
	        return reader;
	    }

	    /*processor*/
/*	    @Bean
	    @StepScope
	    public ItemProcessor<AsLogVo, AsMngVo> mybatisItemProcessor() {
	        return asLogVo -> {
	        	log.info("asLogVo: " + asLogVo);
	        	AsMngVo asMngVo = new AsMngVo(asLogVo);
	            return asMngVo;
	        };
	    }*/
	    
	    
	    /*writer*/
	    @StepScope
	    public MyBatisBatchItemWriter<AsMngVo> mybatisItemWriter(SqlSessionFactory sqlSessionFactory) {
	        MyBatisBatchItemWriter<AsMngVo> writer = new MyBatisBatchItemWriter<>();
	        writer.setSqlSessionFactory(sqlSessionFactory);
	        writer.setStatementId("mapper.batch.batchMapper.updateOmissionResult");
	        return writer;
	    }

}
