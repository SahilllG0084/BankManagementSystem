package com.cjc.bms.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    
	public static Connection getConnection()
	{
		Connection con = null;
		
		try {
			//Step 1: Load Database Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Step 2: Establish Database Connection
			String url = "jdbc:mysql://localhost:3306/reg36";
			String user = "root";
			String pass = "mysql";
			
			con = DriverManager.getConnection(url, user, pass);
		}
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return con;
	}
}
