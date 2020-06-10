package br.inf.lucas.royalrangers.api.cidade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

import br.inf.lucas.royalrangers.api.UF;

@RequestScoped
public class CidadePesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<Cidade> executar(Integer pagina, String valor) {
		
		return new QueryBuilder(em)
			.select(Cidade.class)
			.where().orGroup(w -> {
				if (valor!=null && !valor.isEmpty()) {
					try {
						Long id = Long.valueOf(valor);
						w.field(Cidade_.cidcodigo).eq(id);
					} catch (Exception e) {
						try {
							UF uf = UF.valueOf(valor.toUpperCase());
							w.field(Cidade_.ciduf).eq(uf);
						} catch (Exception e2) {
							w.field(Cidade_.cidnome).ilike("%"+valor+"%");
						}
					}
				}
			})
			.pagination()
				.page(pagina)
				.getResultList();	
	}	
}