package br.inf.lucas.processadv.api.cliente;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

@RequestScoped
public class ClientePesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<Cliente> executar(Integer pagina, String valor) {
		return new QueryBuilder(em)
			.select(Cliente.class)
			.where().orGroup(w -> {
				if (valor!=null && !valor.isEmpty()) {
					try {
						Long id = Long.valueOf(valor);
						w.field("cliente_id").eq(id);
					} catch (Exception e) {
						try {
							w.field("nome").ilike("%"+valor+"%");
						} catch (Exception e2) {
							Logger.getLogger(e2.getMessage());
						}
					}
				}
			})
			.order().asc("nome")
			.pagination()
				.page(pagina)
				.getResultList();	
	}	
}