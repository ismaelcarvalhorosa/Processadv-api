package br.inf.lucas.royalrangers.api.pessoa;

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

import br.inf.lucas.royalrangers.api.usuario.Usuario;

@Entity
@Table(name="pessoa", schema="public")
@SequenceGenerator(name = "SEQ_PESSOA", sequenceName = "SEQ_PESSOA", allocationSize = 1)
public class Pessoa {

	@Id
	@Column(name = "PESCODIGO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PESSOA")
	private Long pescodigo;
	
	@Column(name = "PESNOME")
	@NotNull
	@Size(max = 100)
	private String pesnome;
	
	@Column(name = "PESSEXO")
	@NotNull
	private Integer pessexo;
	
	@Column(name = "PESDATANAS")
	private Date pesdatanas;
	
	@Column(name = "PESCPF")
	@Size(max = 11)
	private String pescpf;
	
	@Column(name = "PESTELEFONE")
	@Size(max = 20)
	private String pestelefone;
	
	@Column(name = "PESCELULAR")
	@Size(max = 20)
	private String pescelular;
	
	@Column(name = "PESEMAIL")
	@Size(max = 100)
	private String pesemail;
	
	@ManyToOne
	@JoinColumn(name = "USUCODIGO")
	private Usuario usuario;
	
	@Column(name = "PESDATALT")
	@NotNull
	private Date pesdatalt;

	public Long getPescodigo() {
		return pescodigo;
	}

	public void setPescodigo(Long pescodigo) {
		this.pescodigo = pescodigo;
	}

	public String getPesnome() {
		return pesnome;
	}

	public void setPesnome(String pesnome) {
		this.pesnome = pesnome;
	}

	public Integer getPessexo() {
		return pessexo;
	}

	public void setPessexo(Integer pessexo) {
		this.pessexo = pessexo;
	}

	public Date getPesdatanas() {
		return pesdatanas;
	}

	public void setPesdatanas(Date pesdatanas) {
		this.pesdatanas = pesdatanas;
	}

	public String getPescpf() {
		return pescpf;
	}

	public void setPescpf(String pescpf) {
		this.pescpf = pescpf;
	}

	public String getPestelefone() {
		return pestelefone;
	}

	public void setPestelefone(String pestelefone) {
		this.pestelefone = pestelefone;
	}

	public String getPescelular() {
		return pescelular;
	}

	public void setPescelular(String pescelular) {
		this.pescelular = pescelular;
	}

	public String getPesemail() {
		return pesemail;
	}

	public void setPesemail(String pesemail) {
		this.pesemail = pesemail;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getPesdatalt() {
		return pesdatalt;
	}

	public void setPesdatalt(Date pesdatalt) {
		this.pesdatalt = pesdatalt;
	}
}