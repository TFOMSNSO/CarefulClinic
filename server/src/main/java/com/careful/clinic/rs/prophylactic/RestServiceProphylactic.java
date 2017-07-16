package com.careful.clinic.rs.prophylactic;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.careful.clinic.model.PersonModel;


@javax.ws.rs.Path("/prophylactic")
public class RestServiceProphylactic {
	
	
	@POST
	@Path("/search_person_ger")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public PersonModel searchGer(PersonModel personmodel) {
/*		System.out.println("TEST 1 "+personmodel);
		String []args={personmodel.getSurname(),personmodel.getFirstname(),personmodel.getLastname(),personmodel.getBithday()};
		Main1.main(args);
		System.out.println("TEST 2 "+personmodel);
*/		/*DispanceryInfo disSoap = new DispanceryInfo(); 
		DispanceryInfoSoap port = disSoap.getDispanceryInfoSoap();
		SendRequest sr = new SendRequest();
		
		sr.setSurname(personmodel.getSurname());
		sr.setName(personmodel.getFirstname());
		sr.setMiddleName(personmodel.getLastname());
		sr.setDateBirth(personmodel.getBithday());
		
		
		SendResponse res = port.getPersonData(sr);
		ResponseData resData = null;
		resData = res.getData();
		System.out.println("test "+resData);
*/		
		
		
		return personmodel;

	}

}
