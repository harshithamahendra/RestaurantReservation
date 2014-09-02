package co.restaurant.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.restaurant.entity.TableVO;
import co.restaurant.entity.TableVO;
import co.restaurant.entity.TableVO;
import co.restaurant.exception.AppException;

public class TableDAO {

	public List<TableVO> getAll() throws AppException{
		List<TableVO> tableList = new ArrayList<TableVO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("SELECT * FROM tbl");
			rs= ps.executeQuery();

			while(rs.next()){
				TableVO table = new TableVO();
				table.setId(rs.getInt("ID"));
				table.setStatus(rs.getString("T_STATUS"));
				table.setSize(rs.getInt("T_SIZE"));
				tableList.add(table);
			}

		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return tableList;
	}
	
	public TableVO addTable(TableVO table) throws AppException{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("INSERT INTO people_db.tbl (T_STATUS, T_SIZE) VALUES(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, table.getStatus());
			ps.setInt(2, table.getSize());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			if(rs.next()){
				table.setId(rs.getInt(1));
				
			}
			
		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return table;
	}
	
	
	
	public List<TableVO> getTable() throws AppException{
		List<TableVO> tableList = new ArrayList<TableVO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("SELECT * FROM people_db.tbl WHERE T_STATUS='AVAILABLE'");
			rs= ps.executeQuery();

			while(rs.next()){
				TableVO table = new TableVO();
				table.setId(rs.getInt("ID"));
				table.setStatus(rs.getString("T_STATUS"));
				table.setSize(rs.getInt("T_SIZE"));
				tableList.add(table);
			}

		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return tableList;
	}
	
	public int updateTable(int id) throws AppException{
		//TableVO table = new TableVO();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("UPDATE people_db.tbl SET T_STATUS='OCCUPIED' WHERE ID=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			/*if(rs.next()){
				table.setId(rs.getInt("ID"));
				table.setStatus(rs.getString("T_STATUS"));
				table.setSize(rs.getInt("T_SIZE"));
			}*/

		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return id;
	}}
