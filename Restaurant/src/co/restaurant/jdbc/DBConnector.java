package co.restaurant.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.restaurant.exception.AppException;

public class DBConnector {
	//get Connection to the Database
	private final static String USERNAME = "root";
	private final static String PASSWORD = "sandy14";
	private final static String DB_URL  = "jdbc:mysql://localhost:3307/people_db";
	public static Connection getConnection() throws AppException{
		Connection con = null;
		try{
		Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			System.err.println("Error in loading jdbc driver");
			e.printStackTrace();
			throw new AppException("Error in loading jdbc driver");
		}
		try{
		con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		System.out.println("Connection successful");
		}
		catch(SQLException e){
			System.err.println("Error in getting connection" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in getting connection" + e.getMessage());
		}
		return con;
	}
	
	public static void closeResources(PreparedStatement ps, ResultSet rs, Connection con){
		if(ps != null)
			try {
				ps.close();

				if(rs != null)
					rs.close();
				if(con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Unexcepted Error: Conenctions are null.");
			}
}
	public static void main(String[] args) {
		//DBConnector.getConnection();
	}
	
}
