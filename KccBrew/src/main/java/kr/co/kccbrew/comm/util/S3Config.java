package kr.co.kccbrew.comm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Configuration
public class S3Config {
    
    @Value("${cloud.aws.credentials.accessKey}")
    public String accessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    public String secretKey;
    @Value("${cloud.aws.s3.bucketName}")
    public String bucketName;
    @Value("${cloud.aws.region.static}")
    public String region;
    
    
    @Bean
    public AmazonS3 amazonS3() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }
}
