package br.inf.lucas.processadv.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/home")
@Tag(
		name = "Home",
		description = "Página de teste da API")
public class HomeResource {
	
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {
        return 
    		  "<head>"
    		  	+ "<title>ProcessADV</title>"
    		+ "</head>"
    		+ "<body>"
    		+ "<h1></h1>"
    		+ "<p>Hello World</p>"
    		+ "</body>";
    }
}