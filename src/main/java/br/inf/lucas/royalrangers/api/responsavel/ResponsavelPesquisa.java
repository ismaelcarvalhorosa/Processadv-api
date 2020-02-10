package br.inf.lucas.royalrangers.api.responsavel;
import java.sql.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

import br.inf.lucas.royalrangers.api.comandante.Comandante;

@RequestScoped
public class ResponsavelPesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<Responsavel> executar(Integer pagina, String valor, String dest, String cidade) {
		
		return new QueryBuilder(em)
				.select(Responsavel.class)
				.where(w -> {
					if (valor!=null && !valor.isEmpty()) {
						try {
							Long id = Long.valueOf(valor);
							w.field("rescodigo").eq(id);
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
				})
				.pagination()
					.page(pagina)
					.getResultList();
		}
}