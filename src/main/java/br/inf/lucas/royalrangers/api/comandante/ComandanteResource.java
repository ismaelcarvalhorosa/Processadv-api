package br.inf.lucas.royalrangers.api.comandante;

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

import br.inf.lucas.royalrangers.api.Mensagem;
import br.inf.lucas.royalrangers.api.explorador.Explorador;

@Tag(name = "Comandante")
@Path("/comandante")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComandanteResource {

	@Inject
	ComandanteService comandanteService;
	
	@Inject
	ComandantePesquisa comandantePesquisa;
	
	@POST
	public Long post(Comandante comandante) {
		return comandanteService.gravar(comandante);
	}
	
	@GET
	@Path("/{id}")
	public Comandante get(@PathParam("id") Long id) {
		return comandanteService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Comandante> getTodas() {
		return comandanteService.tudo();
	}
	
	@PUT
	public void put(Comandante pessoa) {
		comandanteService.atualizar(pessoa);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		comandanteService.remover(id);
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<Comandante> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "valor") @QueryParam("valor") String valor,
			@Parameter(required = false, name = "dest") @QueryParam("dest") String dest,
			@Parameter(required = false, name = "cidade") @QueryParam("cidade") String cidade,
			@Parameter(required = false, name = "grupo") @QueryParam("grupo") String grupo,
			@Parameter(required = false, name = "situacao") @QueryParam("situacao") String situacao) {
		return comandantePesquisa.executar(pagina, valor, dest, cidade, grupo, situacao);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return comandanteService.validarExclusao(codigo);
	}
	
	@POST
	@Path("/validarcomandante")
	public Mensagem validarExplorador(Comandante comandante) {
		return comandanteService.validarComandante(comandante);
	}
}