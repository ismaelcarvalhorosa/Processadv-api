package br.inf.lucas.royalrangers.api.igreja;

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
import br.inf.lucas.royalrangers.api.usuario.Usuario;

@Entity
@Table(name="igreja", schema="public")
@SequenceGenerator(name = "SEQ_IGREJA", sequenceName = "SEQ_IGREJA", allocationSize = 1)
public class Igreja {

	@Id
	@Column(name = "IGRCODIGO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IGREJA")
	private Long igrcodigo;
	
	@Column(name = "IGRNOME")
	@Size(max = 100)
	private String igrnome;
	
	@Column(name = "IGRPASTOR")
	@Size(max = 100)
	private String igrpastor;
	
	@Column(name = "IGRPASTOR2")
	@Size(max = 100)
	private String igrpastor2;
	
	@Column(name = "IGRENDERECO")
	@Size(max = 100)
	private String igrendereco;
	
	@Column(name = "IGRNUMERO")
	@Size(max = 5)
	private String igrnumero;
	
	@Column(name = "IGRBAIRRO")
	@Size(max = 50)
	private String igrbairro;
	
	@Column(name = "IGRCOMPLEMENTO")
	@Size(max = 100)
	private String igrcomplemento;
	
	@Column(name = "IGRCEP")
	@Size(max = 8)
	private String igrcep;
	
	@Column(name = "IGRCONTATO")
	@Size(max = 20)
	private String igrcontato;
	
	@Column(name = "IGRCONTATO2")
	@Size(max = 20)
	private String igrcontato2;
	
	@Column(name = "IGREMAIL")
	@Size(max = 100)
	private String igremail;
	
	@Column(name = "IGRSITE")
	@Size(max = 100)
	private String igrsite;
	
	@ManyToOne
	@JoinColumn(name = "CIDCODIGO")
	private Cidade cidade;
	
	@Column(name = "USUCODIGO")
	private int usucodigo;
	
	@Column(name = "IGRDATALT")
	private Date igrdatalt;

	public Long getIgrcodigo() {
		return igrcodigo;
	}

	public void setIgrcodigo(Long igrcodigo) {
		this.igrcodigo = igrcodigo;
	}

	public String getIgrnome() {
		return igrnome;
	}

	public void setIgrnome(String igrnome) {
		this.igrnome = igrnome;
	}

	public String getIgrpastor() {
		return igrpastor;
	}

	public void setIgrpastor(String igrpastor) {
		this.igrpastor = igrpastor;
	}

	public String getIgrpastor2() {
		return igrpastor2;
	}

	public void setIgrpastor2(String igrpastor2) {
		this.igrpastor2 = igrpastor2;
	}

	public String getIgrendereco() {
		return igrendereco;
	}

	public void setIgrendereco(String igrendereco) {
		this.igrendereco = igrendereco;
	}

	public String getIgrnumero() {
		return igrnumero;
	}

	public void setIgrnumero(String igrnumero) {
		this.igrnumero = igrnumero;
	}

	public String getIgrbairro() {
		return igrbairro;
	}

	public void setIgrbairro(String igrbairro) {
		this.igrbairro = igrbairro;
	}

	public String getIgrcomplemento() {
		return igrcomplemento;
	}

	public void setIgrcomplemento(String igrcomplemento) {
		this.igrcomplemento = igrcomplemento;
	}

	public String getIgrcep() {
		return igrcep;
	}

	public void setIgrcep(String igrcep) {
		this.igrcep = igrcep;
	}

	public String getIgrcontato() {
		return igrcontato;
	}

	public void setIgrcontato(String igrcontato) {
		this.igrcontato = igrcontato;
	}

	public String getIgrcontato2() {
		return igrcontato2;
	}

	public void setIgrcontato2(String igrcontato2) {
		this.igrcontato2 = igrcontato2;
	}

	public String getIgremail() {
		return igremail;
	}

	public void setIgremail(String igremail) {
		this.igremail = igremail;
	}

	public String getIgrsite() {
		return igrsite;
	}

	public void setIgrsite(String igrsite) {
		this.igrsite = igrsite;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public int getUsucodigo() {
		return usucodigo;
	}

	public void setUsucodigo(int usucodigo) {
		this.usucodigo = usucodigo;
	}

	public Date getIgrdatalt() {
		return igrdatalt;
	}

	public void setIgrdatalt(Date igrdatalt) {
		this.igrdatalt = igrdatalt;
	}
}