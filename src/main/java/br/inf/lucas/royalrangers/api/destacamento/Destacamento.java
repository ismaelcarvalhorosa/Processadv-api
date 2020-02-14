package br.inf.lucas.royalrangers.api.destacamento;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import br.inf.lucas.royalrangers.api.comandante.Comandante;
import br.inf.lucas.royalrangers.api.coordenador.Coordenador;
import br.inf.lucas.royalrangers.api.distrito.Distrito;
import br.inf.lucas.royalrangers.api.igreja.Igreja;
import br.inf.lucas.royalrangers.api.usuario.Usuario;

@Entity
@Table(name="destacamento", schema="public")
@SequenceGenerator(name = "SEQ_DESTACAMENTO", sequenceName = "SEQ_DESTACAMENTO", allocationSize = 1)
public class Destacamento {

	@Id
	@Column(name = "DESCODIGO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DESTACAMENTO")
	private Long descodigo;
	
	@Column(name = "DESNOME")
	@NotNull
	@Size(max = 100)
	private String desnome;
	
	@Column(name = "DESENDERECO")
	@Size(max = 100)
	private String desendereco;
	
	@Column(name = "DESNUMERO")
	@Size(max = 5)
	private String desnumero;
	
	@Column(name = "DESBAIRRO")
	@Size(max = 50)
	private String desbairro;
	
	@Column(name = "DESCOMPLEMENTO")
	@Size(max = 20)
	private String descomplemento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CIDCODIGO")
	private Cidade cidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISCODIGO")
	private Distrito distrito;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name = "IGRCODIGO")
	private Igreja igreja;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COOCODIGO")
	private Coordenador coordenador;

	@Column(name = "DESCEP")
	@Size(max = 8)
	private String descep;
	
	@Column(name = "DESTELEFONE")
	@Size(max = 20)
	private String destelefone;
	
	@Column(name = "DESEMAIL")
	@Size(max = 100)
	private String desemail;
	
	@Column(name = "DESSITE")
	@Size(max = 100)
	private String dessite;
	
	@Column(name = "DESFACEBOOK")
	@Size(max = 100)
	private String desfacebook;
	
	@Column(name = "DESINSTAGRAM")
	@Size(max = 100)
	private String desinstagram;
	
	@Column(name = "DESDIAPAG")
	private Integer desdiapag;
	
	@Column(name = "DESVALORMENSAL")
	private Double desvalormensal;
	
	@Column(name = "DESDATALT")
	@NotNull
	private Date desdatalt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USUCODIGO")
	private Usuario usuario;

	public Long getDescodigo() {
		return descodigo;
	}

	public void setDescodigo(Long descodigo) {
		this.descodigo = descodigo;
	}

	public String getDesnome() {
		return desnome;
	}

	public void setDesnome(String desnome) {
		this.desnome = desnome;
	}

	public String getDesendereco() {
		return desendereco;
	}

	public void setDesendereco(String desendereco) {
		this.desendereco = desendereco;
	}

	public String getDesnumero() {
		return desnumero;
	}

	public void setDesnumero(String desnumero) {
		this.desnumero = desnumero;
	}

	public String getDesbairro() {
		return desbairro;
	}

	public void setDesbairro(String desbairro) {
		this.desbairro = desbairro;
	}

	public String getDescomplemento() {
		return descomplemento;
	}

	public void setDescomplemento(String descomplemento) {
		this.descomplemento = descomplemento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public String getDescep() {
		return descep;
	}

	public void setDescep(String descep) {
		this.descep = descep;
	}

	public String getDestelefone() {
		return destelefone;
	}

	public void setDestelefone(String destelefone) {
		this.destelefone = destelefone;
	}

	public String getDesemail() {
		return desemail;
	}

	public void setDesemail(String desemail) {
		this.desemail = desemail;
	}

	public String getDessite() {
		return dessite;
	}

	public void setDessite(String dessite) {
		this.dessite = dessite;
	}

	public String getDesfacebook() {
		return desfacebook;
	}

	public void setDesfacebook(String desfacebook) {
		this.desfacebook = desfacebook;
	}

	public String getDesinstagram() {
		return desinstagram;
	}

	public void setDesinstagram(String desinstagram) {
		this.desinstagram = desinstagram;
	}

	public Integer getDesdiapag() {
		return desdiapag;
	}

	public void setDesdiapag(Integer desdiapag) {
		this.desdiapag = desdiapag;
	}

	public Double getDesvalormensal() {
		return desvalormensal;
	}

	public void setDesvalormensal(Double desvalormensal) {
		this.desvalormensal = desvalormensal;
	}

	public Date getDesdatalt() {
		return desdatalt;
	}

	public void setDesdatalt(Date desdatalt) {
		this.desdatalt = desdatalt;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public Igreja getIgreja() {
		return igreja;
	}

	public void setIgreja(Igreja igreja) {
		this.igreja = igreja;
	}

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}
}