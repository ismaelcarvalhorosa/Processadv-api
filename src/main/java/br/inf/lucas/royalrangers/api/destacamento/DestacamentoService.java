package br.inf.lucas.royalrangers.api.destacamento;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringType;

import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;

import br.inf.lucas.royalrangers.api.Mensagem;

@RequestScoped
public class DestacamentoService {

	@Inject
	EntityManager em;
	
	@Inject
	Validator validator;
	
	private void validar(Destacamento destacamento) {
		Set<ConstraintViolation<Object>> validate = validator.validate(destacamento);
		if (!validate.isEmpty()) {
			throw new ConstraintViolationException(validate);
		}
	}
	
	public List<Destacamento> tudo() {
		return new QueryBuilder(em)
			.select(Destacamento.class)
			.getResultList();
	}
	
	@Transactional
	public Long gravar(Destacamento destacamento) {
		Date data = new Date(System.currentTimeMillis());
		destacamento.setDesdatalt(data);
		em.persist(destacamento);
		return destacamento.getDescodigo();
	}

	public Destacamento busca(Long id) {
		return em.find(Destacamento.class, id);
	}

	@Transactional
	public void atualizar(Destacamento destacamento) {
		Date data = new Date(System.currentTimeMillis());
		destacamento.setDesdatalt(data);
		this.validar(destacamento);
		em.merge(destacamento);
	}
	
	@Transactional
	public void remover(Long id) {
		em.remove(busca(id));
	}
	
	public Mensagem validarExclusao(String codigo) {
		//destacamento em outros cadastros
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
        "WHERE constraint_type = 'FOREIGN KEY' AND ccu.table_name='destacamento'";
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
				msg.setMensagem("Há vínculos desse destacamento com outros cadastros no sistema");
				return msg;
			}
		}
		msg.setMensagem("");
		return msg;
	}
	
	public Mensagem validarDestacamento(Destacamento destacamento) {
		Mensagem msg = new Mensagem();
		Session session = this.em.unwrap(Session.class);
		String sql = "select * from destacamento where upper(desnome) = '"+destacamento.getDesnome().toUpperCase()+"'";
		if (destacamento.getDescodigo().intValue()>0)
			sql += " and descodigo<>"+String.valueOf(destacamento.getDescodigo().intValue());
		Query qr = session.createSQLQuery(sql).addEntity(Destacamento.class);
		List<Destacamento> lista = qr.list();
		if (!lista.isEmpty())
			msg.setMensagem("Já existe um destacamento com o nome informado!");
		else
			msg.setMensagem("");
		return msg;
	}
}