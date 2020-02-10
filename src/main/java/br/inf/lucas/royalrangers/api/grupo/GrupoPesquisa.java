package br.inf.lucas.royalrangers.api.grupo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

@RequestScoped
public class GrupoPesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<Grupo> executar(Integer pagina, String valor) {
		
		return new QueryBuilder(em)
			.select(Grupo.class)
			.where().orGroup(w -> {
				if (valor!=null && !valor.isEmpty()) {
					try {
						Long id = Long.valueOf(valor);
						w.field(Grupo_.grucodigo).eq(id);
					} catch (Exception e) {
						try {
							w.field(Grupo_.grunome).ilike("%"+valor+"%");
						} catch (Exception e2) {
						}
					}
				}
			})
			.pagination()
				.page(pagina)
				.getResultList();	
	}	
}