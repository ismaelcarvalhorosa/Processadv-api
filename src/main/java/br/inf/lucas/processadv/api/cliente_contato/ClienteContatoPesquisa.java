package br.inf.lucas.processadv.api.cliente_contato;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

@RequestScoped
public class ClienteContatoPesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<ClienteContato> executar(Integer pagina, String cliente) {
		return new QueryBuilder(em)
			.select(ClienteContato.class)
			.where().orGroup(w -> {
				if (cliente!=null && !cliente.isEmpty()) {
					try {
						Long id = Long.valueOf(cliente);
						w.field("cliente_id").eq(id);
					} catch (Exception e) {
					}
				}
			})
			.order().asc("descricao")
			.pagination()
				.page(pagina)
				.getResultList();	
	}	
}