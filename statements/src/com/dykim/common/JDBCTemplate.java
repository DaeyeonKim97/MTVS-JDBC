package com.dykim.common;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	public static Connection getConnection() {
		Connection con = null;
		String configPath = "config/config.properties"; //빌드패스에서 config를 기본 참조 경로로 포함시켜서 가능
		Properties prop = new Properties();
	
		
		try {
			prop.load(new FileReader(configPath));
			String oracleDriver = prop.getProperty("ORACLE_DRIVER");
			String url = prop.getProperty("ORACLE_URL");
			String user = prop.getProperty("ORACLE_USER");
			String password = prop.getProperty("ORACLE_PWD");
			
			Class.forName(oracleDriver);
			
			con = DriverManager.getConnection(url,user,password);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return con;
	}
	
	public static void close(Connection con) {
		try {
			if(con != null && !con.isClosed()) { 
				//순서가 중요하다. null부터 체크 순서가 바뀌면 NULLPOINTER EXCEPTION가 생길 수 있다. 
				//왜냐하면 null의 경우isClose시 NPE가 발생하기 때문 
				con.close();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
