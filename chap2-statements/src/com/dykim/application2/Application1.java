package com.dykim.application2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.dykim.common.JDBCTemplate.getConnection;

public class Application1 {
	
	public static void main(String[] args) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		
		PreparedStatement pset = null;
		ResultSet rset = null;
		
		try {
			//미리쿼리문을 준비해 둠.
			pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				System.out.println(rset.getString("EMP_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		
}
