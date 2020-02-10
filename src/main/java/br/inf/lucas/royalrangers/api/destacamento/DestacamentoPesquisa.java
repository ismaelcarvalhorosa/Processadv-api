package br.inf.lucas.royalrangers.api.destacamento;

import java.sql.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

import br.inf.lucas.royalrangers.api.comandante.Comandante;

@RequestScoped
public class DestacamentoPesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<Destacamento> executar(Integer pagina, String valor, String distrito, String cidade, String igreja) {
		
		return new QueryBuilder(em)
				.select(Destacamento.class)
				.where(w -> {
					if (valor!=null && !valor.isEmpty()) {
						try {
							Long id = Long.valueOf(valor);
							w.field("descodigo").eq(id);
						} catch (Exception e) {
							try {
								w.field("desnome").ilike("%"+valor+"%");
							} catch (Exception e2) {
							}
						}
					}
					if (distrito!=null && !distrito.isEmpty()) {
						try {
							Long dis = Long.valueOf(distrito);
							if (dis>0) {
								w.field("distrito.discodigo").eq(dis);
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
					if (igreja!=null && !igreja.isEmpty()) {
						try {
							Long igr = Long.valueOf(igreja);
							if (igr>0) {
								w.field("igreja.igrcodigo").eq(igr);
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