package com.lo54project.webservice.service;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lo54project.webservice.model.Client;

@Path("/clientservice")
public class ClientService {
	
	/**
	 * http://localhost:8080/LO54_Project/webservice/clientservice/
	 * @return
	 * @throws JSONException
	 * @throws JsonProcessingException
	 */
    @GET
    @Produces("application/json")
    public Response getClient() throws JSONException, JsonProcessingException {

    	Client c = new Client(1, "COLLE", "Juju", "Le soleil, nova 2", "0845789865", "jc@lesoleil.espace");
      
    	ObjectMapper mapper = new ObjectMapper();

    	String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + mapper.writeValueAsString(c);
    	return Response.status(200).entity(result).build();
    	
    }
	
    /**
     * http://localhost:8080/LO54_Project/webservice/clientservice/1
     * @param f
     * @return
     * @throws JSONException
     * @throws JsonProcessingException
     */
    @Path("{f}")
    @GET
    @Produces("application/json")
    public Response getClient(@PathParam("f") int f) throws JSONException, JsonProcessingException {
     
    	Client c = new Client(1, "COLLE", "Julien", "La lune, trou 2", "0845789865", "jc@lalune.espace");
      
    	ObjectMapper mapper = new ObjectMapper();
      
    	try {
			// convert user object to json string, and save to a file
			mapper.writeValue(new File("c:\\client.json"), c);
			// display to console
			System.out.println(mapper.writeValueAsString(c));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

      String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + mapper.writeValueAsString(c);
      return Response.status(200).entity(result).build();
    }

}
