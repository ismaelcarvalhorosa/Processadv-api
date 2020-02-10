package br.inf.lucas.royalrangers.api.distrito;

import java.sql.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

import br.inf.lucas.royalrangers.api.comandante.Comandante;
import br.inf.lucas.royalrangers.api.regiao.Regiao;

@RequestScoped
public class DistritoPesquisa {

	@Inject
	EntityManager em;
	
	public PaginationResult<Distrito> executar(Integer pagina, String valor, String regiao) {
		
		return new QueryBuilder(em)
				.select(Distrito.class)
				.where(w -> {
					System.out.println(regiao);
					if (valor!=null && !valor.isEmpty()) {
						try {
							Long id = Long.valueOf(valor);
							w.field("discodigo").eq(id);
						} catch (Exception e) {
							try {
								w.field("disnome").ilike("%"+valor+"%");
							} catch (Exception e2) {
							}
						}
					}
					if (regiao!=null && !regiao.isEmpty()) {
						try {
							Long reg = Long.valueOf(regiao);
							if (reg>0) {
								System.out.println(reg);
								w.field("regiao.regcodigo").eq(reg);
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