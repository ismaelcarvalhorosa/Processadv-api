package br.inf.lucas.royalrangers.api.distrito;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

@RequestScoped
public class DistritoPesquisa {

	@Inject
	EntityManager em;
	
	private void logar(String mensagem) {
		Logger.getLogger(mensagem);
	}
	
	public PaginationResult<Distrito> executar(Integer pagina, String valor, String regiao) {
		
		return new QueryBuilder(em)
				.select(Distrito.class)
				.where(w -> {
					if (valor!=null && !valor.isEmpty()) {
						try {
							Long id = Long.valueOf(valor);
							w.field("discodigo").eq(id);
						} catch (Exception e) {
							try {
								w.field("disnome").ilike("%"+valor+"%");
							} catch (Exception e2) {
								logar(e2.getMessage());
							}
						}
					}
					if (regiao!=null && !regiao.isEmpty()) {
						try {
							Long reg = Long.valueOf(regiao);
							if (reg>0) {
								w.field("regiao.regcodigo").eq(reg);
							}
						} catch (Exception e) {
							logar(e.getMessage());
						}
					}
				})
				.pagination()
					.page(pagina)
					.getResultList();
		}
	}