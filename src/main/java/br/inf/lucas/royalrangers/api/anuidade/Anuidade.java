package br.inf.lucas.royalrangers.api.anuidade;

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

import br.inf.lucas.royalrangers.api.destacamento.Destacamento;
import br.inf.lucas.royalrangers.api.explorador.Explorador;
import br.inf.lucas.royalrangers.api.usuario.Usuario;

@Entity
@Table(name="anuidade", schema="public")
@SequenceGenerator(name = "SEQ_ANUIDADE", sequenceName = "SEQ_ANUIDADE", allocationSize = 1)
public class Anuidade {

	@Id
	@Column(name = "ANUCODIGO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ANUIDADE")
	private Long anucodigo;
	
	@ManyToOne
	@JoinColumn(name = "DESCODIGO")
	private Destacamento destacamento;
	
	@ManyToOne
	@JoinColumn(name = "EXPCODIGO")
	private Explorador explorador;
	
	@Column(name = "ANUANO")
	@NotNull
	private Long anuano;

	@Column(name = "ANUDATEMI")
	private Date anudatemi;
	
	@Column(name = "ANUDATVEN")
	private Date anudatven;
	
	@Column(name = "ANUVALOR")
	private Double anuvalor;
	
	@Column(name = "ANUDATPAG")
	private Date anudatpag;
	
	@Column(name = "ANUVALORPAGO")
	private Double anuvalorpago;
	
	@Column(name = "USUCODIGO")
	private int usucodigo;

	public Long getAnucodigo() {
		return anucodigo;
	}

	public void setAnucodigo(Long anucodigo) {
		this.anucodigo = anucodigo;
	}

	public Destacamento getDestacamento() {
		return destacamento;
	}

	public void setDestacamento(Destacamento destacamento) {
		this.destacamento = destacamento;
	}

	public Explorador getExplorador() {
		return explorador;
	}

	public void setExplorador(Explorador explorador) {
		this.explorador = explorador;
	}

	public Date getAnudatemi() {
		return anudatemi;
	}

	public void setAnudatemi(Date anudatemi) {
		this.anudatemi = anudatemi;
	}

	public Date getAnudatven() {
		return anudatven;
	}

	public void setAnudatven(Date anudatven) {
		this.anudatven = anudatven;
	}

	public Double getAnuvalor() {
		return anuvalor;
	}

	public void setAnuvalor(Double anuvalor) {
		this.anuvalor = anuvalor;
	}

	public Date getAnudatpag() {
		return anudatpag;
	}

	public void setAnudatpag(Date anudatpag) {
		this.anudatpag = anudatpag;
	}

	public Double getAnuvalorpago() {
		return anuvalorpago;
	}

	public void setAnuvalorpago(Double anuvalorpago) {
		this.anuvalorpago = anuvalorpago;
	}

	public int getUsucodigo() {
		return usucodigo;
	}

	public void setUsucodigo(int usucodigo) {
		this.usucodigo = usucodigo;
	}
	
	public Long getAnuano() {
		return anuano;
	}

	public void setAnuano(Long anuano) {
		this.anuano = anuano;
	}
}