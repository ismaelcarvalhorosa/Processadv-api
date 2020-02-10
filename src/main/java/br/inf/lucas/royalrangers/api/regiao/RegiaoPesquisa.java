package br.inf.lucas.royalrangers.api.regiao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

@RequestScoped
public class RegiaoPesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<Regiao> executar(Integer pagina, String valor) {
		
		return new QueryBuilder(em)
			.select(Regiao.class)
			.where().orGroup(w -> {
				if (valor!=null && !valor.isEmpty()) {
					try {
						Long id = Long.valueOf(valor);
						w.field(Regiao_.regcodigo).eq(id);
					} catch (Exception e) {
						w.field(Regiao_.regnome).ilike("%"+valor+"%");
					}
				}
			})
			.pagination()
				.page(pagina)
				.getResultList();	
	}	
}