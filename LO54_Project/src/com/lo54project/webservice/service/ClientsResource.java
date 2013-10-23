package com.lo54project.webservice.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.lo54project.webservice.dao.ClientDao;
import com.lo54project.webservice.model.Client;

//Will map the resource to the URL clients
@Path("/clients")
public class ClientsResource {
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
	  
	  @POST
	  @Produces(MediaType.TEXT_HTML)
	  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	  public void newClient(@FormParam("id") Integer id,
	      @FormParam("lastname") String lastname,
	      @FormParam("firstname") String firstname,
	      @FormParam("address") String address,
	      @FormParam("phone") String phone,
	      @FormParam("email") String email,
	      @Context HttpServletResponse servletResponse) throws IOException {
		Client cli = new Client(10, lastname, firstname, address, phone, email);
	    ClientDao.instance.getModel().put(id, cli);
	    
	    servletResponse.sendRedirect("../participate.html");
	  }
	  
	  
	  // Defines that the next path parameter after clients is
	  // treated as a parameter and passed to the ClientResources
	  // Allows to type http://localhost:8080/de.vogella.jersey.todo/rest/todos/1
	  // 1 will be treaded as parameter todo and passed to TodoResource
	  @Path("{client}")
	  public ClientResource getClient(@PathParam("client") String id) {
	    return new ClientResource(uriInfo, request, id);
	  }
}