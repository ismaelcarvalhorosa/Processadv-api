package br.inf.lucas.royalrangers.api.cidade;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import br.inf.lucas.royalrangers.api.UF;
import br.inf.lucas.royalrangers.api.UfDescricao;
import br.inf.lucas.royalrangers.api.cidade.Cidade;
import br.inf.lucas.royalrangers.api.cidade.CidadePesquisa;

@Tag(name = "Cidade")
@Path("/cidade")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CidadeResource {

	@Inject
	CidadeService cidadeService;
	
	@Inject
	CidadePesquisa cidadePesquisa;
	
	@POST
	public Long post(Cidade cidade) {
		return cidadeService.gravar(cidade);
	}
	
	@GET
	@Path("/{id}")
	public Cidade get(@PathParam("id") Long id) {
		return cidadeService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Cidade> getTodas() {
		return cidadeService.tudo();
	}
	
	@POST
	@Path("/validarcidade")
	public Mensagem validarCidade(Cidade cidade) {
		return cidadeService.validarCidade(cidade);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return cidadeService.validarExclusao(codigo);
	}
	
	@PUT
	public void put(Cidade cidade) {
		cidadeService.atualizar(cidade);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		cidadeService.remover(id);
	}
	
	@GET
	@Path("/uf/tudo")
	public List<UfDescricao> ufs() {
		return Stream.of(UF.values()).map(UfDescricao::parse).collect(Collectors.toList());
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<Cidade> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "valor") @QueryParam("valor") String valor) {
		return cidadePesquisa.executar(pagina, valor);
	}
}