package br.inf.lucas.royalrangers.api.usuario;

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
import br.inf.lucas.royalrangers.api.cidade.Cidade;

@RequestScoped
public class UsuarioService {

	@Inject
	EntityManager em;
	
	@Inject
	Validator validator;
	
	private void validar(Usuario usuario) {
		Set<ConstraintViolation<Object>> validate = validator.validate(usuario);
		if (!validate.isEmpty()) {
			throw new ConstraintViolationException(validate);
		}
	}
	
	public List<Usuario> tudo() {
		return new QueryBuilder(em)
			.select(Usuario.class)
			.getResultList();
	}
	
	@Transactional
	public Long gravar(Usuario usuario) {
		Date data = new Date(System.currentTimeMillis());
		usuario.setUsudatalt(data);
		em.persist(usuario);
		return usuario.getUsucodigo();
	}

	public Usuario busca(Long id) {
		return em.find(Usuario.class, id);
	}

	@Transactional
	public void atualizar(Usuario usuario) {
		Date data = new Date(System.currentTimeMillis());
		usuario.setUsudatalt(data);
		this.validar(usuario);
		em.merge(usuario);
	}
	
	@Transactional
	public void remover(Long id) {
		em.remove(busca(id));
	}
	
	public Mensagem validarExclusao(String codigo) {
		//usuario em outros cadastros
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
        "WHERE constraint_type = 'FOREIGN KEY' AND ccu.table_name='usuario'";
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
				msg.mensagem = "Há vínculos desse usuário com outros cadastros no sistema";
				return msg;
			}
		}
		msg.mensagem = "";
		return msg;
	}
	
	public String validarCampo(String descricao, String campo, String valor, int codigo) {
		Session session = this.em.unwrap(Session.class);
		String sql = "select * from usuario where upper("+campo+") = '"+valor.toUpperCase()+"'";
		if (codigo>0)
			sql += " and usucodigo<>"+String.valueOf(codigo);
		Query qr = session.createSQLQuery(sql).addEntity(Usuario.class);
		List<Usuario> lista = qr.list();
		qr = null;
		session = null;
		if (!lista.isEmpty())
		{
			lista = null;
			return "Esse " + descricao + " já está em uso!";
		}
		else
		{
			lista = null;
			return "";
		}
	}
	
	public Mensagem validarUsuario(Usuario usuario) {
		Mensagem msg = new Mensagem();
		msg.mensagem = "";
		String retorno = "";
		retorno = validarCampo("nome", "usunome", usuario.getUsunome(), usuario.getUsucodigo().intValue());
		if (retorno != "") msg.mensagem = msg.mensagem+""+retorno;
		retorno = validarCampo("login", "usulogin", usuario.getUsulogin(), usuario.getUsucodigo().intValue());
		if (retorno != "") msg.mensagem = msg.mensagem+""+retorno;
		retorno = validarCampo("e-mail", "usuemail", usuario.getUsuemail(), usuario.getUsucodigo().intValue());
		if (retorno != "") msg.mensagem = msg.mensagem+""+retorno;
		return msg;
	}
	
	public Usuario login(String login, String senha) {
		Usuario usuario = new Usuario();
		Session session = this.em.unwrap(Session.class);
		String sql = "select * from usuario where usulogin='"+login+"' and ususenha='"+senha+"'";
		System.out.println(sql);
		Query qr = session.createSQLQuery(sql).addEntity(Usuario.class);
		List<Usuario> lista = qr.list();
		if (!lista.isEmpty())
			usuario = lista.get(0);
		lista = null;
		qr = null;
		session = null;
		return usuario;
	}
}