package br.inf.lucas.royalrangers.api.igreja;

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

import java.sql.Date;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;

import br.inf.lucas.royalrangers.api.Mensagem;
import br.inf.lucas.royalrangers.api.regiao.Regiao;

@RequestScoped
public class IgrejaService {

	@Inject
	EntityManager em;
	
	@Inject
	Validator validator;
	
	private void validar(Igreja igreja) {
		Set<ConstraintViolation<Object>> validate = validator.validate(igreja);
		if (!validate.isEmpty()) {
			throw new ConstraintViolationException(validate);
		}
	}
	
	public List<Igreja> tudo() {
		return new QueryBuilder(em)
			.select(Igreja.class)
			.getResultList();
	}
	
	@Transactional
	public Long gravar(Igreja igreja) {
		Date data = new Date(System.currentTimeMillis());
		igreja.setIgrdatalt(data);
		em.persist(igreja);
		return igreja.getIgrcodigo();
	}

	public Igreja busca(Long id) {
		return em.find(Igreja.class, id);
	}

	@Transactional
	public void atualizar(Igreja igreja) {
		Date data = new Date(System.currentTimeMillis());
		igreja.setIgrdatalt(data);
		this.validar(igreja);
		em.merge(igreja);
	}
	
	@Transactional
	public void remover(Long id) {
		em.remove(busca(id));
	}
	
	public Mensagem validarExclusao(String codigo) {
		//igreja em outros cadastros
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
        "WHERE constraint_type = 'FOREIGN KEY' AND ccu.table_name='igreja'";
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
				msg.mensagem = "Há vínculos dessa igreja com outros cadastros no sistema";
				return msg;
			}
		}
		msg.mensagem = "";
		return msg;
	}
	
	public Mensagem validarIgreja(Igreja igreja) {
		Mensagem msg = new Mensagem();
		Session session = this.em.unwrap(Session.class);
		String sql = "select * from igreja where upper(igrnome) = '"+igreja.getIgrnome().toUpperCase()+"'";
		if (igreja.getIgrcodigo().intValue()>0)
			sql += " and igrcodigo<>"+String.valueOf(igreja.getIgrcodigo().intValue());
		Query qr = session.createSQLQuery(sql).addEntity(Igreja.class);
		List<Igreja> lista = qr.list();
		qr = null;
		session = null;
		if (!lista.isEmpty())
			msg.mensagem = "Já existe uma igreja com o nome informado!";
		else
			msg.mensagem = "";
		return msg;
	}
}