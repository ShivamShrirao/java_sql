package com.test.java_sql;

import java.sql.*;

public class sqlCon {
	public static void main(String[] args) {
		try {
//			Class.forName("com.mysql.jdbc.Driver");				// deprecated
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/forum", "mysql", "password");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from exm");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			con.close();
		    } catch (Exception e) {
		    	System.out.println(e);
		    }
	}

}