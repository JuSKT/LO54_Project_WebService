package com.lo54project.webservice.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.lo54project.webservice.dao.ClientDao;
import com.lo54project.webservice.dao.CourseSessionDao;
import com.lo54project.webservice.model.Client;

//Will map the resource to the URL clients
@Path("/clients")
public class ClientResource {
	 // Allows to insert contextual objects into the class, 
	  // e.g. ServletContext, Request, Response, UriInfo
	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;


	  // Return the list of clients to the user in the browser
//	  @GET
//	  @Produces(MediaType.TEXT_XML)
//	  public List<Client> getClientsBrowser() {
//	    List<Client> clients = new ArrayList<Client>();
//	    clients.addAll(ClientDao.instance.getModel().values());
//	    return clients;
//	  }
	  
	  // Return the list of clients for applications
	  @GET
	  @Produces({MediaType.APPLICATION_JSON})
	  public List<Client> getClients() {
	    List<Client> clients = new ArrayList<Client>();
	    clients.addAll(ClientDao.instance.getModel().values());
	    return clients; 
	  }
	  
	  
	  // retuns the number of clients
	  // use http://localhost:8080/de.vogella.jersey.todo/rest/todos/count
	  // to get the total number of records
	  @GET
	  @Path("count")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String getCount() {
	    int count = ClientDao.instance.getModel().size();
	    return String.valueOf(count);
	  }
	  
	  @PUT
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response putClient(JAXBElement<Client> cli) {
		Client c = cli.getValue();
	    return putAndGetResponse(c);
	  }
	  
	  @DELETE
	  public void deleteClient(@PathParam("client") String id) {
	    Client c = ClientDao.instance.getModel().remove(Integer.valueOf(id));
	    if(c==null)
	      throw new RuntimeException("Delete: Client with " + id +  " not found");
	  }
	  
	  private Response putAndGetResponse(Client cli) {
	    Response res;
	    if(ClientDao.instance.getModel().containsKey(cli.getId())) {
	      res = Response.noContent().build();
	    } else {
	      res = Response.created(uriInfo.getAbsolutePath()).build();
	    }
	    ClientDao.instance.getModel().put(cli.getId(), cli);
	    return res;
	  }
	  
	  @POST
	  @Produces(MediaType.TEXT_HTML)
	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	  public void newClient(@FormParam("id") Integer id_session_course,
			  @FormParam("lastname") String lastname,
			  @FormParam("firstname") String firstname,
			  @FormParam("address") String address,
			  @FormParam("phone") String phone,
			  @FormParam("email") String email,
			  @Context HttpServletResponse servletResponse) throws IOException, ParseException, SQLException {
				  
		  Client cli = new Client(-1, lastname, firstname, address, phone, email);
		  cli.setCrss(CourseSessionDao.instance.getCourseSession(id_session_course));
		  
		  ClientDao.instance.createClientAndSetCourseSession(cli);
	    
		  servletResponse.sendRedirect("../participate.html");
	  }
	  
	  // Return the chosen client
	  @GET
	  @Produces({MediaType.APPLICATION_JSON})
	  @Path("{client}")
	  public Client getClient(@PathParam("client") String id) {
		  Client cli = ClientDao.instance.getModel().get(Integer.valueOf(id));
		  if(cli==null)
			  throw new RuntimeException("Get: Client with " + id +  " not found");
		  return cli;
	  }
}
