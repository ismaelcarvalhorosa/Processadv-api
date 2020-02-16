package br.inf.lucas.royalrangers.api.responsavel;

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

@Tag(name = "Responsavel")
@Path("/responsavel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResponsavelResource {

	@Inject
	ResponsavelService responsavelService;
	
	@Inject
	ResponsavelPesquisa responsavelPesquisa;
	
	@POST
	public Long post(Responsavel responsavel) {
		return responsavelService.gravar(responsavel);
	}
	
	@GET
	@Path("/{id}")
	public Responsavel get(@PathParam("id") Long id) {
		return responsavelService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Responsavel> getTodas() {
		return responsavelService.tudo();
	}
	
	@PUT
	public void put(Responsavel responsavel) {
		responsavelService.atualizar(responsavel);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		responsavelService.remover(id);
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<Responsavel> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "valor") @QueryParam("valor") String valor,
			@Parameter(required = false, name = "dest") @QueryParam("dest") String dest,
			@Parameter(required = false, name = "cidade") @QueryParam("cidade") String cidade) {
		return responsavelPesquisa.executar(pagina, valor, dest, cidade);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return responsavelService.validarExclusao(codigo);
	}
	
	@POST
	@Path("/validarresponsavel")
	public Mensagem validarResponsavel(Responsavel responsavel) {
		return responsavelService.validarResponsavel(responsavel);
	}
}