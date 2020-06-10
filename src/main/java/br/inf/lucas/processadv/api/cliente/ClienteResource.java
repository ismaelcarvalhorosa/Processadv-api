package br.inf.lucas.processadv.api.cliente;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

import br.inf.lucas.processadv.api.Mensagem;
import br.inf.lucas.processadv.api.cliente.Cliente;
import br.inf.lucas.processadv.api.cliente.ClientePesquisa;

@Tag(name = "Cliente")
@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

	@Inject
	ClienteService clienteService;
	
	@Inject
	ClientePesquisa clientePesquisa;
	
	@POST
	public Long post(Cliente cliente) {
		return clienteService.gravar(cliente);
	}
	
	@GET
	@Path("/{id}")
	public Cliente get(@PathParam("id") Long id) {
		return clienteService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Cliente> getTodas() {
		return clienteService.tudo();
	}
	
	@POST
	@Path("/validarcliente")
	public Mensagem validarCliente(Cliente cliente) {
		return clienteService.validarCliente(cliente);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return clienteService.validarExclusao(codigo);
	}
	
	@PUT
	public void put(Cliente cliente) {
		clienteService.atualizar(cliente);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		clienteService.remover(id);
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<Cliente> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "valor") @QueryParam("valor") String valor) {
		return clientePesquisa.executar(pagina, valor);
	}
}