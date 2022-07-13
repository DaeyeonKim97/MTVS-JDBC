package com.dykim.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class Connection1 {
	public static void main(String[] args) {
		//환경변수 설정
		String filePath = "src/com/dykim/section01/connection/jdbc-config.properties"; //build path시작 디렉토리 확인
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String oracleDriver = prop.getProperty("ORACLE_DRIVER");
		String url = prop.getProperty("ORACLE_URL");
		String user = prop.getProperty("ORACLE_USER");
		String password = prop.getProperty("ORACLE_PWD");
		
		
		
		Connection con = null; //데이터베이스 접속 정보를 저장하고 있는 객체
		
		//Driver 연결 
		try {
			// 사용할 드라이버 등록
			Class.forName(oracleDriver); 
			/*Class는 클래스에 대한 모든 메타정보를 가지고 있음 
			 * 런타임 당시에 해당 코드를 만났을 때 동적으로 로드할 수 있도록 하는 메세지 -> Class.forname() */
			
			
			//url, user, password 3가지 정보로 DB접속 가능한 연결 객체 생성
			con = DriverManager.getConnection(url,user,password);
			
			System.out.println("con : "+con);
			
		} catch (ClassNotFoundException e) { //해당 클래스가 없는 에러가 발생했을 경우
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close(); //마지막에 닫아줌 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
