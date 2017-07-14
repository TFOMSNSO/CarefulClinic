package com.careful.clinic.rs.prophylactic;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.careful.clinic.model.Customer;
import com.careful.clinic.model.PersonModel;

@javax.ws.rs.Path("/prophylactic")
public class RestServiceProphylactic {
	
	
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(PersonModel personmodel) {
		
		System.out.println("TEST "+personmodel.getSurname()+personmodel.getLastname()+personmodel.getFirstname()+personmodel.getBithday());
		//String result = "Track saved : " + customer;
		return Response.status(201).entity("").build();

	}

}
