package kr.co.kccbrew.comm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
@Service
public class S3Service {
		private final AmazonS3Client amazonS3Client;

//	    @Value("${cloud.aws.s3.bucketName}")
	    private String bucket="kccbrewbucket";


	    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
	        File uploadFile = convert(multipartFile)
	                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

	        return upload(uploadFile, dirName);
	    }

	    private String upload(File uploadFile, String dirName) {
	    	UUID uuid=UUID.randomUUID();
	        String fileName = dirName + "/" +uuid+"_"+ uploadFile.getName();
	        String uploadImageUrl = putS3(uploadFile, fileName);
	        removeNewFile(uploadFile);
	        return uploadImageUrl;
	    }

	    private String putS3(File uploadFile, String fileName) {
	        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
	        return amazonS3Client.getUrl(bucket, fileName).toString();
	    }

	    private void removeNewFile(File targetFile) {
	        if (targetFile.delete()) {
	            log.info("파일이 삭제되었습니다.");
	        } else {
	            log.info("파일이 삭제되지 못했습니다.");
	        }
	    }

	    private Optional<File> convert(MultipartFile file) throws IOException {
	        File convertFile = new File(file.getOriginalFilename());
	        System.out.println("convertFile = " + convertFile);
	        if (convertFile.exists()) {
	            convertFile.delete(); // 기존 파일 삭제
	        }
	        if(convertFile.createNewFile()) {
	            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
	                fos.write(file.getBytes());
	            }
	            return Optional.of(convertFile);
	        }
	        return Optional.empty();
	    }
	    public void deleteFile(String fileName){
	        DeleteObjectRequest request = new DeleteObjectRequest(bucket, fileName);
	        amazonS3Client.deleteObject(request);
	    }
	}
