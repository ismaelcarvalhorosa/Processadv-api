package br.inf.lucas.processadv.api.usuarios;
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

@Tag(name = "Usuarios")
@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuariosResource {

	@Inject
	UsuariosService usuarioService;
	
	@Inject
	UsuariosPesquisa usuarioPesquisa;
	
	@POST
	public Long post(Usuarios usuario) {
		return usuarioService.gravar(usuario);
	}
	
	@GET
	@Path("/{id}")
	public Usuarios get(@PathParam("id") Long id) {
		return usuarioService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Usuarios> getTodas() {
		return usuarioService.tudo();
	}
	
	@POST
	@Path("/validar")
	public Mensagem validarUsuario(Usuarios usuario) {
		return usuarioService.validarUsuario(usuario);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return usuarioService.validarExclusao(codigo);
	}
	
	@PUT
	public void put(Usuarios usuario) {
		usuarioService.atualizar(usuario);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		usuarioService.remover(id);
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<Usuarios> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "valor") @QueryParam("valor") String valor) {
		return usuarioPesquisa.executar(pagina, valor);
	}
	
	@POST
	@Path("/login")
	public Usuarios login(Usuarios usuario) {
		return usuarioService.login(usuario);
	}
}