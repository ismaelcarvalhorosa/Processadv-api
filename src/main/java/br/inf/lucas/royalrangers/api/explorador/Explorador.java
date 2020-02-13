package br.inf.lucas.royalrangers.api.explorador;

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
import br.inf.lucas.royalrangers.api.pessoa.Pessoa;
import br.inf.lucas.royalrangers.api.responsavel.Responsavel;
import br.inf.lucas.royalrangers.api.usuario.Usuario;

@Entity
@Table(name="explorador", schema="public")
@SequenceGenerator(name = "SEQ_EXPLORADOR", sequenceName = "SEQ_EXPLORADOR", allocationSize = 1)
public class Explorador {

	@Id
	@Column(name = "EXPCODIGO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EXPLORADOR")
	private Long expcodigo;
	
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
	
	@ManyToOne
	@JoinColumn(name = "RESCODIGO")
	@NotNull
	private Responsavel responsavel;
	
	@Column(name = "EXPENDERECO")
	@Size(max = 100)
	private String expendereco;
	
	@Column(name = "EXPNUMERO")
	@Size(max = 5)
	private String expnumero;
	
	@Column(name = "EXPBAIRRO")
	@Size(max = 50)
	private String expbairro;
	
	@Column(name = "EXPCOMPLEMENTO")
	@Size(max = 100)
	private String expcomplemento;
	
	@Column(name = "EXPCEP")
	@Size(max = 8)
	private String expcep;
	
	@Column(name = "EXPDATINI")
	@NotNull
	private Date expdatini;
	
	@Column(name = "EXPDATFIM")
	private Date expdatfim;
	
	@ManyToOne
	@JoinColumn(name = "USUCODIGO")
	private Usuario usuario;
	
	@Column(name = "EXPDATALT")
	@NotNull
	private Date expdatalt;

	public Long getExpcodigo() {
		return expcodigo;
	}

	public void setExpcodigo(Long expcodigo) {
		this.expcodigo = expcodigo;
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

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public String getExpendereco() {
		return expendereco;
	}

	public void setExpendereco(String expendereco) {
		this.expendereco = expendereco;
	}

	public String getExpnumero() {
		return expnumero;
	}

	public void setExpnumero(String expnumero) {
		this.expnumero = expnumero;
	}

	public String getExpbairro() {
		return expbairro;
	}

	public void setExpbairro(String expbairro) {
		this.expbairro = expbairro;
	}

	public String getExpcomplemento() {
		return expcomplemento;
	}

	public void setExpcomplemento(String expcomplemento) {
		this.expcomplemento = expcomplemento;
	}

	public Date getExpdatini() {
		return expdatini;
	}

	public void setExpdatini(Date expdatini) {
		this.expdatini = expdatini;
	}

	public Date getExpdatfim() {
		return expdatfim;
	}

	public void setExpdatfim(Date expdatfim) {
		this.expdatfim = expdatfim;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getExpdatalt() {
		return expdatalt;
	}

	public void setExpdatalt(Date expdatalt) {
		this.expdatalt = expdatalt;
	}

	public String getExpcep() {
		return expcep;
	}

	public void setExpcep(String expcep) {
		this.expcep = expcep;
	}
}