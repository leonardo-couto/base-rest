package com.bewkrop.baserest.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class Hello {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@RolesAllowed("ADMIN")
	public String greeting() {
		return "Ol\u00E1 mundo cruel!\n";
	}

}
