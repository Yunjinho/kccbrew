package kr.co.kccbrew;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JdbcEncryptor {
	
	public static void main(String[] args) {
		
		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
		enc.setPassword("kkccccbbrreeww");
		System.out.println(enc.encrypt("oracle.jdbc.OracleDriver"));
		System.out.println(enc.encrypt("jdbc:oracle:thin:@112.221.241.99:11521:xe"));
		System.out.println(enc.encrypt("hr"));
		System.out.println(enc.encrypt("hr"));
		
	}

	
}
