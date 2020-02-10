package br.inf.lucas.royalrangers.api.usuario;

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
import br.inf.lucas.royalrangers.api.regiao.Regiao;
import br.inf.lucas.royalrangers.api.regiao.RegiaoPesquisa;

@Tag(name = "Usuario")
@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

	@Inject
	UsuarioService usuarioService;
	
	@Inject
	UsuarioPesquisa usuarioPesquisa;
	
	@POST
	public Long post(Usuario usuario) {
		return usuarioService.gravar(usuario);
	}
	
	@GET
	@Path("/{id}")
	public Usuario get(@PathParam("id") Long id) {
		return usuarioService.busca(id);
	}
	
	@GET
	@Path("/tudo")
	public List<Usuario> getTodos() {
		return usuarioService.tudo();
	}
	
	@PUT
	public void put(Usuario usuario) {
		usuarioService.atualizar(usuario);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		usuarioService.remover(id);
	}
	
	@GET
	@Path("/pesquisa")
	public PaginationResult<Usuario> pesquisa(
			@QueryParam("pagina") Integer pagina,
			@Parameter(required = false, name = "valor") @QueryParam("valor") String valor) {
		return usuarioPesquisa.executar(pagina, valor);
	}
	
	@GET
	@Path("/validarexclusao")
	public Mensagem validarExclusao(
			@Parameter(required = false, name = "codigo") @QueryParam("codigo") String codigo) {
		return usuarioService.validarExclusao(codigo);
	}
	
	@POST
	@Path("/validarusuario")
	public Mensagem validarUsuario(Usuario usuario) {
		return usuarioService.validarUsuario(usuario);
	}
	
	@GET
	@Path("/login")
	public Usuario login(
			@Parameter(required = false, name = "login") @QueryParam("login") String login,
			@Parameter(required = false, name = "senha") @QueryParam("senha") String senha) {
		return usuarioService.login(login, senha);
	}
}