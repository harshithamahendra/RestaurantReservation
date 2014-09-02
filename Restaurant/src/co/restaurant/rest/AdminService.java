package co.restaurant.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.restaurant.entity.AdminVO;
import co.restaurant.exception.AppException;
import co.restaurant.jdbc.AdminDAO;

@Path("/admin")
public class AdminService {
	@POST
	@Path("/validate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AppResponse validate(AdminVO admin){
		AppResponse response = new AppResponse();
		try {
			AdminDAO dao = new AdminDAO();
			String result = dao.validate(admin);
			response.setStatus("SUCCESS");
			response.setData(result);
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
	@Path("/get/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse validateEmail(@PathParam("email") String email){
		AppResponse response = new AppResponse();
		try {
			AdminDAO dao = new AdminDAO();
			String result = dao.validateEmail(email);
			response.setStatus("SUCCESS");
			response.setData(result);
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
	@Path("/update/pwd")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AppResponse updatePwd(String pwd){
		AppResponse response = new AppResponse();
		try {
			AdminDAO dao = new AdminDAO();
			dao.updatePwd(pwd);
			response.setStatus("SUCCESS");
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
	public AppResponse get(){
		AppResponse response = new AppResponse();
		try {
			AdminDAO dao = new AdminDAO();
			AdminVO admin = dao.getAll();
			response.setStatus("SUCCESS");
			response.setData(admin);
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
	public AppResponse updateAdmin(AdminVO admin){
		AppResponse response = new AppResponse();
		try {
			AdminDAO dao = new AdminDAO();
			AdminVO result = dao.updateAdmin(admin);
			response.setStatus("SUCCESS");
			response.setData(result);
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
