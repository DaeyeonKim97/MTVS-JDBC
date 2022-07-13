package com.dykim.application;

import static com.dykim.common.JDBCTemplate.getConnection;
import static com.dykim.common.JDBCTemplate.close;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Application1 {

	public static void main(String[] args) {
		Connection con = getConnection();
		
		/*쿼리문을 저장하고 실행하는 기능을 하는 용도의 인터페이스*/
		Statement stmt = null;
		
		/*select 결과 집합을 받아 올 용도의 인터페이스 */
		ResultSet rset = null;
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE"); //문자열 리터럴 형태로 SQL문을 전달함.
			
			while(rset.next()) {
				System.out.println(rset.getString("EMP_ID")+ ", "+rset.getString("EMP_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con);
			close(stmt);
			close(rset);
		}
	}

}
