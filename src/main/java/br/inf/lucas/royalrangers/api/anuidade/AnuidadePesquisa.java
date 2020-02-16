package br.inf.lucas.royalrangers.api.anuidade;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.select.pagination.PaginationResult;

@RequestScoped
public class AnuidadePesquisa {

	@Inject
	EntityManager em;
	
	private void logar(String mensagem) {
		Logger.getLogger(mensagem);
	}
	
	public PaginationResult<Anuidade> executar(Integer pagina, String valor, String dest, String explorador, String ano) {
		
		return new QueryBuilder(em)
				.select(Anuidade.class)
				.where(w -> {
					if (valor!=null && !valor.isEmpty()) {
						try {
							Long id = Long.valueOf(valor);
							w.field("anucodigo").eq(id);
						} catch (Exception e) {
							try {
								w.field("explorador.pessoa.pesnome").ilike("%"+valor+"%");
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
					if (explorador!=null && !explorador.isEmpty()) {
						try {
							Long exp = Long.valueOf(explorador);
							if (exp>0) {
								w.field("explorador.expcodigo").eq(exp);
							}
						} catch (Exception e) {
							logar(e.getMessage());
						}
					}
					if (ano!=null && !ano.isEmpty()) {
						try {
							Long a = Long.valueOf(ano);
							if (a>0) {
								w.field("anuano").eq(a);
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