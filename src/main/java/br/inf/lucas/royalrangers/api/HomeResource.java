package br.inf.lucas.royalrangers.api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.inf.lucas.royalrangers.api.config.Config;
import br.inf.lucas.royalrangers.api.config.ConfigService;

@Path("/home")
@Tag(
		name = "Home",
		description = "Página de teste da API")
public class HomeResource {

	@Inject
	ConfigService configService;
	
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {
    	
    	Config config = configService.busca(1l);
    	
        return 
    		  "<head>"
    		  	+ "<title>Royal Rangers Brazil</title>"
    		+ "</head>"
    		+ "<body>"
    		+ "<h1>"+config.getNome()+"</h1>"
    		+ "<p>Hello World</p>"
    		+ "</body>";
        
    }
    
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public ObjetoTest objTest() {
    	return new ObjetoTest();
    }
    
}