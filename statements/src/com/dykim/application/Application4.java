package com.dykim.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dykim.model.EmployeeDTO;

import static com.dykim.common.JDBCTemplate.getConnection;
import static com.dykim.common.JDBCTemplate.close;

public class Application4 {

	public static void main(String[] args) {
		Connection con = getConnection();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		//한 행에 대한 정보를 담기 위한 DTO
		EmployeeDTO selectEmp = null;
		//여러 DTO를 하나의 인스턴스로 묶기위한 List
		List<EmployeeDTO> empList = null;
		
		String query = "SELECT * FROM EMPLOYEE";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			empList = new ArrayList<>();
			
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
		}finally {
			close(rset);
			close(stmt);
			close(con);
		}
		
		for(EmployeeDTO emp:empList) {
			System.out.println(emp);
		}
		
	}

}
