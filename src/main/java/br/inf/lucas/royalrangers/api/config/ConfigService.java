package br.inf.lucas.royalrangers.api.config;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;

import br.inf.lucas.royalrangers.api.config.Config_;

@RequestScoped
public class ConfigService {

	@Inject
	EntityManager em;
	
	public List<Config> tudo() {
		return new QueryBuilder(em)
			.select(Config.class)
			.getResultList();
	}
	
	public Config busca(Long id) {
		return em.find(Config.class, id);
	}
	
	public List<Config> porNome(String nome) {
		
		return new QueryBuilder(em)
			.select(Config.class)
			.where()
				.field(Config_.nome).ilike("%"+nome+"%")
			.getResultList();
		
	}	
}