package kr.co.kccbrew.schdlMng.validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintValidator; 
import javax.validation.ConstraintValidatorContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



public class LegalHolidayValidator implements ConstraintValidator<LegalHoliday, Map<String, Date>>{



	public void initialize(LegalHoliday constraintAnnotation) {
	}



	public boolean isValid(Map<String, Date> period, ConstraintValidatorContext context) {
		Date startDate =  period.get("startDate");
		Date endDate = period.get("endDate");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);

		while (!calendar.getTime().after(endDate)) {
			Date currentDate = new Date(calendar.getTimeInMillis());

			// 주말 또는 공휴일인지 확인
			if (isWeekend(currentDate) || isHoliday(currentDate)) {
				return false;
			}
			calendar.add(Calendar.DAY_OF_MONTH, 1); // 다음 날짜로 이동
		}
		return true;
	}



	/*주말 확인*/
	private static boolean isWeekend(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
	}



	/*공휴일 확인*/
	private static boolean isHoliday(Date date) {
		String responseBody = HolidayAPIExample();
		List<String> legalHolidays = parseHolidayDates(responseBody);

		// SimpleDateFormat을 사용하여 String을 java.util.Date로 파싱
		for(String legalHoliday : legalHolidays) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			try {
				java.util.Date parsedDate = dateFormat.parse(legalHoliday);
				java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
				if(sqlDate.equals(date)) {
					return true;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return false;
	}



	/*공휴일 api*/
	private static String HolidayAPIExample() {
		String serviceKey = "TPRDLoRYlZ1EtuCh8%2B8NtgCnSYxEpm%2BKTzGG56mdcU24ZF%2FDMD2mYX3VAKVdFtjC0LHaOOPfEaYQ%2F5%2FAijakOw%3D%3D"; 
		int year = 2023; // 연도
		int numOfRows = 100;

		try {
			// API 요청 URL 생성
			String apiUrl = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";
			String urlParameters = "?solYear=" + year + "&numOfRows=" + numOfRows + "&ServiceKey=" + serviceKey;
			URL url = new URL(apiUrl + urlParameters);

			// HTTP 연결 설정
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// 응답 데이터 읽기
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// 응답 데이터 파싱하여 날짜 리스트 추출
			String responseBody = response.toString();

			// 연결 종료
			connection.disconnect();

			return responseBody;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



	/*응답 api 데이터 파싱*/
	private static List<String> parseHolidayDates(String xmlResponse) {
		List<String> dateList = new ArrayList<>();

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new org.xml.sax.InputSource(new java.io.StringReader(xmlResponse)));

			NodeList itemList = doc.getElementsByTagName("item");
			System.out.println("itemList개수: " + itemList.getLength());
			for (int i = 0; i < itemList.getLength(); i++) {
				Element item = (Element) itemList.item(i);
				String locdate = item.getElementsByTagName("locdate").item(0).getTextContent();
				dateList.add(locdate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
/*		// 날짜 리스트 출력
		for (String date : dateList) {
			System.out.println("Holiday Date: " + date);
		}*/

		return dateList;
	}
}
