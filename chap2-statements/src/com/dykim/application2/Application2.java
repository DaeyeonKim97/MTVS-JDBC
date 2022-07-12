package com.dykim.application2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.dykim.common.JDBCTemplate.getConnection;

public class Application2 {
	
	public static void main(String[] args) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		
		PreparedStatement pset = null;
		ResultSet rset = null;
		
		String empId = "202";
		
		try {
			//미리쿼리문을 준비해 둠.
			pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?"); //물음표를 통해 값을 넣어줄 수 있다.
			//1.편하다 2.빠르다 3.보안에 우수하다.
			pstmt.setString(1, empId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				System.out.println(rset.getString("EMP_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		
}
