package br.inf.lucas.processadv.api.cliente_contato;
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
import br.inf.lucas.processadv.api.Mensagem;

@Tag(name = "Cliente_contato")
@Path("/cliente_contato")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteContatoResource {

	@Inject
	ClienteContatoService contatoService;
	
	@Inject
	ClienteContatoPesquisa contatoPesquisa;
	
	@POST
	public Long post(ClienteContato contato) {
		return contatoService.gravar(contato);
	}
	
	@GET
	@Path("/{id}")
	public ClienteContato get(@PathParam("id") Long id) {
		return contatoService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<ClienteContato> getTodas() {
		return contatoService.tudo();
	}
	
	@POST
	@Path("/validar")
	public Mensagem validarContato(ClienteContato contato) {
		return contatoService.validarContato(contato);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return contatoService.validarExclusao(codigo);
	}
	
	@PUT
	public void put(ClienteContato contato) {
		contatoService.atualizar(contato);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		contatoService.remover(id);
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<ClienteContato> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "cliente") @QueryParam("cliente") String cliente) {
		return contatoPesquisa.executar(pagina, cliente);
	}
}