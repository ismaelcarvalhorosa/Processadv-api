package br.inf.lucas.processadv.api.usuarios;
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
public class UsuariosService {

	@Inject
	EntityManager em;
	
	@Inject
	Validator validator;
	
	private void validar(Usuarios usuario) {
		Set<ConstraintViolation<Object>> validate = validator.validate(usuario);
		if (!validate.isEmpty()) {
			throw new ConstraintViolationException(validate);
		}
	}
	
	public List<Usuarios> tudo() {
		return new QueryBuilder(em)
			.select(Usuarios.class)
			.getResultList();
	}
	
	@Transactional
	public Long gravar(@Valid Usuarios usuario) {
		em.persist(usuario);
		return usuario.getUsuario_id();
	}

	public Usuarios busca(Long id) {
		return em.find(Usuarios.class, id);
	}

	@Transactional
	public void atualizar(Usuarios usuario) {
		this.validar(usuario);
		em.merge(usuario);
	}
	
	@Transactional
	public void remover(Long id) {
		em.remove(busca(id));
	}
	
	public Mensagem validarUsuario(Usuarios usuario) {
		Mensagem msg = new Mensagem();
		Session session = this.em.unwrap(Session.class);
		String sql = "select * from usuarios where upper(usuario_nome) = '"+usuario.getUsuario_nome().toUpperCase()+"'";
		if (usuario.getUsuario_id()>0) {
			sql += " and usuario_id<>"+usuario.getUsuario_id().toString();
		}
		Query qr = session.createSQLQuery(sql).addEntity(Usuarios.class);
		List<Usuarios> lista = qr.list();
		if (!lista.isEmpty()) {
			msg.setMensagem("Há outro usuário cadastrado igual a esse!");
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
        "WHERE constraint_type = 'FOREIGN KEY' AND ccu.table_name='usuarios'";
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
				msg.setMensagem("Há vínculos desse usuário com outros cadastros no sistema");
				return msg;
			}
		}
		msg.setMensagem("");
		return msg;
	}
	
	public Usuarios login(Usuarios usu) {
		Usuarios usuario = new Usuarios();
		Session session = this.em.unwrap(Session.class);
		String sql = "select * from usuarios where usuario_login='"+usu.getUsuario_login()+"' and usuario_senha='"+usu.getUsuario_senha()+"'";
		Query qr = session.createSQLQuery(sql).addEntity(Usuarios.class);
		List<Usuarios> lista = qr.list();
		if (!lista.isEmpty())
			usuario = lista.get(0);
		return usuario;
	}
}