package br.inf.lucas.royalrangers.api.cidade;

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

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;
import br.inf.lucas.royalrangers.api.Mensagem;

@RequestScoped
public class CidadeService {

	@Inject
	EntityManager em;
	
	@Inject
	Validator validator;
	
	private void validar(Cidade cidade) {
		Set<ConstraintViolation<Object>> validate = validator.validate(cidade);
		if (!validate.isEmpty()) {
			throw new ConstraintViolationException(validate);
		}
	}
	
	public List<Cidade> tudo() {
		return new QueryBuilder(em)
			.select(Cidade.class)
			.getResultList();
	}
	
	@Transactional
	public Long gravar(@Valid Cidade cidade) {
		em.persist(cidade);
		return cidade.getCidcodigo();
	}

	public Cidade busca(Long id) {
		return em.find(Cidade.class, id);
	}

	@Transactional
	public void atualizar(Cidade cidade) {
		this.validar(cidade);
		em.merge(cidade);
	}
	
	@Transactional
	public void remover(Long id) {
		em.remove(busca(id));
	}
	
	public Mensagem validarCidade(Cidade cidade) {
		//cidade duplicada no banco
		Mensagem msg = new Mensagem();
		Session session = this.em.unwrap(Session.class);
		String sql = "select * from cidade where upper(cidnome) = '"+cidade.getCidnome().toUpperCase()+
				 	 "' and upper(ciduf)= '"+cidade.getCiduf()+"'";
		if (cidade.getCidcodigo()>0) {
			sql += " and cidcodigo<>"+cidade.getCidcodigo().toString();
		}
		Query qr = session.createSQLQuery(sql).addEntity(Cidade.class);
		List<Cidade> lista = qr.list();
		qr = null;
		session = null;
		if (!lista.isEmpty()) {
			lista = null;
			msg.mensagem = "Há outra cidade cadastrada igual a essa!";
			return msg;
		}
		msg.mensagem = "";
		return msg;
	}
	
	public Mensagem validarExclusao(String codigo) {
		//cidade em outros cadastros
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
				msg.mensagem = "Há vínculos dessa cidade com outros cadastros no sistema";
				return msg;
			}
		}
		msg.mensagem = "";
		return msg;
	}
}