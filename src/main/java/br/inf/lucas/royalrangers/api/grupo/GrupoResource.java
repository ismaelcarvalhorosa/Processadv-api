package br.inf.lucas.royalrangers.api.grupo;

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
import br.inf.lucas.royalrangers.api.cidade.Cidade;
import br.inf.lucas.royalrangers.api.cidade.CidadePesquisa;
import br.inf.lucas.royalrangers.api.regiao.Regiao;

@Tag(name = "Grupo")
@Path("/grupo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GrupoResource {

	@Inject
	GrupoService grupoService;
	
	@Inject
	GrupoPesquisa grupoPesquisa;
	
	@POST
	public Long post(Grupo grupo) {
		return grupoService.gravar(grupo);
	}
	
	@GET
	@Path("/{id}")
	public Grupo get(@PathParam("id") Long id) {
		return grupoService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Grupo> getTodos() {
		return grupoService.tudo();
	}
	
	@PUT
	public void put(Grupo grupo) {
		grupoService.atualizar(grupo);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		grupoService.remover(id);
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<Grupo> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "valor") @QueryParam("valor") String valor) {
		return grupoPesquisa.executar(pagina, valor);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return grupoService.validarExclusao(codigo);
	}
	
	@POST
	@Path("/validargrupo")
	public Mensagem validarGrupo(Grupo grupo) {
		return grupoService.validarGrupo(grupo);
	}
}