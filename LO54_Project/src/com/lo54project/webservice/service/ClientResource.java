package com.lo54project.webservice.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.lo54project.webservice.dao.ClientDao;
import com.lo54project.webservice.model.Client;

public class ClientResource {
	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;
	  Integer id;
	  public ClientResource(UriInfo uriInfo, Request request, String id) {
	    this.uriInfo = uriInfo;
	    this.request = request;
	    this.id = Integer.valueOf(id);
	  }
	  
	  //Application integration     
	  @GET
	  @Produces({MediaType.APPLICATION_JSON})
	  public Client getClient() {
		  Client cli = ClientDao.instance.getModel().get(id);
	    if(cli==null)
	      throw new RuntimeException("Get: Client with " + id +  " not found");
	    return cli;
	  }
	  
	  // for the browser
//	  @GET
//	  @Produces(MediaType.TEXT_XML)
//	  public Client getClientHTML() {
//		  Client cli = ClientDao.instance.getModel().get(id);
//	    if(cli==null)
//	      throw new RuntimeException("Get: Client with " + id +  " not found");
//	    return cli;
//	  }

	  @PUT
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response putClient(JAXBElement<Client> cli) {
		Client c = cli.getValue();
	    return putAndGetResponse(c);
	  }
	  
	  @DELETE
	  public void deleteClient() {
	    Client c = ClientDao.instance.getModel().remove(id);
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
}
