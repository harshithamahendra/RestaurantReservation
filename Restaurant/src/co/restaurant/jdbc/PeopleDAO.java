package co.restaurant.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.restaurant.entity.PersonVO;
import co.restaurant.exception.AppException;

// Used to get the connection to the DB
public class PeopleDAO {

	public List<PersonVO> getAll() throws AppException{
		List<PersonVO> peopleList = new ArrayList<PersonVO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("SELECT * FROM people");
			rs= ps.executeQuery();

			while(rs.next()){
				PersonVO person = new PersonVO();
				person.setId(rs.getInt("ID"));
				person.setFirstName(rs.getString("FIRST_NAME"));
				person.setLastName(rs.getString("LAST_NAME"));
				person.setEmail(rs.getString("EMAIL"));
				person.setPhone(rs.getString("PHONE"));
				person.setDate(rs.getString("DATE"));
				person.setTime(rs.getString("TIME"));
				person.setSize(rs.getInt("SIZE"));
				person.setEvent(rs.getString("EVENT"));
				person.setT_id(rs.getInt("T_ID"));

				peopleList.add(person);
			}

		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return peopleList;
	}

	public List<PersonVO> getSeating() throws AppException{
		List<PersonVO> peopleList = new ArrayList<PersonVO>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("SELECT * FROM people");
			rs= ps.executeQuery();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			while(rs.next()){
				//System.out.println(rs.getString("DATE"));
				if(rs.getString("DATE").equals(dateFormat.format(new Date()))){
					PersonVO person = new PersonVO();
					person.setId(rs.getInt("ID"));
					person.setFirstName(rs.getString("FIRST_NAME"));
					person.setLastName(rs.getString("LAST_NAME"));
					person.setEmail(rs.getString("EMAIL"));
					person.setPhone(rs.getString("PHONE"));
					person.setDate(rs.getString("DATE"));
					person.setTime(rs.getString("TIME"));
					person.setSize(rs.getInt("SIZE"));
					person.setEvent(rs.getString("EVENT"));
					person.setT_id(rs.getInt("T_ID"));
					peopleList.add(person);
				}
			}

		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return peopleList;
	}
	public PersonVO getPerson(int id) throws AppException{
		PersonVO person = new PersonVO();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("SELECT * FROM people WHERE ID=?");
			ps.setInt(1, id);
			rs= ps.executeQuery();

			if(rs.next()){
				person.setId(rs.getInt("ID"));
				person.setFirstName(rs.getString("FIRST_NAME"));
				person.setLastName(rs.getString("LAST_NAME"));
				person.setEmail(rs.getString("EMAIL"));
				person.setPhone(rs.getString("PHONE"));
				person.setDate(rs.getString("DATE"));
				person.setTime(rs.getString("TIME"));
				person.setSize(rs.getInt("SIZE"));
				person.setEvent(rs.getString("EVENT"));
				person.setT_id(rs.getInt("T_ID"));
			}
			else
				throw new AppException("Person with Id = " + id + " not available");

		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return person;
	}

	public PersonVO addPerson(PersonVO person) throws AppException{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("INSERT INTO people_db.people (FIRST_NAME, LAST_NAME, EMAIL, PHONE, DATE, TIME, SIZE, EVENT, T_ID) VALUES (?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, person.getFirstName());
			ps.setString(2, person.getLastName());
			ps.setString(3, person.getEmail());
			ps.setString(4, person.getPhone());
			ps.setString(5, person.getDate());
			ps.setString(6, person.getTime());
			ps.setInt(7, person.getSize());
			ps.setString(8, person.getEvent());
			ps.setInt(9, person.getT_id());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			if(rs.next()){
				person.setId(rs.getInt(1));
			}
		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return person;
	}

	public PersonVO updatePerson(PersonVO person) throws AppException{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("UPDATE people_db.people SET DATE=?, TIME=?, SIZE=? WHERE ID=?",PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, person.getDate());
			ps.setString(2, person.getTime());
			ps.setInt(3, person.getSize());
			ps.setInt(4, person.getId());

			ps.executeUpdate();


		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return person;
	}
	public int assignTable(int personId, int tableId) throws AppException{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			con = DBConnector.getConnection();
			ps = con.prepareStatement("UPDATE people_db.people SET T_ID=? WHERE ID=?");
			ps.setInt(1, tableId);
			ps.setInt(2, personId);
			ps.executeUpdate();

		}catch(SQLException e){
			System.err.println("Error in executin query" + e.getMessage());
			e.printStackTrace();
			throw new AppException("Error in executin query" + e.getMessage());
		}
		finally{
			DBConnector.closeResources(ps,rs,con);
		}
		return personId;
	}
}
