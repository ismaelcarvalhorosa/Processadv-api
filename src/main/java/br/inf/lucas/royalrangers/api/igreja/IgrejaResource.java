package br.inf.lucas.royalrangers.api.igreja;

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

@Tag(name = "Igreja")
@Path("/igreja")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IgrejaResource {

	@Inject
	IgrejaService igrejaService;
	
	@Inject
	IgrejaPesquisa igrejaPesquisa;
	
	@POST
	public Long post(Igreja igreja) {
		return igrejaService.gravar(igreja);
	}
	
	@GET
	@Path("/{id}")
	public Igreja get(@PathParam("id") Long id) {
		return igrejaService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Igreja> getTodas() {
		return igrejaService.tudo();
	}
	
	@PUT
	public void put(Igreja igreja) {
		igrejaService.atualizar(igreja);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		igrejaService.remover(id);
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<Igreja> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "valor") @QueryParam("valor") String valor) {
		return igrejaPesquisa.executar(pagina, valor);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return igrejaService.validarExclusao(codigo);
	}
	
	@POST
	@Path("/validarigreja")
	public Mensagem validarIgreja(Igreja igreja) {
		return igrejaService.validarIgreja(igreja);
	}
}