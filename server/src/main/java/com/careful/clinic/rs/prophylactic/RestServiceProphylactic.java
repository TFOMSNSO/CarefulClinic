package com.careful.clinic.rs.prophylactic;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.careful.clinic.model.Customer;

@javax.ws.rs.Path("/prophylactic")
public class RestServiceProphylactic {
	
	
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON() {

		//String result = "Track saved : " + customer;
		return Response.status(201).entity("").build();

	}

}
