package br.inf.lucas.royalrangers.api.grupo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="grupo", schema="public")
@SequenceGenerator(name = "SEQ_GRUPO", sequenceName = "SEQ_GRUPO", allocationSize = 1)
public class Grupo {

	@Id
	@Column(name = "GRUCODIGO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GRUPO")
	private Long grucodigo;
	
	@Column(name = "GRUNOME")
	@NotNull
	@Size(max = 100)
	private String grunome;
	
	@Column(name = "GRUIDADE")
	@Size(max = 50)
	private String gruidade;

	public Long getGrucodigo() {
		return grucodigo;
	}

	public void setGrucodigo(Long grucodigo) {
		this.grucodigo = grucodigo;
	}

	public String getGrunome() {
		return grunome;
	}

	public void setGrunome(String grunome) {
		this.grunome = grunome;
	}

	public String getGruidade() {
		return gruidade;
	}

	public void setGruidade(String gruidade) {
		this.gruidade = gruidade;
	}
}