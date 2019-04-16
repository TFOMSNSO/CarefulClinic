package com.careful.clinic.rs;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.careful.clinic.dao.CustomDao;
import com.careful.clinic.model.Customer;

/**
 * Created by marco on 11.03.17.
 */
@javax.ws.rs.Path("/type_of_examples")
public class TypeOfExamples {
	
	@EJB
    private CustomDao customersDao;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") int id) {
    	JsonObject jsonObject = Json.createObjectBuilder()
    	  .add("result", customersDao.getCustomer(id).toString())
    	.add("result2", "Test").build();
    	
        return Response.ok().entity(jsonObject).build();
        		//customersDao.getCustomer(id);
    }
    @GET
    @Path("/object/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerObj(@PathParam("id") int id) {
        return customersDao.getCustomer(id);
    }
    
    @POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Customer customer) {

		String result = "Track saved : " + customer;
		return Response.status(201).entity(result).build();

	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHtml() {

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("result", "Here Java answering  ... Hello World!").build();

        return Response.ok().entity(jsonObject).build();

    }

	@GET
	@Path("/json")
    @Produces({ "application/json" })
    public String getHelloWorldJSON() {
        return "{\"result\":\"" + "TEST" + "\"}";
    }	

	@GET
    @Path("/xml")
    @Produces({ "application/xml" })
    public String getHelloWorldXML() {
        return "<xml><result>" + "world" + "</result></xml>";
    }

}
