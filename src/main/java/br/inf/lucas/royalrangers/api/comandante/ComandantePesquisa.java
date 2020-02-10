package br.inf.lucas.royalrangers.api.comandante;
import java.sql.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

@RequestScoped
public class ComandantePesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<Comandante> executar(Integer pagina, String valor, String dest, String cidade, String grupo, String situacao) {
		
		return new QueryBuilder(em)
			.select(Comandante.class)
			.where(w -> {
				if (valor!=null && !valor.isEmpty()) {
					try {
						Long id = Long.valueOf(valor);
						w.field("comcodigo").eq(id);
					} catch (Exception e) {
						try {
							w.field("pessoa.pesnome").ilike("%"+valor+"%");
						} catch (Exception e2) {
						}
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
				if (cidade!=null && !cidade.isEmpty()) {
					try {
						Long cid = Long.valueOf(cidade);
						if (cid>0) {
							w.field("cidade.cidcodigo").eq(cid);
						}
					} catch (Exception e) {
					}
				}
				if (grupo!=null && !grupo.isEmpty()) {
					try {
						Long gru = Long.valueOf(grupo);
						if (gru>0) {
							w.field("grupo.grucodigo").eq(gru);
						}
					} catch (Exception e) {
					}
				}
				if (situacao!=null && !situacao.isEmpty()) {
					try {
						Long sit = Long.valueOf(situacao);
						Date data = new Date(System.currentTimeMillis());
						if (sit==1) {
							w.field("comdatini").lessThanOrEqual(data);
						}
						else if (sit==2) {
							w.field("comdatini").greaterThan(data);
							w.field("comdatfim").lessThan(data);
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