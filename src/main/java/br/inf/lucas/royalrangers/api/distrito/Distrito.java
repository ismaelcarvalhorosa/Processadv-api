package br.inf.lucas.royalrangers.api.distrito;

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
import br.inf.lucas.royalrangers.api.regiao.Regiao;
//import br.inf.lucas.royalrangers.api.usuario.Usuario;

@Entity
@Table(name="distrito", schema="public")
@SequenceGenerator(name = "SEQ_DISTRITO", sequenceName = "SEQ_DISTRITO", allocationSize = 1)
public class Distrito {

	@Id
	@Column(name = "DISCODIGO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DISTRITO")
	private Long discodigo;
	
	@Column(name = "DISNOME")
	@NotNull
	@Size(max = 100)
	private String disnome;
	
	@ManyToOne
	@JoinColumn(name = "REGCODIGO")
	private Regiao regiao;
	
	@Column(name = "DISDATALT")
	@NotNull
	private Date disdatalt;
	
	/*@ManyToOne
	@JoinColumn(name = "USUCODIGO")
	private Usuario usuario;*/
	
	@Column(name = "USUCODIGO")
	@NotNull
	private int usucodigo;

	public Long getDiscodigo() {
		return discodigo;
	}

	public void setDiscodigo(Long discodigo) {
		this.discodigo = discodigo;
	}

	public String getDisnome() {
		return disnome;
	}

	public void setDisnome(String disnome) {
		this.disnome = disnome;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	public Date getDisdatalt() {
		return disdatalt;
	}

	public void setDisdatalt(Date disdatalt) {
		this.disdatalt = disdatalt;
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