package br.inf.lucas.royalrangers.api.distrito;

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
import br.inf.lucas.royalrangers.api.destacamento.Destacamento;
import br.inf.lucas.royalrangers.api.distrito.Distrito;
import br.inf.lucas.royalrangers.api.distrito.DistritoPesquisa;

@Tag(name = "Distrito")
@Path("/distrito")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DistritoResource {

	@Inject
	DistritoService distritoService;
	
	@Inject
	DistritoPesquisa distritoPesquisa;
	
	@POST
	public Long post(Distrito distrito) {
		return distritoService.gravar(distrito);
	}
	
	@GET
	@Path("/{id}")
	public Distrito get(@PathParam("id") Long id) {
		return distritoService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Distrito> getTodos() {
		return distritoService.tudo();
	}
	
	@PUT
	public void put(Distrito distrito) {
		distritoService.atualizar(distrito);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		distritoService.remover(id);
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<Distrito> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "valor") @QueryParam("valor") String valor,
			@Parameter(required = false, name = "regiao") @QueryParam("regiao") String regiao) {
		return distritoPesquisa.executar(pagina, valor, regiao);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return distritoService.validarExclusao(codigo);
	}
	
	@POST
	@Path("/validardistrito")
	public Mensagem validarDistrito(Distrito distrito) {
		return distritoService.validarDistrito(distrito);
	}
}