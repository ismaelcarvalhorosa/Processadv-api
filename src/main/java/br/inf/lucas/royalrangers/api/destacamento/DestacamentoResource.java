package br.inf.lucas.royalrangers.api.destacamento;
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
import br.inf.lucas.royalrangers.api.destacamento.DestacamentoPesquisa;

@Tag(name = "Destacamento")
@Path("/destacamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DestacamentoResource {

	@Inject
	DestacamentoService destacamentoService;
	
	@Inject
	DestacamentoPesquisa destacamentoPesquisa;
	
	@POST
	public Long post(Destacamento destacamento) {
		return destacamentoService.gravar(destacamento);
	}
	
	@GET
	@Path("/{id}")
	public Destacamento get(@PathParam("id") Long id) {
		return destacamentoService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Destacamento> getTodos() {
		return destacamentoService.tudo();
	}
	
	@PUT
	public void put(Destacamento destacamento) {
		destacamentoService.atualizar(destacamento);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		destacamentoService.remover(id);
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<Destacamento> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "valor") @QueryParam("valor") String valor,
			@Parameter(required = false, name = "distrito") @QueryParam("distrito") String distrito,
			@Parameter(required = false, name = "cidade") @QueryParam("cidade") String cidade,
			@Parameter(required = false, name = "igreja") @QueryParam("igreja") String igreja) {
		return destacamentoPesquisa.executar(pagina, valor, distrito, cidade, igreja);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return destacamentoService.validarExclusao(codigo);
	}
	
	@POST
	@Path("/validardestacamento")
	public Mensagem validarDestacamento(Destacamento destacamento) {
		return destacamentoService.validarDestacamento(destacamento);
	}
}