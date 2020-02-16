package br.inf.lucas.royalrangers.api.comandante;

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

import java.sql.Date;
import com.ordnaelmedeiros.jpafluidselect.querybuilder.QueryBuilder;

import br.inf.lucas.royalrangers.api.Mensagem;

@RequestScoped
public class ComandanteService {

	@Inject
	EntityManager em;
	
	@Inject
	Validator validator;
	
	private void validar(Comandante pessoa) {
		Set<ConstraintViolation<Object>> validate = validator.validate(pessoa);
		if (!validate.isEmpty()) {
			throw new ConstraintViolationException(validate);
		}
	}
	
	public List<Comandante> tudo() {
		return new QueryBuilder(em)
			.select(Comandante.class)
			.getResultList();
	}
	
	@Transactional
	public Long gravar(Comandante comandante) {
		Date data = new Date(System.currentTimeMillis());
		comandante.setComdatalt(data);
		em.persist(comandante);
		return comandante.getComcodigo();
	}

	public Comandante busca(Long id) {
		return em.find(Comandante.class, id);
	}

	@Transactional
	public void atualizar(Comandante comandante) {
		Date data = new Date(System.currentTimeMillis());
		comandante.setComdatalt(data);
		this.validar(comandante);
		em.merge(comandante);
	}
	
	@Transactional
	public void remover(Long id) {
		em.remove(busca(id));
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
        "WHERE constraint_type = 'FOREIGN KEY' AND ccu.table_name='comandante'";
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
				msg.setMensagem("Há vínculos desse comandante com outros cadastros no sistema");
				return msg;
			}
		}
		msg.setMensagem("");
		return msg;
	}
	
	public Mensagem validarComandante(Comandante comandante) {
		Mensagem msg = new Mensagem();
		msg.setMensagem("");
		Session session = this.em.unwrap(Session.class);
		String sql = "select * from comandante where pescodigo = "+comandante.getPessoa().getPescodigo().toString()+"";
		if (comandante.getComcodigo().intValue()>0)
			sql += " and comcodigo<>"+String.valueOf(comandante.getComcodigo().intValue());
		Query qr = session.createSQLQuery(sql).addEntity(Comandante.class);
		List<Comandante> lista = qr.list();
		if (!lista.isEmpty())
			msg.setMensagem(msg.getMensagem()+"Já existe um comandante para a pessoa informada!");
		return msg;
	}
}