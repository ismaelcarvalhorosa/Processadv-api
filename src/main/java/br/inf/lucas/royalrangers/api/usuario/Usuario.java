package br.inf.lucas.royalrangers.api.usuario;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="usuario", schema="public")
@SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
public class Usuario {

	@Id
	@Column(name = "USUCODIGO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
	private Long usucodigo;
	
	@Column(name = "USUNOME")
	@NotNull
	@Size(max = 100)
	private String usunome;
	
	@Column(name = "USULOGIN")
	@NotNull
	@Size(max = 100)
	private String usulogin;
	
	@Column(name = "USUSENHA")
	@NotNull
	@Size(max = 100)
	private String ususenha;
	
	@Column(name = "USUDATALT")
	@NotNull
	private Date usudatalt;
	
	@Column(name = "USUPERMISSAO")
	@NotNull
	private int usupermissao;
	
	@Column(name = "USUEMAIL")
	@Size(max = 100)
	private String usuemail;

	public Long getUsucodigo() {
		return usucodigo;
	}

	public void setUsucodigo(Long usucodigo) {
		this.usucodigo = usucodigo;
	}

	public String getUsunome() {
		return usunome;
	}

	public void setUsunome(String usunome) {
		this.usunome = usunome;
	}

	public String getUsulogin() {
		return usulogin;
	}

	public void setUsulogin(String usulogin) {
		this.usulogin = usulogin;
	}

	public String getUsusenha() {
		return ususenha;
	}

	public void setUsusenha(String ususenha) {
		this.ususenha = ususenha;
	}

	public Date getUsudatalt() {
		return usudatalt;
	}

	public void setUsudatalt(Date usudatalt) {
		this.usudatalt = usudatalt;
	}

	public int getUsupermissao() {
		return usupermissao;
	}

	public void setUsupermissao(int usupermissao) {
		this.usupermissao = usupermissao;
	}

	public String getUsuemail() {
		return usuemail;
	}

	public void setUsuemail(String usuemail) {
		this.usuemail = usuemail;
	}
}
