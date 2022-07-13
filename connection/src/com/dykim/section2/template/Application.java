package com.dykim.section2.template;

import java.sql.Connection;
//import static com.dykim.section2.template.JDBCTemplate.*; // static import, 패키지/클래스 내의 메소드를 인스턴스 메소드를 바로 사용할 수 있게 함


public class Application {

	public static void main(String[] args) {
		Connection con = JDBCTemplate.getConnection();
		System.out.println(con);
		
		JDBCTemplate.close(con);
	}
	

}
