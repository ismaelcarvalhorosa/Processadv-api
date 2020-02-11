package br.inf.lucas.royalrangers.api.regiao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import br.inf.lucas.royalrangers.api.usuario.Usuario;

@Entity
@Table(name="regiao", schema="public")
@SequenceGenerator(name = "SEQ_REGIAO", sequenceName = "SEQ_REGIAO", allocationSize = 1)
public class Regiao {

	@Id
	@Column(name = "REGCODIGO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REGIAO")
	private Long regcodigo;
	
	@Column(name = "REGNOME")
	@NotNull
	@Size(max = 100)
	private String regnome;
	
	@Column(name = "REGDATALT")
	@NotNull
	private Date regdatalt;
	
	@Column(name = "USUCODIGO")
	@NotNull
	private int usucodigo;

	public Long getRegcodigo() {
		return regcodigo;
	}

	public void setRegcodigo(Long regcodigo) {
		this.regcodigo = regcodigo;
	}

	public String getRegnome() {
		return regnome;
	}

	public void setRegnome(String regnome) {
		this.regnome = regnome;
	}

	public Date getRegdatalt() {
		return regdatalt;
	}

	public void setRegdatalt(Date regdatalt) {
		this.regdatalt = regdatalt;
	}

	public int getUsucodigo() {
		return usucodigo;
	}

	public void setUsucodigo(int usucodigo) {
		this.usucodigo = usucodigo;
	}

	/*public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}*/
}