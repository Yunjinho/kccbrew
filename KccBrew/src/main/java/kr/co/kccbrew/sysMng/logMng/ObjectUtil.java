package kr.co.kccbrew.sysMng.logMng;


import org.springframework.stereotype.Component;

@Component("objectUtil")
public class ObjectUtil {
	public static int divideAndGetQuotient(int dividend, int divisor) {
		return dividend / divisor;
	}

}
