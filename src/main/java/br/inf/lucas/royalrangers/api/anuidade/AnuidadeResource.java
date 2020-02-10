package br.inf.lucas.royalrangers.api.anuidade;

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

@Tag(name = "Anuidade")
@Path("/anuidade")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnuidadeResource {

	@Inject
	AnuidadeService anuidadeService;
	
	@Inject
	AnuidadePesquisa anuidadePesquisa;
	
	@POST
	public Long post(Anuidade anuidade) {
		return anuidadeService.gravar(anuidade);
	}
	
	@GET
	@Path("/{id}")
	public Anuidade get(@PathParam("id") Long id) {
		return anuidadeService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Anuidade> getTodas() {
		return anuidadeService.tudo();
	}
	
	@PUT
	public void put(Anuidade anuidade) {
		anuidadeService.atualizar(anuidade);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		anuidadeService.remover(id);
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<Anuidade> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "valor") @QueryParam("valor") String valor,
			@Parameter(required = false, name = "dest") @QueryParam("dest") String dest,
			@Parameter(required = false, name = "explorador") @QueryParam("explorador") String explorador,
			@Parameter(required = false, name = "ano") @QueryParam("ano") String ano) {
		return anuidadePesquisa.executar(pagina, valor, dest, explorador, ano);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return anuidadeService.validarExclusao(codigo);
	}
	
	@POST
	@Path("/validaranuidade")
	public Mensagem validarAnuidade(Anuidade anuidade) {
		return anuidadeService.validarAnuidade(anuidade);
	}
}