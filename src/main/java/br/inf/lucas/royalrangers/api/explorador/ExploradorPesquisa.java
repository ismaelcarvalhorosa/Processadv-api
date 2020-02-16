package br.inf.lucas.royalrangers.api.explorador;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

@RequestScoped
public class ExploradorPesquisa {

	@Inject
	EntityManager em;
	
	private void logar(String mensagem) {
		Logger.getLogger(mensagem);
	}
	
	public PaginationResult<Explorador> executar(Integer pagina, String valor, String dest, String cidade, String responsavel) {
		
		return new QueryBuilder(em)
				.select(Explorador.class)
				.where(w -> {
					if (valor!=null && !valor.isEmpty()) {
						try {
							Long id = Long.valueOf(valor);
							w.field("expcodigo").eq(id);
						} catch (Exception e) {
							try {
								w.field("pessoa.pesnome").ilike("%"+valor+"%");
							} catch (Exception e2) {
								logar(e2.getMessage());
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
							logar(e.getMessage());
						}
					}
					if (cidade!=null && !cidade.isEmpty()) {
						try {
							Long cid = Long.valueOf(cidade);
							if (cid>0) {
								w.field("cidade.cidcodigo").eq(cid);
							}
						} catch (Exception e) {
							logar(e.getMessage());
						}
					}
					if (responsavel!=null && !responsavel.isEmpty()) {
						try {
							Long res = Long.valueOf(responsavel);
							if (res>0) {
								w.field("responsavel.rescodigo").eq(res);
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
	
public PaginationResult<Explorador> exploradoresByResponsavel(Integer pagina, String responsavel) {
		
		return new QueryBuilder(em)
				.select(Explorador.class)
				.where(w -> {
					if (responsavel!=null && !responsavel.isEmpty()) {
						try {
							Long id = Long.valueOf(responsavel);
							w.field("responsavel.rescodigo").eq(id);
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