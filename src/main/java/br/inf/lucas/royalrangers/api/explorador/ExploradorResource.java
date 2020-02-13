package br.inf.lucas.royalrangers.api.explorador;

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
import br.inf.lucas.royalrangers.api.regiao.Regiao;

@Tag(name = "Explorador")
@Path("/explorador")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExploradorResource {

	@Inject
	ExploradorService exploradorService;
	
	@Inject
	ExploradorPesquisa exploradorPesquisa;
	
	@POST
	public Long post(Explorador explorador) {
		return exploradorService.gravar(explorador);
	}
	
	@GET
	@Path("/{id}")
	public Explorador get(@PathParam("id") Long id) {
		return exploradorService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Explorador> getTodas() {
		return exploradorService.tudo();
	}
	
	@PUT
	public void put(Explorador explorador) {
		exploradorService.atualizar(explorador);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		exploradorService.remover(id);
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<Explorador> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "valor") @QueryParam("valor") String valor,
			@Parameter(required = false, name = "dest") @QueryParam("dest") String dest,
			@Parameter(required = false, name = "cidade") @QueryParam("cidade") String cidade,
			@Parameter(required = false, name = "responsavel") @QueryParam("responsavel") String responsavel) {
		return exploradorPesquisa.executar(pagina, valor, dest, cidade, responsavel);
	}
	
	@GET
	@Path("/ExploradoresByResponsavel")
	public PaginationResult<Explorador> ExploradoresByResponsavel(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "responsavel") @QueryParam("responsavel") String responsavel) {
		return exploradorPesquisa.ExploradoresByResponsavel(pagina, responsavel);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return exploradorService.validarExclusao(codigo);
	}
	
	@POST
	@Path("/validarexplorador")
	public Mensagem validarExplorador(Explorador explorador) {
		return exploradorService.validarExplorador(explorador);
	}
}