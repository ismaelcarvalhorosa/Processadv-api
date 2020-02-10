package br.inf.lucas.royalrangers.api.pessoa;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

@RequestScoped
public class PessoaPesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<Pessoa> executar(Integer pagina, String valor) {
		
		return new QueryBuilder(em)
			.select(Pessoa.class)
			.where().orGroup(w -> {
				if (valor!=null && !valor.isEmpty()) {
					try {
						Long id = Long.valueOf(valor);
						w.field("pescodigo").eq(id);
					} catch (Exception e) {
						w.field("pesnome").ilike("%"+valor+"%");
					}
				}
			})
			.pagination()
				.page(pagina)
				.getResultList();	
	}	
}