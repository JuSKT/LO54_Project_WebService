package com.lo54project.webservice.service;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

import com.lo54project.webservice.dao.LocationDao;
import com.lo54project.webservice.model.Location;

// Will map the resource to the URL locations
@Path("/locations")
public class LocationResource 
{
	// Allows to insert contextual objects into the class
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	  
	// Return the list of locations
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Location> getLocations() 
	{
		List<Location> locations = new ArrayList<Location>();
		locations.addAll(LocationDao.instance.getModel().values());
	    return locations; 
	}
	  
	// Return the chosen location
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{location}")
	public Location getCourseSession(@PathParam("location") String id) 
	{
		Location loc = LocationDao.instance.getModel().get(Integer.valueOf(id));
		
		if(loc==null)
		{
			throw new RuntimeException("Get: Location with " + id +  " not found");
		}
		
		return loc;
	}
	
	// Return the number of locations
	@GET
	@Path("count")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCount() 
	{
		int count = LocationDao.instance.getModel().size();
		return String.valueOf(count);
	}
	
	// Put a location into response and return it 
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putLocation(JAXBElement<Location> location) 
	{
		Location c = location.getValue();
	    return putAndGetResponse(c);
	}
	  
	// Put a location into response and return it
	private Response putAndGetResponse(Location c) 
	{
		Response res;
		
		if(LocationDao.instance.getModel().containsKey(c.getId())) 
		{
			res = Response.noContent().build();
	    } 
		else 
		{
			res = Response.created(uriInfo.getAbsolutePath()).build();
	    }
		
		LocationDao.instance.getModel().put(c.getId(), c);
	    return res;
	}
}