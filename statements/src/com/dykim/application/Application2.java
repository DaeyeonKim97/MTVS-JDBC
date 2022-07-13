package com.dykim.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.dykim.common.JDBCTemplate.getConnection;
import static com.dykim.common.JDBCTemplate.close;

public class Application2 {
	
	public static void main(String[] args) {
		//1.Connection 생성
		Connection con = getConnection();
		
		
		//2. Statement 선언
		Statement stmt = null;
		
		//3. ResultSet 선언
		ResultSet rset = null;
		
		//4. Connection의 createStatement()를 이용한 Statmenet 인스턴스 반환
		try {
			stmt = con.createStatement();
			//5. executeQuery()로 쿼리문 실행하고 결과를 ResultSet 타입으로 반환받음
			//쿼리를 스트링으로 빼주어서 관리가 쉽게 함.
			Scanner scan = new Scanner(System.in);
			System.out.print("조회하려는 사번을 입력해주세요: ");
			String empId = scan.next();
			String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '"+ empId + "'";
			
			rset = stmt.executeQuery(query);
			
			//6. ResultSet에 담긴 결과물을 컬럼 이름을 이용해 꺼내와서 사용 (출력)
			if(rset.next()) {
				System.out.println(rset.getString("EMP_ID")+","+ rset.getString("EMP_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//7. 사용한 자원 반납(Connection, Statement, ResultSet)
			close(rset);
			close(stmt);
			close(con);
		}
		
		
	}
	
}
