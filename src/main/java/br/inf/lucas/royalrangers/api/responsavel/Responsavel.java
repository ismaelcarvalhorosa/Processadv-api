package br.inf.lucas.royalrangers.api.responsavel;

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

import br.inf.lucas.royalrangers.api.cidade.Cidade;
import br.inf.lucas.royalrangers.api.destacamento.Destacamento;
import br.inf.lucas.royalrangers.api.grupo.Grupo;
import br.inf.lucas.royalrangers.api.pessoa.Pessoa;
import br.inf.lucas.royalrangers.api.usuario.Usuario;

@Entity
@Table(name="responsavel", schema="public")
@SequenceGenerator(name = "SEQ_RESPONSAVEL", sequenceName = "SEQ_RESPONSAVEL", allocationSize = 1)
public class Responsavel {

	@Id
	@Column(name = "RESCODIGO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESPONSAVEL")
	private Long rescodigo;
	
	@ManyToOne
	@JoinColumn(name = "PESCODIGO")
	@NotNull
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name = "DESCODIGO")
	@NotNull
	private Destacamento destacamento;
	
	@ManyToOne
	@JoinColumn(name = "CIDCODIGO")
	@NotNull
	private Cidade cidade;
	
	@Column(name = "RESENDERECO")
	@Size(max = 100)
	private String resendereco;
	
	@Column(name = "RESNUMERO")
	@Size(max = 5)
	private String resnumero;
	
	@Column(name = "RESBAIRRO")
	@Size(max = 50)
	private String resbairro;
	
	@Column(name = "RESCOMPLEMENTO")
	@Size(max = 100)
	private String rescomplemento;
	
	@Column(name = "RESDATINI")
	@NotNull
	private Date resdatini;
	
	@Column(name = "RESDATFIM")
	private Date resdatfim;
	
	@Column(name = "USUCODIGO")
	private int usucodigo;
	
	@Column(name = "RESDATALT")
	@NotNull
	private Date resdatalt;

	public Long getRescodigo() {
		return rescodigo;
	}

	public void setRescodigo(Long rescodigo) {
		this.rescodigo = rescodigo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Destacamento getDestacamento() {
		return destacamento;
	}

	public void setDestacamento(Destacamento destacamento) {
		this.destacamento = destacamento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getResendereco() {
		return resendereco;
	}

	public void setResendereco(String resendereco) {
		this.resendereco = resendereco;
	}

	public String getResnumero() {
		return resnumero;
	}

	public void setResnumero(String resnumero) {
		this.resnumero = resnumero;
	}

	public String getResbairro() {
		return resbairro;
	}

	public void setResbairro(String resbairro) {
		this.resbairro = resbairro;
	}

	public String getRescomplemento() {
		return rescomplemento;
	}

	public void setRescomplemento(String rescomplemento) {
		this.rescomplemento = rescomplemento;
	}

	public Date getResdatini() {
		return resdatini;
	}

	public void setResdatini(Date resdatini) {
		this.resdatini = resdatini;
	}

	public Date getResdatfim() {
		return resdatfim;
	}

	public void setResdatfim(Date resdatfim) {
		this.resdatfim = resdatfim;
	}

	public int getUsucodigo() {
		return usucodigo;
	}

	public void setUsucodigo(int usucodigo) {
		this.usucodigo = usucodigo;
	}

	public Date getResdatalt() {
		return resdatalt;
	}

	public void setResdatalt(Date resdatalt) {
		this.resdatalt = resdatalt;
	}
}