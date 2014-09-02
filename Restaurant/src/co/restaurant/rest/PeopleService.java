package co.restaurant.rest;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.restaurant.entity.PersonVO;
import co.restaurant.exception.AppException;
import co.restaurant.jdbc.PeopleDAO;

@Path("/people")
public class PeopleService {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAll(){
		AppResponse response = new AppResponse();
		List<PersonVO> peopleList;
		try {
			PeopleDAO dao = new PeopleDAO();
			peopleList = dao.getAll();
			response.setStatus("SUCCESS");
			response.setData(peopleList);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
			response.setStatus("ERROR");
			response.setMsg(e.getMessage());
		}
		return response;
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getPerson(@PathParam("id") int personId){
		AppResponse response = new AppResponse();
		try {

			PeopleDAO dao = new PeopleDAO();
			PersonVO person = dao.getPerson(personId);
			response.setStatus("SUCCESS");
			response.setData(person);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
			response.setStatus("ERROR");
			response.setMsg(e.getMessage());
		}

		return response;
	}

	@POST
	@Path("/assign/{id}/{t_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse assignTable(@PathParam("id") int personId,@PathParam("t_id") int tableId){
		AppResponse response = new AppResponse();
		try {

			PeopleDAO dao = new PeopleDAO();
			int id = dao.assignTable(personId,tableId);
			response.setStatus("SUCCESS");
			response.setData(id);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
			response.setStatus("ERROR");
			response.setMsg(e.getMessage());
		}

		return response;
	}
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AppResponse addPerson(PersonVO person){
		AppResponse response = new AppResponse();
		try {
			PeopleDAO dao = new PeopleDAO();
			person = dao.addPerson(person);
			response.setStatus("SUCCESS");
			response.setData(person);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
			response.setStatus("ERROR");
			response.setMsg(e.getMessage());
		}
		return response;
	}
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AppResponse updatePerson(PersonVO person){
		AppResponse response = new AppResponse();
		try {
			PeopleDAO dao = new PeopleDAO();
			person = dao.updatePerson(person);
			response.setStatus("SUCCESS");
			response.setData(person);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
			response.setStatus("ERROR");
			response.setMsg(e.getMessage());
		}
		return response;
	}
	
	@GET
	@Path("/seating")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getSeating(){
		AppResponse response = new AppResponse();
		List<PersonVO> peopleList;
		try {
			PeopleDAO dao = new PeopleDAO();
			peopleList = dao.getSeating();
			response.setStatus("SUCCESS");
			response.setData(peopleList);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
			response.setStatus("ERROR");
			response.setMsg(e.getMessage());
		}
		return response;
	}

}
