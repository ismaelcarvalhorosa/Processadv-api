package br.inf.lucas.processadv.api.cliente_obs;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;

import br.inf.lucas.processadv.api.Mensagem;
import br.inf.lucas.processadv.api.cliente.Cliente;

@RequestScoped
public class Cliente_obsService {

	@Inject
	EntityManager em;
	
	@Inject
	Validator validator;
	
	private void validar(Cliente_obs cliente_obs) {
		Set<ConstraintViolation<Object>> validate = validator.validate(cliente_obs);
		if (!validate.isEmpty()) {
			throw new ConstraintViolationException(validate);
		}
	}
	
	public List<Cliente_obs> tudo() {
		return new QueryBuilder(em)
			.select(Cliente_obs.class)
			.getResultList();
	}
	
	@Transactional
	public Long gravar(@Valid Cliente_obs cliente_obs) {
		em.persist(cliente_obs);
		return cliente_obs.getObservacao_id();
	}

	public Cliente_obs busca(Long id) {
		return em.find(Cliente_obs.class, id);
	}

	@Transactional
	public void atualizar(Cliente_obs cliente_obs) {
		this.validar(cliente_obs);
		em.merge(cliente_obs);
	}
	
	@Transactional
	public void remover(Long id) {
		em.remove(busca(id));
	}
	
	public Cliente_obs buscaByCliente(Integer id) {
		Cliente_obs obs = new Cliente_obs();
		Session session = this.em.unwrap(Session.class);
		String sql = "select * from cliente_obs where cliente_id = "+id.toString();
		Query qr = session.createSQLQuery(sql).addEntity(Cliente_obs.class);
		List<Cliente_obs> lista = qr.list();
		if (!lista.isEmpty()) {
			obs = lista.get(0);
		}
		return obs;
	}
}