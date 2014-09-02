package co.restaurant.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.restaurant.entity.TableVO;
import co.restaurant.exception.AppException;
import co.restaurant.jdbc.TableDAO;

@Path("/table")
public class TableService {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAll(){
		AppResponse response = new AppResponse();
		List<TableVO> tableList;
		try {
			TableDAO dao = new TableDAO();
			tableList = dao.getAll();
			response.setStatus("SUCCESS");
			response.setData(tableList);
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
	public AppResponse addTable(TableVO table){
		AppResponse response = new AppResponse();
		try {
			TableDAO dao = new TableDAO();
			table = dao.addTable(table);
			response.setStatus("SUCCESS");
			response.setData(table);
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
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getTable(){
		AppResponse response = new AppResponse();
		try {

			TableDAO dao = new TableDAO();
			TableVO table;
			List<TableVO> tableList = dao.getTable();
			if(tableList.size() == 0)
				table = tableList.get(0);
			else
				table = null;
			response.setStatus("SUCCESS");
			response.setData(table);
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
	@Path("/available")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAllAvailable(){
		AppResponse response = new AppResponse();
		try {
			TableDAO dao = new TableDAO();
			List<TableVO> tableList = dao.getTable();
			response.setStatus("SUCCESS");
			response.setData(tableList);
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
	@Path("/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse updateTable(@PathParam("id") int tableId){
		AppResponse response = new AppResponse();
		try {

			TableDAO dao = new TableDAO();
			int id = dao.updateTable(tableId);
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

}
