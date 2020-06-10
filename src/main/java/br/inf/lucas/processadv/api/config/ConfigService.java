package br.inf.lucas.processadv.api.config;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;

@RequestScoped
public class ConfigService {

	@Inject
	EntityManager em;
	
	@Inject
	Validator validator;
	
	private void validar(Config config) {
		Set<ConstraintViolation<Object>> validate = validator.validate(config);
		if (!validate.isEmpty()) {
			throw new ConstraintViolationException(validate);
		}
	}
	
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
				.field("nome").ilike("%"+nome+"%")
			.getResultList();
	}
	
	@Transactional
	public void atualizar(Config config) {
		this.validar(config);
		em.merge(config);
	}
}