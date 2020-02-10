package br.inf.lucas.royalrangers.api.regiao;

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
import br.inf.lucas.royalrangers.api.regiao.Regiao;
import br.inf.lucas.royalrangers.api.regiao.RegiaoPesquisa;

@Tag(name = "Regiao")
@Path("/regiao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegiaoResource {

	@Inject
	RegiaoService regiaoService;
	
	@Inject
	RegiaoPesquisa regiaoPesquisa;
	
	@POST
	public Long post(Regiao regiao) {
		return regiaoService.gravar(regiao);
	}
	
	@GET
	@Path("/{id}")
	public Regiao get(@PathParam("id") Long id) {
		return regiaoService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Regiao> getTodas() {
		return regiaoService.tudo();
	}
	
	@PUT
	public void put(Regiao regiao) {
		regiaoService.atualizar(regiao);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		regiaoService.remover(id);
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<Regiao> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "valor") @QueryParam("valor") String valor) {
		return regiaoPesquisa.executar(pagina, valor);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return regiaoService.validarExclusao(codigo);
	}
	
	@POST
	@Path("/validarregiao")
	public Mensagem validarRegiao(Regiao regiao) {
		return regiaoService.validarRegiao(regiao);
	}
}