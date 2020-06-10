package br.inf.lucas.processadv.api.cliente_obs;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Cliente_obs")
@Path("/cliente_obs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Cliente_obsResource {

	@Inject
	Cliente_obsService cliente_obsService;
	
	@POST
	public Long post(Cliente_obs cliente_obs) {
		return cliente_obsService.gravar(cliente_obs);
	}
	
	@GET
	@Path("/{id}")
	public Cliente_obs get(@PathParam("id") Integer id) {
		return cliente_obsService.buscaByCliente(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Cliente_obs> getTodos() {
		return cliente_obsService.tudo();
	}
	
	@PUT
	public void put(Cliente_obs cliente_obs) {
		cliente_obsService.atualizar(cliente_obs);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		cliente_obsService.remover(id);
	}
}