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

@Configuration
public class S3Config {
	
    //@Value("${cloud.aws.credentials.accessKey}")
    private String accessKey="AKIA5PQESN3XLEESCDFT";
    //@Value("${cloud.aws.credentials.secretKey}")
    private String secretKey="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";
    //@Value("${cloud.aws.s3.bucketName}")
    private String bucketName="kccbrewbucket";
   // @Value("${cloud.aws.region.static}")
    private String region="ap-northeast-2";

/*    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;
    @Value("${cloud.aws.region.static}")
    private String region;*/

    @Autowired
    public AmazonS3 amazonS3() {
    	System.out.println(accessKey);
    	System.out.println(secretKey);
    	System.out.println(bucketName);
    	System.out.println(region);
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }
    
    
}
