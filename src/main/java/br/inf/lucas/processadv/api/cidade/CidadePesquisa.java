package br.inf.lucas.processadv.api.cidade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

@RequestScoped
public class CidadePesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<Cidades> executar(Integer pagina, String valor) {		
		return new QueryBuilder(em)
			.select(Cidades.class)
			.where().orGroup(w -> {
				if (valor!=null && !valor.isEmpty()) {
					try {
						Long id = Long.valueOf(valor);
						w.field("Cidade_.cidade_id").eq(id);
					} catch (Exception e) {
						try {							
							w.field("cidade_nome").ilike("%"+valor+"%");
							w.field("cidade_uf").ilike("%"+valor+"%");
						} catch (Exception e2) {
							w.field(Cidades_.cidade_nome).ilike("%"+valor+"%");
						}
					}
				}
			})
			.order().asc("cidade_nome")
			.pagination()
				.page(pagina)
				.getResultList();	
	}	
}