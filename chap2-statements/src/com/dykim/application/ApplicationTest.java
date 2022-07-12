package com.dykim.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dykim.model.EmployeeDTO;
import static com.dykim.common.JDBCTemplate.getConnection;
import static com.dykim.common.JDBCTemplate.close;

public class ApplicationTest {
	public static void main(String[] args) {
		Connection con = getConnection();
		List<EmployeeDTO> empList = new ArrayList<>();
		EmployeeDTO empDTO = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM EMPLOYEE";
		
		try {
			empDTO = new EmployeeDTO();
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				empDTO.setEmpId(rset.getString("EMP_ID"));
				empDTO.setEmpName(rset.getString("EMP_NAME"));
				
				empList.add(empDTO);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con);
			close(stmt);
			close(rset);
		}
		
		
		for(EmployeeDTO emp:empList) {
			System.out.println(emp.getEmpId()+emp.getEmpName());
		}
	}
	
}
