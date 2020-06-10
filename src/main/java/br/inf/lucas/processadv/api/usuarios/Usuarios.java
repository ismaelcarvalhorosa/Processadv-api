package br.inf.lucas.processadv.api.usuarios;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="usuarios", schema="public")
@SequenceGenerator(name = "seq_usuarios", sequenceName = "seq_usuarios", allocationSize = 1)
public class Usuarios {
	@Id
	@Column(name = "USUARIO_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuarios")
	private Long usuario_id;
	
	@Column(name = "USUARIO_NOME")
	@Size(max = 60)
	private String usuario_nome;
	
	@Column(name = "USUARIO_LOGIN")
	@Size(max = 60)
	private String usuario_login;
	
	@Column(name = "USUARIO_SENHA")
	@Size(max = 60)
	private String usuario_senha;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "NIVEL_ACESSO")
	private Integer nivel_acesso;
	
	@Column(name = "COLABORADOR_ID")
	private Integer colaborador_id;
	
	@Column(name = "AGENDA")
	private Integer agenda;
	
	@Column(name = "USUARIO_LOGO")
	private String logo;

	public Long getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}

	public String getUsuario_nome() {
		return usuario_nome;
	}

	public void setUsuario_nome(String usuario_nome) {
		this.usuario_nome = usuario_nome;
	}

	public String getUsuario_login() {
		return usuario_login;
	}

	public void setUsuario_login(String usuario_login) {
		this.usuario_login = usuario_login;
	}

	public String getUsuario_senha() {
		return usuario_senha;
	}

	public void setUsuario_senha(String usuario_senha) {
		this.usuario_senha = usuario_senha;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getNivel_acesso() {
		return nivel_acesso;
	}

	public void setNivel_acesso(Integer nivel_acesso) {
		this.nivel_acesso = nivel_acesso;
	}

	public Integer getColaborador_id() {
		return colaborador_id;
	}

	public void setColaborador_id(Integer colaborador_id) {
		this.colaborador_id = colaborador_id;
	}

	public Integer getAgenda() {
		return agenda;
	}

	public void setAgenda(Integer agenda) {
		this.agenda = agenda;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
}