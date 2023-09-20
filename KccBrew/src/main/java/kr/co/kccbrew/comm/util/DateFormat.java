package kr.co.kccbrew.comm.util;

import java.sql.Date; 
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class DateFormat {
	
	/*문자열 -> sql.Date타입 형변환*/
	public Date stringToSqlDate(String date) {
		  try {
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date parsedDate = dateFormat.parse(date);
	            Date sqlDate = new Date(parsedDate.getTime());
	            
	            System.out.println("DateFormat.stringToSqlDate");
	            System.out.println("sqlDate: " + sqlDate);
	            
	            return sqlDate;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	}

}
