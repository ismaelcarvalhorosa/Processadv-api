package br.inf.lucas.processadv.api.usuarios;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

@RequestScoped
public class UsuariosPesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<Usuarios> executar(Integer pagina, String valor) {
		return new QueryBuilder(em)
			.select(Usuarios.class)
			.where().orGroup(w -> {
				if (valor!=null && !valor.isEmpty()) {
					try {
						Long id = Long.valueOf(valor);
						w.field("usuario_id").eq(id);
					} catch (Exception e) {
						try {
							w.field("usuario_nome").ilike("%"+valor+"%");
						} catch (Exception e2) {
							Logger.getLogger(e2.getMessage());
						}
					}
				}
			})
			.order().asc("usuario_nome")
			.pagination()
				.page(pagina)
				.getResultList();	
	}	
}