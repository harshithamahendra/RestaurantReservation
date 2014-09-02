package co.restaurant.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.restaurant.entity.AdminVO;
import co.restaurant.exception.AppException;

public class AdminDAO {

	public String validate(AdminVO admin) throws AppException{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String result = "false";
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("SELECT * FROM people_db.admin WHERE ID=1");
			rs = ps.executeQuery();
			
			if(rs.next()){
				String login = rs.getString("LOGIN");
				String pass = rs.getString("PASSWORD");

				if(login.equals(admin.getLogin()) && pass.equals(admin.getPassword()))
					result = "true";
			}
			
		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return result;
	}
	public String validateEmail(String email) throws AppException{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String result = "false";
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("SELECT * FROM people_db.admin WHERE ID=1");
			rs = ps.executeQuery();
			
			if(rs.next()){
				if(rs.getString("LOGIN").equals(email))
					result = "true";
			}
			
		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return result;
	}
	public void updatePwd(String pwd) throws AppException{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("UPDATE people_db.admin SET PASSWORD=? WHERE ID=1");
			ps.setString(1, pwd);
			ps.executeUpdate();
			
		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
	}
	
	public AdminVO getAll() throws AppException{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		AdminVO admin = new AdminVO();

		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("SELECT * FROM admin");
			rs= ps.executeQuery();

			if(rs.next()){
				admin.setName(rs.getString("NAME"));
				admin.setAddress1(rs.getString("ADDRESS1"));
				admin.setAddress2(rs.getString("ADDRESS2"));
				admin.setState(rs.getString("STATE"));
				admin.setZip(rs.getString("ZIP"));
				admin.setWeekdayAm(rs.getString("WEEKDAY_AM"));
				admin.setWeekdayPm(rs.getString("WEEKDAY_PM"));
				admin.setWeekendAm(rs.getString("WEEKEND_AM"));
				admin.setWeekendPm(rs.getString("WEEKEND_PM"));

			}

		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return admin;
	}
	
	public AdminVO updateAdmin(AdminVO admin) throws AppException{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("UPDATE people_db.admin SET NAME=?, ADDRESS1=?, ADDRESS2=?,STATE=?,ZIP=?,WEEKDAY_AM=?,WEEKDAY_PM=?,WEEKEND_AM=?,WEEKEND_PM=? WHERE ID=1");
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getAddress1());
			ps.setString(3, admin.getAddress2());
			ps.setString(4, admin.getState());
			ps.setString(5,admin.getZip());
			ps.setString(6, admin.getWeekdayAm());
			ps.setString(7, admin.getWeekdayPm());
			ps.setString(8, admin.getWeekendAm());
			ps.setString(9, admin.getWeekendPm());
			ps.executeUpdate();

		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return admin;
	}
}
