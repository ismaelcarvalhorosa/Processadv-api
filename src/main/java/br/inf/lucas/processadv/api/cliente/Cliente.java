package br.inf.lucas.royalrangers.api.cidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.inf.lucas.royalrangers.api.UF;

@Entity
@Table(name="cidade", schema="public")
@SequenceGenerator(name = "SEQ_CIDADE", sequenceName = "SEQ_CIDADE", allocationSize = 1)
public class Cidade {

	@Id
	@Column(name = "cidcodigo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CIDADE")
	private Long cidcodigo;
	
	@NotNull
	@Size(max = 100)
	private String cidnome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private UF ciduf;

	public Long getCidcodigo() {
		return cidcodigo;
	}

	public void setCidcodigo(Long cidcodigo) {
		this.cidcodigo = cidcodigo;
	}

	public String getCidnome() {
		return cidnome;
	}

	public void setCidnome(String cidnome) {
		this.cidnome = cidnome;
	}

	public UF getCiduf() {
		return ciduf;
	}

	public void setCiduf(UF ciduf) {
		this.ciduf = ciduf;
	}
}