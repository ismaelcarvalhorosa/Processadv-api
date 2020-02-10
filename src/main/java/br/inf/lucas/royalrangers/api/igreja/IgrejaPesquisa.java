package br.inf.lucas.royalrangers.api.igreja;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

@RequestScoped
public class IgrejaPesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<Igreja> executar(Integer pagina, String valor) {
		
		return new QueryBuilder(em)
			.select(Igreja.class)
			.where().orGroup(w -> {
				if (valor!=null && !valor.isEmpty()) {
					try {
						Long id = Long.valueOf(valor);
						w.field("igrcodigo").eq(id);
					} catch (Exception e) {
						w.field("igrnome").ilike("%"+valor+"%");
					}
				}
			})
			.pagination()
				.page(pagina)
				.getResultList();	
	}	
}