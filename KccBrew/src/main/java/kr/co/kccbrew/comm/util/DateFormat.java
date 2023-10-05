package kr.co.kccbrew.comm.util;

import java.sql.Date; 
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class DateFormat {

	/**
	 * 날짜형식의 문자열을 sql.Date타입으로 변환한다.
	 * 
	 * @param String date 날짜형식의 문자열
	 * @return Date sqlDate sql.Date타입의 날짜
	 * @throws Exception e
	 */
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

	/**
	 * 이번년도의 첫날을 sql.Date타입으로 반환한다.
	 * 
	 * @return Date sqlFirstDayOfYear 이번년도의 첫날의 sql.Date타입
	 */
	public Date getFirstDayOfYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		java.util.Date firstDayOfYear = calendar.getTime();
		
		Date sqlFirstDayOfYear = new Date(firstDayOfYear.getTime());
		return sqlFirstDayOfYear;
	}

	/**
	 * 이번년도의 마지막날을 sql.Date타입으로 반환한다.
	 * 
	 * @return Date lastDayOfYear 이번년도의 마지막날의 sql.Date타입
	 */
	public Date getLastDayOfYear() {
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.set(currentYear + 1, Calendar.JANUARY, 1);
		calendar.add(Calendar.DATE, -1);
		java.util.Date lastDayOfYear = calendar.getTime();
		
		Date sqlLastDayOfYear = new Date(lastDayOfYear.getTime());
		return sqlLastDayOfYear;
	}



}
