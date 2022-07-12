package com.dykim.application2;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.dykim.model.EmployeeDTO;

import static com.dykim.common.JDBCTemplate.getConnection;
import static com.dykim.common.JDBCTemplate.close;

public class Application5 {
	
	public static void main(String[] args) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pset = null;
		ResultSet rset = null;
		EmployeeDTO selectEmp = null;
		List<EmployeeDTO> empList = new ArrayList<>();
		
		
		System.out.print("조회할 이름의 성을 입력하세요 : ");
		Scanner scan = new Scanner(System.in);
		String empFirst = scan.next();
		
		try {
			//미리쿼리문을 준비해 둠.
			pstmt = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE ? ||'%' "); 
			//?는 자동으로 문자열로 치환하기 때문에 연결연산자를 이용해야 한다.
			pstmt.setString(1, empFirst);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				selectEmp = new EmployeeDTO();
				
				selectEmp.setEmpId(rset.getString("EMP_ID"));
				selectEmp.setEmpName(rset.getString("EMP_NAME"));
				selectEmp.setSalary(rset.getInt("SALARY"));
				selectEmp.setHireDate(rset.getString("HIRE_DATE"));
				
				empList.add(selectEmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(con);
		}
		
		for(EmployeeDTO emp:empList) {
			System.out.println(emp);
		}
		
	}
		
}
