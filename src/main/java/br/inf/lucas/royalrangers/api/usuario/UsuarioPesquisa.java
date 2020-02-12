package br.inf.lucas.royalrangers.api.usuario;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

@RequestScoped
public class UsuarioPesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<Usuario> executar(Integer pagina, String valor, String dest) {
		
		return new QueryBuilder(em)
			.select(Usuario.class)
			.where().orGroup(w -> {
				if (valor!=null && !valor.isEmpty()) {
					try {
						Long id = Long.valueOf(valor);
						w.field(Usuario_.usucodigo).eq(id);
					} catch (Exception e) {
						w.field(Usuario_.usunome).ilike("%"+valor+"%");
					}
				}
				if (dest!=null && !dest.isEmpty()) {
					try {
						Long des = Long.valueOf(dest);
						if (des>0) {
							w.field("destacamento.descodigo").eq(des);
						}
					} catch (Exception e) {
					}
				}
			})
			.pagination()
				.page(pagina)
				.getResultList();	
	}	
}