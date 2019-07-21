package com.test.java_sql;

import java.sql.*;
import java.util.Scanner;

public class sqlCon {
	final private static String hostname = "127.0.0.1";
	final private static int port = 3306;
	final private static String database = "forum";
	final private static String user = "mysql";
	final private static String pass = "password";
	
	private static Connection con = null;
	private static Statement stmt = null;
	private static PreparedStatement prestmt = null;
	private static ResultSet rs = null;
	
	protected static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		connectDB();
		readInput();
		readDB();
		closeDB();
	}
	private static void connectDB() {
		try {
//			Class.forName("com.mysql.jdbc.Driver");				// deprecated
			con = DriverManager.getConnection("jdbc:mysql://"+hostname+":"+port+"/"+database, user, pass);
			stmt = con.createStatement();
		} catch (Exception e) {
	    	System.out.println(e);
	    }
	}
	private static void readInput() {
		try {
			prestmt = con.prepareStatement("insert into example values (?, ?, ?)");
			System.out.println("Enter Details:");
			System.out.println("[?] Enter id: ");
			int id = scan.nextInt();
			prestmt.setInt(1, id);
			System.out.println("[?] Enter name: ");                                                                                                
			String name = scan.next();
			prestmt.setString(2, name);
			System.out.println("[?] Enter age: ");
			int age = scan.nextInt();
			prestmt.setInt(3, age);
			prestmt.executeUpdate();
		} catch (Exception e) {
	    	System.out.println(e);
	    }
	}
	private static void readDB() {
		try {
			rs = stmt.executeQuery("select * from example");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
		    } catch (Exception e) {
		    	System.out.println(e);
		    }
	}
	private static void closeDB() {
		try {
			if(con!=null) {
				con.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
			if(rs!=null) {
				rs.close();
			}
			if(scan!=null) {
				scan.close();
			}
			System.out.println("\nConnection closed.");
		} catch (Exception e) {
	    	System.out.println(e);
	    }
	}
}

/*
 OUTPUT
Enter Details:
[?] Enter id: 
9
[?] Enter name: 
John
[?] Enter age: 
26
1 ad 12
2 adam 19
3 henry 22
4 jack 23
5 ggg 23
6 fff 33
9 John 26

Connection closed.
 */
