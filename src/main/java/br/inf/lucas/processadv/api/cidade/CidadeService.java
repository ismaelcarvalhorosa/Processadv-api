package br.inf.lucas.processadv.api.cidade;

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
import org.hibernate.type.StringType;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import br.inf.lucas.processadv.api.Mensagem;

@RequestScoped
public class CidadeService {

	@Inject
	EntityManager em;
	
	@Inject
	Validator validator;
	
	private void validar(Cidades cidade) {
		Set<ConstraintViolation<Object>> validate = validator.validate(cidade);
		if (!validate.isEmpty()) {
			throw new ConstraintViolationException(validate);
		}
	}
	
	public List<Cidades> tudo() {
		return new QueryBuilder(em)
			.select(Cidades.class)
			.getResultList();
	}
	
	@Transactional
	public Long gravar(@Valid Cidades cidade) {
		em.persist(cidade);
		return cidade.getCidade_id();
	}

	public Cidades busca(Long id) {
		return em.find(Cidades.class, id);
	}

	@Transactional
	public void atualizar(Cidades cidade) {
		this.validar(cidade);
		em.merge(cidade);
	}
	
	@Transactional
	public void remover(Long id) {
		em.remove(busca(id));
	}
	
	public Mensagem validarCidade(Cidades cidade) {		
		Mensagem msg = new Mensagem();
		Session session = this.em.unwrap(Session.class);
		String sql = "select * from cidade where upper(cidade_nome) = '"+cidade.getCidade_nome().toUpperCase()+
			   		"' and upper(cidade_uf)= '"+cidade.getCidade_uf()+"'";
		if (cidade.getCidade_id()>0) {
			sql += " and cidade_id<>"+cidade.getCidade_id().toString();
		}
		Query qr = session.createSQLQuery(sql).addEntity(Cidades.class);
		List<Cidades> lista = qr.list();
		if (!lista.isEmpty()) {
			msg.setMensagem("Há outro cidade cadastrado igual a esse!");
			return msg;
		}
		msg.setMensagem("");
		return msg;
	}
	
	public Mensagem validarExclusao(String codigo) {
		Mensagem msg = new Mensagem();
		Session session = this.em.unwrap(Session.class);
		String sql = "SELECT distinct tc.table_schema, tc.constraint_name, tc.table_name, kcu.column_name, "+
        "ccu.table_schema AS foreign_table_schema, ccu.table_name AS foreign_table_name, "+
        "ccu.column_name AS foreign_column_name FROM "+
        "information_schema.table_constraints AS tc "+
        "JOIN information_schema.key_column_usage AS kcu "+
        "ON tc.constraint_name = kcu.constraint_name "+
        "JOIN information_schema.constraint_column_usage AS ccu "+
        "ON ccu.constraint_name = tc.constraint_name "+
        "WHERE constraint_type = 'FOREIGN KEY' AND ccu.table_name='cidade'";
		Query qr = session.createSQLQuery(sql)
						   .addScalar("table_schema", new StringType())
						   .addScalar("constraint_name", new StringType())
						   .addScalar("table_name", new StringType())
						   .addScalar("column_name", new StringType())
						   .addScalar("foreign_table_schema", new StringType())
						   .addScalar("foreign_table_name", new StringType())
						   .addScalar("foreign_column_name", new StringType());
		List<Object[]> rows = qr.list();
		for(Object[] row : rows){
			sql = "SELECT * FROM "+row[2].toString()+
		    	      " where "+row[3].toString()+"="+codigo+" limit 1";
			qr = session.createSQLQuery(sql);
			if (!qr.list().isEmpty()) {
				msg.setMensagem("Há vínculos dessa cidade com outros cadastros no sistema");
				return msg;
			}
		}
		msg.setMensagem("");
		return msg;
	}
}