package com.bank.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagerApplication {

	public static void main(String[] args) throws Exception  {
		SpringApplication.run(ManagerApplication.class, args);
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		
//		Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","ram","tiger");
//		
//		Statement st=con.createStatement();
//		ResultSet rs=st.executeQuery("Select * from my_table");
//		while(rs.next()) {
//			System.out.println(rs.getInt(1)+" "+rs.getString(2));
//		}
//		rs.close();
//		st.close();
	}

}
