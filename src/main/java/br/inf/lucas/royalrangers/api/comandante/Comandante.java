package br.inf.lucas.royalrangers.api.comandante;

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
import br.inf.lucas.royalrangers.api.distrito.Distrito;
import br.inf.lucas.royalrangers.api.grupo.Grupo;
import br.inf.lucas.royalrangers.api.pessoa.Pessoa;
import br.inf.lucas.royalrangers.api.usuario.Usuario;

@Entity
@Table(name="comandante", schema="public")
@SequenceGenerator(name = "SEQ_COMANDANTE", sequenceName = "SEQ_COMANDANTE", allocationSize = 1)
public class Comandante {

	@Id
	@Column(name = "COMCODIGO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMANDANTE")
	private Long comcodigo;
	
	@ManyToOne
	@JoinColumn(name = "PESCODIGO")
	@NotNull
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name = "CIDCODIGO")
	@NotNull
	private Cidade cidade;
	
	@Column(name = "COMENDERECO")
	@Size(max = 100)
	private String comendereco;
	
	@Column(name = "COMNUMERO")
	@Size(max = 5)
	private String comnumero;
	
	@Column(name = "COMBAIRRO")
	@Size(max = 50)
	private String combairro;
	
	@Column(name = "COMCOMPLEMENTO")
	@Size(max = 100)
	private String comcomplemento;
	
	@ManyToOne
	@JoinColumn(name = "DESCODIGO")
	@NotNull
	private Destacamento destacamento;
	
	@ManyToOne
	@JoinColumn(name = "GRUCODIGO")
	private Grupo grupo;
	
	@Column(name = "COMDATINI")
	@NotNull
	private Date comdatini;
	
	@Column(name = "COMDATFIM")
	private Date comdatfim;
	
	@Column(name = "USUCODIGO")
	private int usucodigo;
	
	@Column(name = "COMDATALT")
	@NotNull
	private Date comdatalt;

	public Long getComcodigo() {
		return comcodigo;
	}

	public void setComcodigo(Long comcodigo) {
		this.comcodigo = comcodigo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getComendereco() {
		return comendereco;
	}

	public void setComendereco(String comendereco) {
		this.comendereco = comendereco;
	}

	public String getComnumero() {
		return comnumero;
	}

	public void setComnumero(String comnumero) {
		this.comnumero = comnumero;
	}

	public String getCombairro() {
		return combairro;
	}

	public void setCombairro(String combairro) {
		this.combairro = combairro;
	}

	public String getComcomplemento() {
		return comcomplemento;
	}

	public void setComcomplemento(String comcomplemento) {
		this.comcomplemento = comcomplemento;
	}

	public Destacamento getDestacamento() {
		return destacamento;
	}

	public void setDestacamento(Destacamento destacamento) {
		this.destacamento = destacamento;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Date getComdatini() {
		return comdatini;
	}

	public void setComdatini(Date comdatini) {
		this.comdatini = comdatini;
	}

	public Date getComdatfim() {
		return comdatfim;
	}

	public void setComdatfim(Date comdatfim) {
		this.comdatfim = comdatfim;
	}

	public int getUsucodigo() {
		return usucodigo;
	}

	public void setUsucodigo(int usucodigo) {
		this.usucodigo = usucodigo;
	}

	public Date getComdatalt() {
		return comdatalt;
	}

	public void setComdatalt(Date comdatalt) {
		this.comdatalt = comdatalt;
	}
}